package com.yunlong.samples.design.strategy;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;

/**
 * Created by shiyunlong on 2017/7/24.
 * 设置数据
 */

public class StrategyImplListActivity extends BaseLoadFileListActivity {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.strategy.StrategyImplList";

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_strategy_impl_list);
        super.initTitleBar();
    }

    @Override
    protected void setData() {
        addTest();
        addStrategy();
        addConcreteStrategyA();
        addConcreteStrategyB();
        addStrategyContext();
        addConcreteStrategyContextA();
        addConcreteStrategyContextB();
    }

    /**
     * 加载测试代码
     */
    private void addTest() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_strategy_test_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_strategy_test_title_desc);
        ylDesignPatternModel.assertPath = "design/strategy/Test.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载抽象策略代码
     */
    private void addStrategy() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_strategy_strategy_abstract_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_strategy_strategy_abstract_title_desc);
        ylDesignPatternModel.assertPath = "design/strategy/Strategy.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体策略A代码
     */
    private void addConcreteStrategyA() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_strategy_strategy_concrete_a_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_strategy_strategy_concrete_a_title_desc);
        ylDesignPatternModel.assertPath = "design/strategy/ConcreteStrategyA.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体策略B代码
     */
    private void addConcreteStrategyB() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_strategy_strategy_concrete_b_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_strategy_strategy_concrete_b_title_desc);
        ylDesignPatternModel.assertPath = "design/strategy/ConcreteStrategyB.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载环境类代码
     */
    private void addStrategyContext() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_strategy_strategy_context_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_strategy_strategy_context_title_desc);
        ylDesignPatternModel.assertPath = "design/strategy/StrategyContext.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体环境类A代码
     */
    private void addConcreteStrategyContextA() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_strategy_strategy_context_a_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_strategy_strategy_context_a_title_desc);
        ylDesignPatternModel.assertPath = "design/strategy/StrategyContextA.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载具体环境类B代码
     */
    private void addConcreteStrategyContextB() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_strategy_strategy_context_b_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_strategy_strategy_context_b_title);
        ylDesignPatternModel.assertPath = "design/strategy/StrategyContextA.java";
        addData(ylDesignPatternModel);
    }
}
