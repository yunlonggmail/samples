package com.yunlong.samples.systemfunction.wifip2p;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/20.
 * Wi-Fi直连
 */

public class WiFiP2PMainActivity extends BaseActivity implements View.OnClickListener, BaseAdapter.OnItemClickListener {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.WiFiP2PMain";

    /**
     * 开始搜索WiFi P2P设备
     */
    @Bind(R.id.btn_wifi_p2p_start_discover)
    Button btnWifiP2PDiscover;
    /**
     * WifiP2P管理器
     */
    private WifiP2pManager mWifiP2PManager;
    /**
     * WiFiP2P频道
     */
    private WifiP2pManager.Channel mChannel;

    /**
     * WiFi广播
     */
    private WiFiP2PBroadcastReceiver mWiFiP2PBroadcastReceiver;
    /**
     * Wifi P2P 设备
     */
    @Bind(R.id.rv_wifi_p2p_devices)
    RecyclerView rvWifiP2PDevices;
    /**
     * 适配器
     */
    private WiFiP2PDeviceAdapter mWiFiP2PDeviceAdapter;
    /**
     * Wifi设备集合
     */
    private List<WifiP2pDevice> mWifiP2pDeviceList;
    /**
     * 选中Wifi P2P设备
     */
    private WifiP2pDevice mSelectWifiP2pDevice;
    /**
     * 创建客户端
     */
    @Bind(R.id.btn_connect_wifi_p2p)
    Button btnConnectWifiP2P;
    /**
     * 发送消息
     */
    @Bind(R.id.btn_send_msg)
    Button btnSendMsg;
    /**
     * 客户端消息
     */
    @Bind(R.id.et_client_msg)
    EditText etClientMsg;
    /**
     * WifiP2P处理器
     */
    private WifiP2PHandler mWifiP2PHandler = new WifiP2PHandler(this);
    /**
     * 端口号
     */
    private static final int PORT = 8888;

    /**
     * 服务端Host
     */
    private String serverHost = "";

    /**
     * 服务端信息接收器
     */
    private static class WifiP2PHandler extends Handler {

        private WeakReference<WiFiP2PMainActivity> weakReference;

        private WiFiP2PMainActivity wiFiP2PMainActivity;

        public WifiP2PHandler(WiFiP2PMainActivity wiFiP2PMainActivity) {
            if (wiFiP2PMainActivity != null)
                weakReference = new WeakReference<>(wiFiP2PMainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference != null)
                wiFiP2PMainActivity = weakReference.get();
            if (wiFiP2PMainActivity != null) {
                ToastUtils.show(wiFiP2PMainActivity, msg.obj.toString());
            }
        }
    }


    /**
     * PeerList监听
     */
    private WifiP2pManager.PeerListListener mPeerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peers) {
            Collection<WifiP2pDevice> collection = peers.getDeviceList();
            for (WifiP2pDevice peer : collection) {
                if (!mWifiP2pDeviceList.contains(peer)) {
                    mWifiP2pDeviceList.add(peer);
                }
            }
            mWiFiP2PDeviceAdapter.notifyDataSetChanged();
        }
    };

    /**
     * 连接信息数据
     */
    private WifiP2pManager.ConnectionInfoListener mConnectionInfoListener = new WifiP2pManager.ConnectionInfoListener() {
        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo info) {
            if (info.groupFormed && info.isGroupOwner) {
                ToastUtils.show(mContext, "我是Server");
                etClientMsg.setText("我是Server端的数据");
                btnSendMsg.setVisibility(View.GONE);
                createWifiP2PServer();
            } else if (info.groupFormed) {
                etClientMsg.setText("我是Client的数据");
                ToastUtils.show(mContext, "我是Client");
                btnSendMsg.setVisibility(View.VISIBLE);
                serverHost = info.groupOwnerAddress.getHostAddress();
            }
        }
    };

    /**
     * WifiP2P广播
     */
    public class WiFiP2PBroadcastReceiver extends BroadcastReceiver {
        /**
         * 当前界面
         */
        private WeakReference<WiFiP2PMainActivity> weakReference;

        public WiFiP2PBroadcastReceiver(WiFiP2PMainActivity wiFiP2PMainActivity) {
            weakReference = new WeakReference<>(wiFiP2PMainActivity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            WifiP2pManager wifiP2pManager = null;
            WifiP2pManager.Channel channel = null;
            WiFiP2PMainActivity wiFiP2PMainActivity = null;
            if (weakReference != null) {
                wiFiP2PMainActivity = weakReference.get();
            }
            if (wiFiP2PMainActivity != null) {
                wifiP2pManager = wiFiP2PMainActivity.mWifiP2PManager;
                channel = wiFiP2PMainActivity.mChannel;
            } else {
                return;
            }
            String action = intent.getAction();
            if (action.equals(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)) {
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                    ToastUtils.show(mContext, "WiFi P2P 状态可用");
                } else {
                    ToastUtils.show(mContext, "WiFi P2P 状态不可用");
                }
            } else if (action.equals(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)) {
                if (wifiP2pManager != null) {
                    wifiP2pManager.requestPeers(channel, wiFiP2PMainActivity.mPeerListListener);
                }
            } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
                ToastUtils.show(mContext, "WiFi  P2P连接状态改变");
                wifiP2pManager.requestConnectionInfo(mChannel, wiFiP2PMainActivity.mConnectionInfoListener);

            } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
                // Respond to this device's wifi state changing
            }
        }
    }


    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_wifi_p2p;
    }

    @Override
    protected void initView() {
        btnWifiP2PDiscover.setOnClickListener(this);
        btnConnectWifiP2P.setOnClickListener(this);
        btnSendMsg.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mWifiP2PManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mWifiP2PManager.initialize(mContext, getMainLooper(), null);
        mWiFiP2PBroadcastReceiver = new WiFiP2PBroadcastReceiver(this);

        mWifiP2pDeviceList = new ArrayList<>();
        mWiFiP2PDeviceAdapter = new WiFiP2PDeviceAdapter(mContext, mWifiP2pDeviceList);
        mWiFiP2PDeviceAdapter.setOnItemClickListener(this);
        rvWifiP2PDevices.setLayoutManager(new LinearLayoutManager(this));
        rvWifiP2PDevices.setAdapter(mWiFiP2PDeviceAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wifi_p2p_start_discover:
                discoverPeer();
                break;
            case R.id.btn_connect_wifi_p2p:
                connectP2PClient();
                break;
            case R.id.btn_send_msg:
                if (!StringsUtils.isEmpty(serverHost)) {
                    clientSendMsg(serverHost);
                } else {
                    ToastUtils.show(mContext, "服务端未找到。。。。");
                }
                break;
        }
    }

    /**
     * 创建客户端
     */
    private void clientSendMsg(String host) {
        String str = etClientMsg.getText().toString();
        if (StringsUtils.isEmpty(str)) {
            ToastUtils.show(mContext, "请输入客户端需要向服务端传递的信息");
            return;
        }

        ClientThread clientThread = new ClientThread(host, str);
        clientThread.start();
    }

    /**
     * 连接P2P
     */
    private void connectP2PClient() {
        if (mSelectWifiP2pDevice == null) {
            ToastUtils.show(mContext, "请选择一个设备");
            return;
        }

        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = mSelectWifiP2pDevice.deviceAddress;
        config.wps.setup = WpsInfo.PBC;

        mWifiP2PManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                ToastUtils.show(mContext, "与设备" + mSelectWifiP2pDevice.deviceName + "连接成功");
            }

            @Override
            public void onFailure(int reason) {
                ToastUtils.show(mContext, "与设备" + mSelectWifiP2pDevice.deviceName + "连接失败，失败原因：" + reason);
            }
        });
    }

    /**
     * 创建服务端
     */
    private void createWifiP2PServer() {
        ServerThread serverThread = new ServerThread();
        serverThread.start();
    }

    /**
     * 发现周围设备
     */
    private void discoverPeer() {
        if (mWifiP2PManager != null)
            mWifiP2PManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    ToastUtils.show(mContext, "WiFi P2P检测成功");
                }

                @Override
                public void onFailure(int reason) {
                    ToastUtils.show(mContext, "WiFi P2P检测失败，原因是：" + reason);
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(Integer.MAX_VALUE);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        registerReceiver(mWiFiP2PBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWiFiP2PBroadcastReceiver != null) {
            unregisterReceiver(mWiFiP2PBroadcastReceiver);
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        mSelectWifiP2pDevice = mWiFiP2PDeviceAdapter.getItem(position);
        mWiFiP2PDeviceAdapter.setSelectItem(position);
        mWiFiP2PDeviceAdapter.notifyDataSetChanged();
    }

    public class ServerThread extends Thread {

        ServerSocket serverSocket;
        Socket client;
        InputStream is;
        OutputStream os;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(PORT);
                client = serverSocket.accept();

                while (true) {

                    is = client.getInputStream();
                    os = client.getOutputStream();
                    manageClientSocket();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            super.run();
        }

        /**
         * 处理Socket数据
         */
        private void manageClientSocket() {
            try {
                byte[] bytes = new byte[1024];
                int count = is.read(bytes);
                Message message = Message.obtain();
                if (count > 0) {
                    message.obj = new String(bytes, 0, count, "utf-8");
                    mWifiP2PHandler.sendMessage(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                try {
//                    if (is != null)
//                        is.close();
//                    if (os != null)
//                        os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }

    }

    public class ClientThread extends Thread {

        String host;

        String str;

        public ClientThread(String host, String info) {
            this.host = host;
            this.str = info;
        }


        @Override
        public void run() {
            Socket socket = new Socket();
            OutputStream os = null;
            try {

                socket.bind(null);
                socket.connect((new InetSocketAddress(host, PORT)), 500);

                os = socket.getOutputStream();
                os.write(str.getBytes("utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                try {
                    if (socket.isConnected())
                        socket.close();
                    if (os != null)
                        os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }

}
