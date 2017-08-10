package com.yunlong.samples.design.state.impl;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.state.impl.first.TV;
import com.yunlong.samples.design.state.impl.second.Magnifier;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/10.
 * 状态测试代码
 */

public class StateImplTestActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.state.StateImplTest";
    /**
     * 电视
     */
    TV mTV;

    @Bind(R.id.tv_tv)
    TextView tvTV;

    @Bind(R.id.btn_power)
    TextView btnPower;

    @Bind(R.id.btn_minus_sound)
    Button btnMinusSound;

    @Bind(R.id.btn_plus_sound)
    Button btnPlusSound;

    @Bind(R.id.btn_pre_channel)
    Button btnPreChannel;

    @Bind(R.id.btn_next_channel)
    Button btnNextChannel;

    @Bind(R.id.tv_magnifier)
    TextView tvMagnifier;

    @Bind(R.id.btn_magnifier_enlarge)
    Button btnMagnifierEnlarge;

    Magnifier mMagnifier;

    @Override
    protected int getResourceId() {
        return R.layout.a_design_pattern_state_impl_test;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_state_impl_test);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

        btnPower.setOnClickListener(this);
        btnMinusSound.setOnClickListener(this);
        btnPlusSound.setOnClickListener(this);
        btnPreChannel.setOnClickListener(this);
        btnNextChannel.setOnClickListener(this);

        btnMagnifierEnlarge.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mTV = new TV();
        refreshTVUI();


        mMagnifier = new Magnifier();
        refreshMagnifier();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_power:
                mTV.powerTV();
                refreshTVUI();
                break;
            case R.id.btn_minus_sound:
                mTV.turnDownShound();
                refreshTVUI();
                break;
            case R.id.btn_plus_sound:
                mTV.turnUpSound();
                refreshTVUI();
                break;
            case R.id.btn_pre_channel:
                mTV.preShow();
                refreshTVUI();
                break;
            case R.id.btn_next_channel:
                mTV.nextShow();
                refreshTVUI();
                break;
            case R.id.btn_magnifier_enlarge:
                mMagnifier.click();
                refreshMagnifier();
                break;
        }
    }

    private void refreshTVUI() {
        tvTV.setText(mTV.toString());
    }


    private void refreshMagnifier() {
        tvMagnifier.setText(mMagnifier.toString());
    }
}
