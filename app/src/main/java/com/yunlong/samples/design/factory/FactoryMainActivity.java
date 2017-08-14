package com.yunlong.samples.design.factory;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.factory.abstraction.AbstractFactoryMainActivity;
import com.yunlong.samples.design.factory.method.FactoryMethodMainActivity;
import com.yunlong.samples.design.factory.simple.SimpleFactoryMainActivity;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/1.
 * 状态设计模式主页面
 */

public class FactoryMainActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.factory.Main";
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
        titleBar.setTitle(R.string.nav_title_design_pattern_factory_main);
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
        addSimpleFactory();
        addFactoryMethod();
        addAbstractFactory();
    }


    /**
     * 添加简单工厂模式列表代码
     */
    protected void addSimpleFactory() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = SimpleFactoryMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_factory_simple_main);
        ylMain.desc = getString(R.string.nav_title_design_pattern_factory_simple_main_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加工厂方法模式列表代码
     */
    protected void addFactoryMethod() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = FactoryMethodMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_factory_method_main);
        ylMain.desc = getString(R.string.nav_title_design_pattern_factory_method_main_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加抽象工厂模式列表代码
     */
    protected void addAbstractFactory() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = AbstractFactoryMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_factory_abstract_main);
        ylMain.desc = getString(R.string.nav_title_design_pattern_factory_abstract_main_desc);
        dataList.add(ylMain);
    }

}
