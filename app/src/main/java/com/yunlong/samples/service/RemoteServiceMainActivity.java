package com.yunlong.samples.service;

import com.yunlong.samples.R;
import com.yunlong.samples.base.BaseActivity;

/**
 * Created by shiyunlong on 2016/10/27.
 * Local服务主界面
 */
public class RemoteServiceMainActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.RemoteServiceMain";

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

    }
}
