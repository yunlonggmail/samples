package com.yunlong.samples.animation.md;

import android.view.View;
import android.widget.Button;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/16.
 * Copy界面
 */

public class MDActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.animation.MD";

    @Bind(R.id.btn_ripple)
    Button btnRipple;

    @Override
    protected int getResourceId() {
        return R.layout.a_animation_md;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_animation_md);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

        btnRipple.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ripple:
                break;
        }
    }
}
