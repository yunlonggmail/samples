package com.yunlong.samples.design.prototype;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;
import com.yunlong.samples.model.YLMain;

/**
 * Created by shiyunlong on 2017/8/1.
 * 原型设计模式实现主页面
 */

public class PrototypeImplMainActivity extends BaseLoadFileListActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.prototype.ImplMain";

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_builder_impl_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    protected void setData() {
        addTest();
        addPrototype();
        addConcretePrototype();
        addConcretePrototypePart();
    }

    /**
     * 添加测试界面
     */
    private void addTest() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = PrototypeImplTestActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_prototype_impl_test);
        ylMain.desc = getString(R.string.nav_title_design_pattern_prototype_impl_test_desc);
        addData(ylMain);
    }

    /**
     * 添加原型类
     */
    private void addPrototype() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_prototype_impl_prototype);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_prototype_impl_prototype);
        ylDesignPatternModel.assertPath = "design/prototype/Prototype.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体原型类
     */
    private void addConcretePrototype() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_prototype_impl_concrete_prototype);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_prototype_impl_concrete_prototype);
        ylDesignPatternModel.assertPath = "design/prototype/ConcretePrototype.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加原型Part类
     */
    private void addConcretePrototypePart() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_prototype_impl_concrete_prototype_part);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_prototype_impl_concrete_prototype_part);
        ylDesignPatternModel.assertPath = "design/prototype/PrototypePart.java";
        addData(ylDesignPatternModel);
    }

}
