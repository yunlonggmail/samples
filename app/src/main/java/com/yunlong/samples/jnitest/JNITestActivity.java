package com.yunlong.samples.jnitest;

import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.ToastUtils;
import com.yunlong.samples.R;
import com.yunlong.samples.jnitest.impl.JNITestUtils;

import butterknife.Bind;


public class JNITestActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.jni.Test";

    @Bind(R.id.btn_dynamic_1)
    Button btnDynamic1;

    @Override
    protected int getResourceId() {
        return R.layout.a_jni_test;
    }

    @Override
    protected void initView() {
        btnDynamic1.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dynamic_1:
                ToastUtils.show(mContext, JNITestUtils.newInstance().dynamicFunc1("test"));
                break;
        }
    }
}
