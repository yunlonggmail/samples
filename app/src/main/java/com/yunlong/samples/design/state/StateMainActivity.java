package com.yunlong.samples.design.state;

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

public class StateMainActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.state.Main";
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
        return R.layout.a_design_pattern_state_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_state_main);
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
        addStrategyUML();
        addStrategyImpl();
    }

    /**
     * 添加UML代码
     */
    protected void addStrategyUML() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.nav_title_design_pattern_state_uml);
        ylDesignPatternModel.desc = getString(R.string.nav_title_design_pattern_state_uml_desc);
        ylDesignPatternModel.umlPath = "design_pattern/state_uml.png";

        YLMain ylMain = new YLMain();
        ylMain.activityIntent = LoadUMLActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_state_uml);
        ylMain.desc = getString(R.string.nav_title_design_pattern_state_uml_desc);
        Bundle extras = new Bundle();
        extras.putParcelable(LoadFileConfig.DESIGN_PATTERN_FILE_INFO, ylDesignPatternModel);
        ylMain.extras = extras;
        dataList.add(ylMain);
    }

    /**
     * 添加策略模式列表代码
     */
    protected void addStrategyImpl() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = StateImplMainActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_state_impl_main);
        ylMain.desc = getString(R.string.nav_title_design_pattern_state_impl_main_desc);
        dataList.add(ylMain);
    }

}
