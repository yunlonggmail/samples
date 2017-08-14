package com.yunlong.samples.design.factory.simple;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;
import com.yunlong.samples.model.YLMain;

/**
 * Created by shiyunlong on 2017/8/1.
 * 状态设计模式主页面
 */

public class SimpleFactoryImplMainActivity extends BaseLoadFileListActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.factory.simple.ImplMain";

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_factory_simple_impl_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    protected void setData() {
        addTest();
        addFactory();
        addProduct();
        addConcreteProductA();
        addConcreteProductB();
    }

    /**
     * 添加测试界面
     */
    private void addTest() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = SimpleFactoryImplTestActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_factory_simple_impl_test);
        ylMain.desc = getString(R.string.nav_title_design_pattern_factory_simple_impl_test_desc);
        addData(ylMain);
    }

    /**
     * 添加工厂角色类
     */
    private void addFactory() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_simple_impl_factory);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_simple_impl_factory_desc);
        ylDesignPatternModel.assertPath = "design/factory/simple/SimpleFactory.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加抽象产品类
     */
    private void addProduct() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_simple_impl_product);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_simple_impl_product_desc);
        ylDesignPatternModel.assertPath = "design/factory/simple/SimpleProduct.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品类A
     */
    private void addConcreteProductA() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_simple_impl_concrete_product_a);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_simple_impl_concrete_product_a_desc);
        ylDesignPatternModel.assertPath = "design/factory/simple/ConcreteSimpleProductA.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品类B
     */
    private void addConcreteProductB() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_simple_impl_concrete_product_b);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_simple_impl_concrete_product_b_desc);
        ylDesignPatternModel.assertPath = "design/factory/simple/ConcreteSimpleProductB.java";
        addData(ylDesignPatternModel);
    }


}
