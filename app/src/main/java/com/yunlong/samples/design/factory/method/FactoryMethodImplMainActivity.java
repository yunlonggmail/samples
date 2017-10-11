package com.yunlong.samples.design.factory.method;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;
import com.yunlong.samples.model.YLMain;

/**
 * Created by shiyunlong on 2017/8/1.
 * 状态设计模式主页面
 */

public class FactoryMethodImplMainActivity extends BaseLoadFileListActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.factory.method.ImplMain";

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_factory_method_impl_main);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    protected void setData() {
        addTest();
        addFactory();
        addConcreteFactoryA();
        addConcreteFactoryB();
        addProduct();
        addConcreteProductA();
        addConcreteProductB();
    }

    /**
     * 添加测试界面
     */
    private void addTest() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = FactoryMethodImplTestActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_factory_method_impl_test);
        ylMain.desc = getString(R.string.nav_title_design_pattern_factory_method_impl_test_desc);
        addData(ylMain);
    }

    /**
     * 添加工厂角色类
     */
    private void addFactory() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_method_impl_factory);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_method_impl_factory_desc);
        ylDesignPatternModel.assertPath = "design/factory/method/factory/Factory.java";
        addData(ylDesignPatternModel);
    }


    /**
     * 添加具体产品A的工厂类
     */
    private void addConcreteFactoryA() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_method_impl_factory_concrete_a);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_method_impl_factory_concrete_a_desc);
        ylDesignPatternModel.assertPath = "design/factory/method/factory/ConcreteFactoryA.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品B的工厂类
     */
    private void addConcreteFactoryB() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_method_impl_factory_concrete_a);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_method_impl_factory_concrete_a_desc);
        ylDesignPatternModel.assertPath = "design/factory/method/factory/ConcreteFactoryB.java";
        addData(ylDesignPatternModel);
    }


    /**
     * 添加抽象产品类
     */
    private void addProduct() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_method_impl_product);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_method_impl_product_desc);
        ylDesignPatternModel.assertPath = "design/factory/method/product/Product.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品类A
     */
    private void addConcreteProductA() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_method_impl_concrete_product_a);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_method_impl_concrete_product_a_desc);
        ylDesignPatternModel.assertPath = "design/factory/method/product/ConcreteProductA.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 添加具体产品类B
     */
    private void addConcreteProductB() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_factory_method_impl_concrete_product_b);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_factory_method_impl_concrete_product_b_desc);
        ylDesignPatternModel.assertPath = "design/factory/method/product/ConcreteProductB.java";
        addData(ylDesignPatternModel);
    }


}
