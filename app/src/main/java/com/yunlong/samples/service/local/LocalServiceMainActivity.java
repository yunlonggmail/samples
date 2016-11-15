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
 * Local服务主界面
 * Activity在展现的时候同时只能展现一个.
 * 同时只有一个Activity可以bind到服务.
 * 如果想多个Activity bind到Service
 * 就分别在Activity的onResume onPause方法中执行bind和unbind方法
 */
public class LocalServiceMainActivity extends BaseActivity implements View.OnClickListener, LocalServiceConnection.LocalServiceConnectionListener {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.LocalServiceMain";
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
     * 同步数据
     */
    @Bind(R.id.btn_sync)
    public Button btnSync;
    /**
     * 解除绑定服务
     */
    @Bind(R.id.btn_unbind)
    public Button btnUnBindService;
    /**
     * 开启服务第二个界面
     */
    @Bind(R.id.btn_second)
    public Button btnStartSecondService;
    /**
     * Binder
     */
    LocalService.LocalServiceBinder localServiceBinder;
    /**
     * 绑定
     */
    private boolean mIsBind;


    @Override
    protected int getResourceId() {
        return R.layout.a_service_local_main;
    }


    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_service_local_service);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
        btnStartSecondService.setOnClickListener(this);
        btnSync.setOnClickListener(this);
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
            case R.id.btn_sync:
                sync();
                break;
            case R.id.btn_unbind:
                unBindService();
                break;
            case R.id.btn_second:
                startSecondServiceActivity();
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
        setServiceRunning();
        if (mIsBind && localServiceConnection != null)
            unbindService(localServiceConnection);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        LogUtils.D("LocalServiceMainActivity", " onServiceConnected ");
        mIsBind = true;
        localServiceBinder = (LocalService.LocalServiceBinder) iBinder;
        setDataChangeListener();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        LogUtils.D("LocalServiceMainActivity", " onServiceDisconnected ");
        localServiceBinder = null;
        mIsBind = false;
    }

    /**
     * 开启服务第二个界面
     */
    public void startSecondServiceActivity() {
        Intent intent = new Intent();
        intent.setAction(LocalServiceSecondActivity.INTENT_ACTION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
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

    /**
     * 设置服务Tag
     */
    public void setServiceRunning() {
        if (localServiceBinder != null)
            localServiceBinder.setRunning(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindService();
    }
}
