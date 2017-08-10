package com.yunlong.samples.design.state.impl;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;

/**
 * Created by shiyunlong on 2017/7/24.
 * 设置数据
 */

public class StateSecondImplListActivity extends BaseLoadFileListActivity {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.state.StateSecondImplList";

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_state_second_impl_list);
        super.initTitleBar();
    }

    @Override
    protected void setData() {
        addContext();
        addState();
        addConcreteStateA();
        addConcreteStateB();
        addConcreteStateC();
    }

    /**
     * 加载环境类
     */
    private void addContext() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_second_impl_context);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_second_impl_context_desc);
        ylDesignPatternModel.assertPath = "design/state/second/Magnifier.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载状态类
     */
    private void addState() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_second_impl_state);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_second_impl_state_desc);
        ylDesignPatternModel.assertPath = "design/state/second/IMagnifierState.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体实现类A
     */
    private void addConcreteStateA() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_second_impl_state_normal_state);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_second_impl_state_normal_state_desc);
        ylDesignPatternModel.assertPath = "design/state/second/MagnifierNormalState.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体实现类B
     */
    private void addConcreteStateB() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_second_impl_state_twice_state);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_second_impl_state_twice_state_desc);
        ylDesignPatternModel.assertPath = "design/state/second/MagnifierTwiceState.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体实现类C
     */
    private void addConcreteStateC() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_state_second_impl_state_4_times_state);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_state_second_impl_state_4_times_state_desc);
        ylDesignPatternModel.assertPath = "design/state/second/Magnifier4TimesState.java";
        addData(ylDesignPatternModel);
    }

}
