package com.yunlong.samples.custom.loading;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/14.
 * 加载View
 */

public class LoadingViewActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.custom.LoadingView";

    @Bind(R.id.lv)
    LoadingView lv;

    @Override
    protected int getResourceId() {
        return R.layout.a_custom_loading_view;
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        lv.setDuration(2000);
        lv.setText("我是云龙");
        lv.setTextSize(mContext.getResources().getDimension(R.dimen.text_size_big_0));
        lv.setTextStrokeWidth(mContext.getResources().getDimension(R.dimen.normal_width_2));
        lv.setTopTextFillColor(mContext.getResources().getColor(R.color.white));
        lv.setTopTextStrokeColor(mContext.getResources().getColor(R.color.blue));
        lv.setBottomTextFillColor(mContext.getResources().getColor(R.color.blue));
        lv.setBottomTextStrokeColor(mContext.getResources().getColor(R.color.white));
        lv.setStrokeColor(mContext.getResources().getColor(R.color.blue));
        lv.setFillColor(mContext.getResources().getColor(R.color.blue));
        lv.setStrokeWidth(mContext.getResources().getDimension(R.dimen.normal_width_2));
        lv.setStrokeRadius(mContext.getResources().getDimension(R.dimen.normal_width_5));
    }
}
