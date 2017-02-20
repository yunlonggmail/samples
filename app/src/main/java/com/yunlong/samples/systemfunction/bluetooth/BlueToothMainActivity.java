package com.yunlong.samples.systemfunction.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.lib.utils.PermissionUtils;
import com.yunlong.lib.utils.StringsUtils;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/17.
 * <p>
 * 默认蓝牙操作需要第一条权限
 * <uses-permission android:name="android.permission.BLUETOOTH" />
 * 静默蓝牙操作需要第二条权限
 * <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
 * <p>
 * 蓝牙权限被禁止时，功能无法使用
 * 6.0蓝牙搜索需要以下权限
 * android.permission.ACCESS_COARSE_LOCATION
 */

public class BluetoothMainActivity extends BaseActivity implements View.OnClickListener, BaseAdapter.OnItemClickListener {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.BluetoothMain";
    /**
     * 请求打开蓝牙
     */
    public static final int REQUEST_ENABLE_BLUETOOTH = 0x1001;
    /**
     * 请求权限
     */
    public static final int REQUEST_ACCESS_COARSE_LOCATION = 0x1002;
    /**
     * 蓝牙状态文本
     */
    @Bind(R.id.tv_bluetooth_tag)
    TextView tvBluetoothTag;

    /**
     * 正常打开蓝牙
     */
    @Bind(R.id.btn_normal_open_bluetooth)
    Button btnNormalOpenBluetooth;

    /**
     * 静默打开蓝牙
     */
    @Bind(R.id.btn_silent_open_bluetooth)
    Button btnSilentOpenBluetooth;

    /**
     * 搜索蓝牙
     */
    @Bind(R.id.btn_bluetooth_discovery)
    Button btnBluetoothDiscovery;
    /**
     * 蓝牙设备是否打开
     */
    private boolean mBlueToothTag;
    /**
     * 蓝牙适配器
     */
    private BluetoothAdapter mBluetoothAdapter;
    /**
     * 是否正在搜索
     */
    private boolean mIsDiscovering;

    /**
     * 蓝牙设备列表
     */
    @Bind(R.id.rv_bluetooth_devices)
    RecyclerView rvBluetoothDevices;

    /**
     * 蓝牙设备
     */
    private List<BluetoothDevice> mBluetoothDeviceList;

    /**
     * 蓝牙设备适配器
     */
    BluetoothDeviceAdapter mBluetoothDeviceAdapter;
    /**
     * 创建蓝牙服务端
     */
    @Bind(R.id.btn_create_bluetooth_server)
    Button btnCreateBluetoothServer;
    /**
     * 创建蓝牙移动端
     */
    @Bind(R.id.btn_create_bluetooth_client)
    Button btnCreateBluetoothClient;

    /**
     * 创建蓝牙客户端和服务端使用的UUID
     */
    private static UUID mUUID = UUID.fromString("abcdef01-abc2-def3-abc4-def567890abc");
    /**
     * 蓝牙服务端名称
     */
    private static String NAME = "Dragon";

    /**
     * 服务端Handler
     */
    private BlueHandler blueHandler;
    /**
     * 客户端Socket
     */
    private BluetoothSocket mBluetoothClientSocket;
    /**
     * 输出流
     */
    private OutputStream mOs;
    /**
     * 客户端蓝牙设备
     */
    private BluetoothDevice mClientBluetoothDevice;
    /**
     * 客户端Msg
     */
    @Bind(R.id.et_client_msg)
    EditText etClientMsg;

    /**
     * 服务端信息接收器
     */
    private static class BlueHandler extends Handler {

        private WeakReference<BluetoothMainActivity> weakReference;

        private BluetoothMainActivity bluetoothMainActivity;

        public BlueHandler(BluetoothMainActivity bluetoothMainActivity) {
            if (bluetoothMainActivity != null)
                weakReference = new WeakReference<>(bluetoothMainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference != null)
                bluetoothMainActivity = weakReference.get();
            if (bluetoothMainActivity != null) {
                ToastUtils.show(bluetoothMainActivity, msg.obj.toString());
            }
        }
    }

    /**
     * 发现蓝牙设备
     */
    private BroadcastReceiver mBluetoothDiscoveryBR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                appendBlueDevice(device);
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                appendBondedDevices();
                mIsDiscovering = false;
                btnBluetoothDiscovery.setText(R.string.a_bluetooth_start_discovery);
            }
        }
    };

    @Override
    protected int getResourceId() {
        return R.layout.a_bluetooth_main;
    }


    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_bluetooth_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

        btnNormalOpenBluetooth.setOnClickListener(this);
        btnSilentOpenBluetooth.setOnClickListener(this);
        btnBluetoothDiscovery.setOnClickListener(this);
        btnCreateBluetoothServer.setOnClickListener(this);
        btnCreateBluetoothClient.setOnClickListener(this);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            ToastUtils.show(mContext, R.string.a_bluetooth_not_support);
            finish();
        }

        mBlueToothTag = mBluetoothAdapter.isEnabled();
        tvBluetoothTag.setText(mBlueToothTag ? R.string.a_bluetooth_normal_open_enabled : R.string.a_bluetooth_normal_open_disabled);
        btnSilentOpenBluetooth.setText(mBlueToothTag ? R.string.a_bluetooth_silent_close : R.string.a_bluetooth_silent_open);

    }

    @Override
    protected void initData() {
        mBluetoothDeviceList = new ArrayList<>();
        mBluetoothDeviceAdapter = new BluetoothDeviceAdapter(mContext, mBluetoothDeviceList);
        mBluetoothDeviceAdapter.setOnItemClickListener(this);
        rvBluetoothDevices.setLayoutManager(new LinearLayoutManager(this));
        rvBluetoothDevices.setAdapter(mBluetoothDeviceAdapter);
        appendBondedDevices();
    }

    /**
     * 添加绑定设备
     */
    private void appendBondedDevices() {
        Set<BluetoothDevice> deviceSet = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : deviceSet) {
            appendBlueDevice(device);
        }
    }

    /**
     * 向文本中添加蓝牙设备
     *
     * @param bluetoothDevice
     */
    private void appendBlueDevice(BluetoothDevice bluetoothDevice) {
        if (!mBluetoothDeviceList.contains(bluetoothDevice))
            mBluetoothDeviceList.add(bluetoothDevice);
        mBluetoothDeviceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal_open_bluetooth:
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_ENABLE_BLUETOOTH);
                break;
            case R.id.btn_silent_open_bluetooth:
                blueToothSilentOperating();
                break;
            case R.id.btn_bluetooth_discovery:
                if (!mIsDiscovering) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PermissionUtils.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}
                                , REQUEST_ACCESS_COARSE_LOCATION);
                    } else {
                        discoverBluetooth();
                    }
                } else {
                    cancelBluetoothDiscovery();
                }
                break;
            case R.id.btn_create_bluetooth_server:
                createBluetoothServer();
                break;
            case R.id.btn_create_bluetooth_client:
                createBluetoothClient();
                break;
        }
    }


    /**
     * 蓝牙静默操作
     */
    private void blueToothSilentOperating() {
        boolean flag = false;
        if (mBlueToothTag) {
            flag = mBluetoothAdapter.disable();
            mBlueToothTag = !flag;
            if (flag) {
                ToastUtils.show(mContext, R.string.a_bluetooth_silent_close);
            }
        } else {
            flag = mBluetoothAdapter.enable();
            mBlueToothTag = flag;
            if (flag) {
                ToastUtils.show(mContext, R.string.a_bluetooth_silent_open);
            }
        }
        if (!mBlueToothTag) {
            btnSilentOpenBluetooth.setText(R.string.a_bluetooth_silent_open);
            tvBluetoothTag.setText(R.string.a_bluetooth_normal_open_disabled);
        } else {
            btnSilentOpenBluetooth.setText(R.string.a_bluetooth_silent_close);
            tvBluetoothTag.setText(R.string.a_bluetooth_normal_open_enabled);
        }
    }

    /**
     * 搜索蓝牙
     */
    private void discoverBluetooth() {
        if (!mBlueToothTag) {
            ToastUtils.show(mContext, R.string.a_bluetooth_please_open);
            return;
        }
        cancelBluetoothDiscovery();
        mBluetoothAdapter.startDiscovery();
        mIsDiscovering = true;
        btnBluetoothDiscovery.setText(R.string.a_bluetooth_cancel_discovery);

    }

    /**
     * 取消蓝牙检查
     */
    private void cancelBluetoothDiscovery() {
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
            mIsDiscovering = false;
            btnBluetoothDiscovery.setText(R.string.a_bluetooth_start_discovery);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (REQUEST_ENABLE_BLUETOOTH):
                if (resultCode == RESULT_OK) {
                    ToastUtils.show(mContext, R.string.a_bluetooth_normal_open_enabled);
                    tvBluetoothTag.setText(R.string.a_bluetooth_normal_open_enabled);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter foundIntentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        foundIntentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mBluetoothDiscoveryBR, foundIntentFilter);

        IntentFilter finishIntentFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        finishIntentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mBluetoothDiscoveryBR, finishIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBluetoothAdapter.isDiscovering())
            mBluetoothAdapter.cancelDiscovery();
        unregisterReceiver(mBluetoothDiscoveryBR);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isGrant = PermissionUtils.isGrant(grantResults);
        switch (requestCode) {
            case REQUEST_ACCESS_COARSE_LOCATION:
                if (isGrant) {
                    discoverBluetooth();
                } else {
                    if (!PermissionUtils.showPermissions(this, PermissionUtils.checkPermissions(this, permissions)))
                        PermissionUtils.showNeverAskDialog(this, permissions);
                }
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        BluetoothDevice bluetoothDevice = mBluetoothDeviceAdapter.getItem(position);
        if (bluetoothDevice != null) {
            mClientBluetoothDevice = mBluetoothAdapter.getRemoteDevice(bluetoothDevice.getAddress());
        }
        mBluetoothDeviceAdapter.setSelectItem(position);
        mBluetoothDeviceAdapter.notifyDataSetChanged();
    }

    /**
     * 创建蓝牙移动端
     */
    public void createBluetoothServer() {
        blueHandler = new BlueHandler(this);
        BluetoothServerThread bluetoothServerThread = new BluetoothServerThread();
        bluetoothServerThread.start();
    }

    /**
     * 创建蓝牙客户端
     */
    public void createBluetoothClient() {

        if (mClientBluetoothDevice == null) {
            ToastUtils.show(mContext, "请选择一个蓝牙设备！");
            return;
        }

        String str = etClientMsg.getText().toString();
        if (StringsUtils.isEmpty(str)) {
            ToastUtils.show(mContext, "请输入客户端传递信息");
            return;
        }

        try {
            mBluetoothClientSocket = mClientBluetoothDevice.createRfcommSocketToServiceRecord(mUUID);
            mBluetoothClientSocket.connect();

            mOs = mBluetoothClientSocket.getOutputStream();
            if (mOs != null)
                mOs.write(str.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class BluetoothServerThread extends Thread {
        /**
         * 服务端Socket
         */
        private BluetoothServerSocket bluetoothServerSocket;
        /**
         * 客户端Socket
         */
        private BluetoothSocket bluetoothClientSocket;
        /**
         * 输入流
         */
        private InputStream is;
        /**
         * 输出流
         */
        private OutputStream os;

        public BluetoothServerThread() {
            try {
                bluetoothServerSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, mUUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                bluetoothClientSocket = bluetoothServerSocket.accept();
                is = bluetoothClientSocket.getInputStream();
                os = bluetoothClientSocket.getOutputStream();
                if (bluetoothClientSocket != null)
                    manageClientSocket();
                cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /**
         * 关闭服务端Socket
         */
        private void cancel() {
            try {
                if (bluetoothServerSocket != null)
                    bluetoothServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 处理Socket数据
         */
        private void manageClientSocket() {
            try {
                byte[] bytes = new byte[1024];
                int count = is.read(bytes);
                Message message = Message.obtain();
                message.obj = new String(bytes, 0, count, "utf-8");
                blueHandler.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null)
                        is.close();
                    if (os != null)
                        os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
