package com.yunlong.samples.intent;

import com.yunlong.samples.R;
import com.yunlong.samples.base.BaseActivity;

/**
 * Created by shiyunlong on 2016/10/19.
 * Intent不携带参数
 */
public class IntentWithoutBundleActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.IntentWithoutBundle";

    @Override
    protected int getResourceId() {
        return R.layout.a_intent_without_bundle;
    }

    @Override
    protected void initTitleBar() {
        toolbar.setTitle(R.string.nav_title_intent_without_bundle);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
