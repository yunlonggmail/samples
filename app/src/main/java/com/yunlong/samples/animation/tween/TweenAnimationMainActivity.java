package com.yunlong.samples.animation.tween;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/3/15.
 * 补间动画主界面
 */

public class TweenAnimationMainActivity extends BaseActivity {


    public static final String INTENT_ACTION = "com.yunlong.samples.animation.TweenAnimationMain";

    /**
     * 数据
     */
    @Bind(R.id.rv_list)
    RecyclerView rvData;
    /**
     * 适配器
     */
    MainDataAdapter mainDataAdapter;
    /**
     * 数据集合
     */
    private List<YLMain> dataList = new ArrayList<>();

    @Override
    protected int getResourceId() {
        return R.layout.a_animation_tween_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_animation_tween_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setData();
        mainDataAdapter = new MainDataAdapter(mContext, dataList);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(mainDataAdapter);
    }


    protected void setData() {
        addTweenAnimationAll();
    }

    /**
     * 所有的补间动画
     */
    protected void addTweenAnimationAll() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = TweenAnimationAllActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_animation_tween_animation_all);
        ylMain.desc = getString(R.string.nav_title_animation_tween_animation_all_desc);
        dataList.add(ylMain);
    }
}
