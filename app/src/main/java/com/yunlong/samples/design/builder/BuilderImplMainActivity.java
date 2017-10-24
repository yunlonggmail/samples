package com.yunlong.samples.design.builder;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;
import com.yunlong.samples.model.YLMain;

/**
 * Created by shiyunlong on 2017/8/1.
 * 状态设计模式主页面
 */

public class BuilderImplMainActivity extends BaseLoadFileListActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.builder.ImplMain";

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
        addBuilder();
        addConcreteBuilder();
        addProduct();
        addDirector();
    }

    /**
     * 添加测试界面
     */
    private void addTest() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = BuilderImplTestActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_builder_impl_test);
        ylMain.desc = getString(R.string.nav_title_design_pattern_builder_impl_test_desc);
        addData(ylMain);
    }

    /**
     * 添加创建者类
     */
    private void addBuilder() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_builder_impl_builder);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_builder_impl_builder_desc);
        ylDesignPatternModel.assertPath = "design/builder/Builder.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体创建者类
     */
    private void addConcreteBuilder() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_builder_impl_concrete_builder);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_builder_impl_concrete_builder_desc);
        ylDesignPatternModel.assertPath = "design/builder/ConcreteBuilder.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品类
     */
    private void addProduct() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_builder_impl_product);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_builder_impl_product_desc);
        ylDesignPatternModel.assertPath = "design/builder/Product.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加指挥者类
     */
    private void addDirector() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_builder_impl_concrete_director);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_builder_impl_concrete_director);
        ylDesignPatternModel.assertPath = "design/builder/Director.java";
        addData(ylDesignPatternModel);
    }


}
