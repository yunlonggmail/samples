package com.yunlong.samples.custom.stroke;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

/**
 * Created by shiyunlong on 2017/3/16.
 * Copy界面
 */

public class StrokeTextViewActivity extends BaseActivity {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.custom.StrokeTextView";


    @Override
    protected int getResourceId() {
        return R.layout.a_custom_stroke_text_view;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_custom_stroke_text_view);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


}
