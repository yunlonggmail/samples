package com.yunlong.samples.custom.trebling;

import android.graphics.Color;
import android.view.View;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/7/19.
 * 测试三重View界面
 */

public class TreblingViewActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.custom.TreblingView";

    @Bind(R.id.tv_info)
    TreblingView tvInfo;


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
        tvInfo.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tvInfo.setMiddleFillPercent(55);
        tvInfo.setMiddleFillColor(mContext.getResources().getColor(R.color.blue));
        tvInfo.setTopTextColor(Color.WHITE);
        tvInfo.setBottomTextColor(mContext.getResources().getColor(R.color.blue));
        tvInfo.setStrokeWidth(mContext.getResources().getDimension(R.dimen.normal_width_1));
        tvInfo.setStrokeColor(Color.RED);
        tvInfo.setStrokeRadius(mContext.getResources().getDimension(R.dimen.normal_width_4));
    }

    protected void postData() {
        tvInfo.setMustInvalidate(false);
        tvInfo.setMiddleFillPercent(100);
        tvInfo.setMiddleFillColor(mContext.getResources().getColor(R.color.gray));
        tvInfo.setTopTextColor(Color.WHITE);
        tvInfo.setBottomTextColor(mContext.getResources().getColor(R.color.gray));
        tvInfo.setStrokeWidth(mContext.getResources().getDimension(R.dimen.normal_width_1));
        tvInfo.setStrokeColor(Color.BLACK);
        tvInfo.setStrokeRadius(mContext.getResources().getDimension(R.dimen.normal_width_4));
        tvInfo.doInvalidate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_info:
                postData();
                break;
        }
    }
}
