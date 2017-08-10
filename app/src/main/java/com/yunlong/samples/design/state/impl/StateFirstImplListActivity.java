package com.yunlong.samples.design.state.impl;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;

/**
 * Created by shiyunlong on 2017/7/24.
 * 设置数据
 */

public class StateFirstImplListActivity extends BaseLoadFileListActivity {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.state.StateFirstImplList";

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_state_first_impl_list);
        super.initTitleBar();
    }

    @Override
    protected void setData() {
        addContext();
        addState();
        addConcreteStateA();
        addConcreteStateB();
    }

    /**
     * 加载环境类
     */
    private void addContext() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_first_impl_context);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_first_impl_context_desc);
        ylDesignPatternModel.assertPath = "design/state/first/TV.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载状态类
     */
    private void addState() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_first_impl_state);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_first_impl_state_desc);
        ylDesignPatternModel.assertPath = "design/state/first/ITVState.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体实现类A
     */
    private void addConcreteStateA() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_first_impl_state_tv_power_on_state);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_first_impl_state_tv_power_on_state_desc);
        ylDesignPatternModel.assertPath = "design/state/first/TVPowerOnState.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体实现类B
     */
    private void addConcreteStateB() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_first_impl_state_tv_power_off_state);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_first_impl_state_tv_power_off_state_desc);
        ylDesignPatternModel.assertPath = "design/state/first/TVPowerOffState.java";
        addData(ylDesignPatternModel);
    }

}
