package com.yunlong.samples.request.ok;

import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.request.HttpRequestParams;
import com.yunlong.lib.request.ok.GETRequest;
import com.yunlong.lib.request.ok.HttpResponseListener;
import com.yunlong.samples.R;
import com.yunlong.samples.common.YGUrl;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * Copy界面
 */

public class OkHttpActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.request.OKHttp";

    @Bind(R.id.btn_get)
    Button btnGet;

    @Override
    protected int getResourceId() {
        return R.layout.a_request_ok;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_request_ok);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        btnGet.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                getMessage();
                break;
        }
    }

    /**
     * 获取消息
     */
    public void getMessage() {
        HttpRequestParams params = new HttpRequestParams();
        new GETRequest(YGUrl.METHOD_GET_DOCTOR_DETAIL, params, new HttpResponseListener());
    }
}
