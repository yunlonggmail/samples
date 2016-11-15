package com.yunlong.samples.service.remote;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2016/10/27.
 * Local服务主界面
 */
public class RemoteServiceMainActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.RemoteServiceMain";

    @Bind(R.id.btn_start)
    Button btnStart;

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

    }


    @Override
    protected void initData() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RemoteService.class);
                startService(intent);
            }
        });
    }
}
