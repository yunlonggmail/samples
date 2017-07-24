package com.yunlong.samples.custom.trebling;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

/**
 * Created by shiyunlong on 2017/7/19.
 * 测试三重View界面
 */

public class TreblingViewActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.custom.TreblingView";

    @Override
    protected int getResourceId() {
        return R.layout.a_custom_trebling_view;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_custom_trebling_view);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
