package com.yunlong.samples.design.factory.abstraction;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;
import com.yunlong.samples.model.YLMain;

/**
 * Created by shiyunlong on 2017/8/1.
 * 状态设计模式主页面
 */

public class AbstractFactoryImplMainActivity extends BaseLoadFileListActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.factory.abstraction.ImplMain";

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_factory_abstract_impl_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    protected void setData() {
        addTest();
        addFactory();
        addConcreteFactory1();
        addConcreteFactory2();
        addProductA();
        addConcreteProductA1();
        addConcreteProductA2();
        addProductB();
        addConcreteProductB1();
        addConcreteProductB2();
    }

    /**
     * 添加测试界面
     */
    private void addTest() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = AbstractFactoryImplTestActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_factory_abstract_impl_test);
        ylMain.desc = getString(R.string.nav_title_design_pattern_factory_abstract_impl_test);
        addData(ylMain);
    }

    /**
     * 添加工厂角色类
     */
    private void addFactory() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_method_impl_factory);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_method_impl_factory_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/factory/Factory.java";
        addData(ylDesignPatternModel);
    }


    /**
     * 添加生产第1类产品的工厂类
     */
    private void addConcreteFactory1() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_abstract_impl_factory_concrete_1);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_abstract_impl_factory_concrete_1_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/factory/ConcreteFactory1.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加生产第2类产品的工厂类
     */
    private void addConcreteFactory2() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_abstract_impl_factory_concrete_2);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_abstract_impl_factory_concrete_2_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/factory/ConcreteFactory2.java";
        addData(ylDesignPatternModel);
    }


    /**
     * 添加抽象产品类A
     */
    private void addProductA() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_abstract_impl_abstract_product_a);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_abstract_impl_abstract_product_a_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/product/AbstractProductA.java";
        addData(ylDesignPatternModel);
    }


    /**
     * 添加具体产品类A1
     */
    private void addConcreteProductA1() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_abstract_impl_concrete_product_a_1);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_abstract_impl_concrete_product_a_1_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/product/ConcreteProductA1.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品类A2
     */
    private void addConcreteProductA2() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_abstract_impl_concrete_product_a_2);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_abstract_impl_concrete_product_a_2_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/product/ConcreteProductA2.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加抽象产品类B
     */
    private void addProductB() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_abstract_impl_abstract_product_b);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_abstract_impl_abstract_product_b_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/product/AbstractProductB.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品类B1
     */
    private void addConcreteProductB1() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_abstract_impl_concrete_product_b_1);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_abstract_impl_concrete_product_b_1_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/product/ConcreteProductB1.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品类B2
     */
    private void addConcreteProductB2() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_abstract_impl_concrete_product_b_2);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_abstract_impl_concrete_product_b_2_desc);
        ylDesignPatternModel.assertPath = "design/factory/abstraction/product/ConcreteProductB2.java";
        addData(ylDesignPatternModel);
    }


}
