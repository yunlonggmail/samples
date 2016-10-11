package com.yunlong.samples.intent;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.yunlong.samples.R;
import com.yunlong.samples.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by shiyunlong on 16/9/13.
 */
public class IntentMainActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.IntentMain";

    @Override
    protected int getResourceId() {
        return R.layout.a_intent_main;
    }

    @Override
    protected void initTitleBar() {
        toolbar.setTitle(R.string.nav_title_intent_main);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
