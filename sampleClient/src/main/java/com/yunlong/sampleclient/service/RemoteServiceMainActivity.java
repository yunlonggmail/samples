package com.yunlong.sampleclient.service;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.sampleclient.R;
import com.yunlong.samplelibrary.service.DataChangeListener;
import com.yunlong.samplelibrary.service.RemoteServiceMain;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2016/11/4.
 */

public class RemoteServiceMainActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.sampleclient.service.RemoteServiceMain";
    /**
     * 远程服务主函数
     */
    RemoteServiceMain remoteServiceMain;

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

    public DataChangeListener mDataChangeListener = new DataChangeListener() {
        @Override
        public void dataChange(final String data) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvServiceInfo.setText(data);
                }
            });
        }
    };

    @Override
    protected int getResourceId() {
        return R.layout.a_service_remote_main;
    }


    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_service_remote_service);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
        btnSync.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        remoteServiceMain = new RemoteServiceMain(this, mDataChangeListener);
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
        }
    }

    /**
     * 开启服务
     */
    public void startService() {
        remoteServiceMain.startService();
    }

    /**
     * 关闭服务
     */
    public void stopService() {
        remoteServiceMain.stopService();
    }

    /**
     * 开启服务
     */
    public void bindService() {
        remoteServiceMain.bindService();
    }

    /**
     * 关闭服务
     */
    public void unBindService() {
        remoteServiceMain.unBindService();
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
        remoteServiceMain.sync(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        remoteServiceMain.unBindService();
    }
}
