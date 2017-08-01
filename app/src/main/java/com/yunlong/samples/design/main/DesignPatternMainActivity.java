package com.yunlong.samples.design.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.singleton.SingletonMainActivity;
import com.yunlong.samples.design.strategy.StrategyMainActivity;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/7/24.
 * 设计模式主页面
 */

public class DesignPatternMainActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.Main";

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
        return R.layout.a_design_pattern_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_main);
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
        addSingleton();
        addStrategy();
    }

    /**
     * 添加单例设计模式
     */
    protected void addSingleton() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = SingletonMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_singleton_main);
        ylMain.desc = getString(R.string.nav_title_design_pattern_singleton_main_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加策略模式
     */
    protected void addStrategy() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = StrategyMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_strategy_main);
        ylMain.desc = getString(R.string.nav_title_design_pattern_strategy_main);
        dataList.add(ylMain);
    }
}
