package com.yunlong.samples.test;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.LogUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/6/19.
 * 测试A
 */

public class TestAActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.TestA";

    public static final int REQUEST_CODE_START_B = 0x1001;

    @Bind(R.id.btn_start_b)
    Button btnStartB;

    @Bind(R.id.btn_start_b_for_result)
    Button btnStartBForResult;

    @Override
    protected int getResourceId() {
        return R.layout.a_test_a;
    }

    @Override
    protected void initView() {

        btnStartB.setOnClickListener(this);
        btnStartBForResult.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_b:
                startB();
                break;
            case R.id.btn_start_b_for_result:
                startBForResult();
                break;
        }
    }

    public void startB() {
        Intent intent = new Intent();
        intent.setAction(TestBActivity.INTENT_ACTION);
        startActivity(intent);
    }

    public void startBForResult() {
        Intent intent = new Intent();
        intent.setAction(TestBActivity.INTENT_ACTION);
        startActivityForResult(intent, REQUEST_CODE_START_B);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.E(this.getClass().getName(), "：onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        LogUtils.E(this.getClass().getName(), "：onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        LogUtils.E(this.getClass().getName(), "：onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        LogUtils.E(this.getClass().getName(), "：onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtils.E(this.getClass().getName(), "：onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtils.E(this.getClass().getName(), "：onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtils.E(this.getClass().getName(), "：onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.E(this.getClass().getName(), "：onActivityResult--->" + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtils.E(this.getClass().getName(), "：onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtils.E(this.getClass().getName(), "：onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        LogUtils.E(this.getClass().getName(), "：onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
