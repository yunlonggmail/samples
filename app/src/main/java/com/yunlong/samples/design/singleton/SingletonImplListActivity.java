package com.yunlong.samples.design.singleton;

import com.yunlong.samples.R;
import com.yunlong.samples.design.main.activity.BaseLoadFileListActivity;
import com.yunlong.samples.design.main.model.YLDesignPatternModel;

/**
 * Created by shiyunlong on 2017/7/24.
 * 设置数据
 */

public class SingletonImplListActivity extends BaseLoadFileListActivity {
    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.singleton.SingletonImplList";

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_singleton_impl_list);
        super.initTitleBar();
    }

    @Override
    protected void setData() {
        addSingletonDCL();
        addSingletonEnum();
        addSingletonHungry();
        addSingletonLazy();
        addSingletonSIC();
    }

    /**
     * 加载DCL实现的单例模式
     */
    private void addSingletonDCL() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_singleton_dcl_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_singleton_dcl_title_desc);
        ylDesignPatternModel.path = "design/singleton/SingletonDCL.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载Enum实现的单例模式
     */
    private void addSingletonEnum() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_singleton_enum_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_singleton_enum_title_desc);
        ylDesignPatternModel.path = "design/singleton/SingletonEnum.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载Hungry实现的单例模式
     */
    private void addSingletonHungry() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_singleton_hungry_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_singleton_hungry_title_desc);
        ylDesignPatternModel.path = "design/singleton/SingletonHungry.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载Lazy实现的单例模式
     */
    private void addSingletonLazy() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_singleton_lazy_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_singleton_lazy_title_desc);
        ylDesignPatternModel.path = "design/singleton/SingletonLazy.java";
        addData(ylDesignPatternModel);
    }

    /**
     * 加载SIC（Static Inner Class）实现的单例模式
     */
    private void addSingletonSIC() {
        YLDesignPatternModel ylDesignPatternModel = new YLDesignPatternModel();
        ylDesignPatternModel.title = getString(R.string.a_design_pattern_singleton_sic_title);
        ylDesignPatternModel.desc = getString(R.string.a_design_pattern_singleton_sic_title_desc);
        ylDesignPatternModel.path = "design/singleton/SingletonSIC.java";
        addData(ylDesignPatternModel);
    }
}
