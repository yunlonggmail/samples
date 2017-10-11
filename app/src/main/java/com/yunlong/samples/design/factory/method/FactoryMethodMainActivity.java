package com.yunlong.samples.design.factory.method;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.LoadUMLActivity;
import com.yunlong.samples.design.main.config.LoadFileConfig;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/1.
 * 状态设计模式主页面
 */

public class FactoryMethodMainActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.factory.method.Main";
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
        titleBar.setTitle(R.string.nav_title_design_pattern_factory_method_main);
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
        addFactoryMethodUML();
        addFactoryMethodImpl();
    }

    /**
     * 添加工厂方法UML代码
     */
    protected void addFactoryMethodUML() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.nav_title_design_pattern_factory_method_uml);
        ylDesignPatternModel.desc = getString(R.string.nav_title_design_pattern_factory_method_uml_desc);
        ylDesignPatternModel.umlPath = "design_pattern/factory_method_uml.png";

        YLMain ylMain = new YLMain();
        ylMain.activityIntent = LoadUMLActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_factory_method_uml);
        ylMain.desc = getString(R.string.nav_title_design_pattern_factory_method_uml_desc);
        Bundle extras = new Bundle();
        extras.putParcelable(LoadFileConfig.DESIGN_PATTERN_FILE_INFO, ylDesignPatternModel);
        ylMain.extras = extras;
        dataList.add(ylMain);
    }

    /**
     * 添加工厂方法设计模式列表代码
     */
    protected void addFactoryMethodImpl() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = FactoryMethodImplMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_factory_method_impl_main);
        ylMain.desc = getString(R.string.nav_title_design_pattern_factory_method_impl_main_desc);
        dataList.add(ylMain);
    }

}
