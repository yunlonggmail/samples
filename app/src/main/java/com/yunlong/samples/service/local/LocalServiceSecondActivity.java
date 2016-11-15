package com.yunlong.samples.service.local;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.LogUtils;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2016/10/27.
 * Local服务第二界面
 */
public class LocalServiceSecondActivity extends BaseActivity implements View.OnClickListener, LocalServiceConnection.LocalServiceConnectionListener {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.LocalServiceSecond";
    /**
     * Local服务Service
     * 在一个应用中, 一个服务只有一个对象
     * 限定于服务的开启和关闭,在其他界面可以对服务进行关闭
     */
    public Intent localServiceIntent;
    /**
     * 服务连接
     */
    public LocalServiceConnection localServiceConnection;
    /**
     * 服务信息
     */
    @Bind(R.id.tv_service_info)
    public TextView tvServiceInfo;
    /**
     * 传递到服务的信息
     */
    @Bind(R.id.et_service_info)
    public EditText etServiceInfo;
    /**
     * 开启服务
     */
    @Bind(R.id.btn_start)
    public Button btnStartService;
    /**
     * 停止服务
     */
    @Bind(R.id.btn_stop)
    public Button btnStopService;
    /**
     * 绑定服务
     */
    @Bind(R.id.btn_bind)
    public Button btnBindService;
    /**
     * 解除绑定服务
     */
    @Bind(R.id.btn_unbind)
    public Button btnUnBindService;

    /**
     * 同步数据
     */
    @Bind(R.id.btn_sync)
    public Button btnSync;
    /**
     * Binder
     */
    LocalService.LocalServiceBinder localServiceBinder;

    @Override
    protected int getResourceId() {
        return R.layout.a_service_local_second;
    }


    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_service_local_service_second);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnSync.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        localServiceIntent = new Intent(mContext, LocalService.class);
        localServiceConnection = LocalServiceConnection.newInstance(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startService();
                break;
            case R.id.btn_stop:
                stopService();
                break;
            case R.id.btn_bind:
                bindService();
                break;
            case R.id.btn_unbind:
                unBindService();
                break;
            case R.id.btn_sync:
                sync();
                break;
        }
    }

    /**
     * 开启服务
     */
    public void startService() {
        startService(localServiceIntent);
    }

    /**
     * 关闭服务
     */
    public void stopService() {
        stopService(localServiceIntent);
    }

    /**
     * 开启服务
     */
    public void bindService() {
        bindService(localServiceIntent, localServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 关闭服务
     */
    public void unBindService() {
        setServiceTag();
        if(localServiceConnection!=null)
        unbindService(localServiceConnection);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        LogUtils.D("LocalServiceMainActivity", " onServiceConnected ");
        localServiceBinder = (LocalService.LocalServiceBinder) iBinder;
        setDataChangeListener();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        LogUtils.D("LocalServiceMainActivity", " onServiceDisconnected ");
        localServiceBinder = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindService();
    }

    /**
     * 同步数据
     */
    public void sync() {
        String text = etServiceInfo.getText().toString();
        if (TextUtils.isEmpty(text)) {
            ToastUtils.show(mContext, "请填写数据!");
            return;
        }
        if (localServiceBinder != null) {
            localServiceBinder.updateData(text);
        }
        setDataChangeListener();
    }

    /**
     * 设置服务Tag
     */
    public void setServiceTag() {
        if (localServiceBinder != null)
            localServiceBinder.setRunning(false);
    }

    /**
     * 设置数据改变监听
     */
    public void setDataChangeListener() {
        if (localServiceBinder != null)
            localServiceBinder.setDataChangeListener(new LocalService.DataChangeListener() {
                @Override
                public void dataChange(final String data) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvServiceInfo.setText(data);
                        }
                    });
                }
            });
    }
}
