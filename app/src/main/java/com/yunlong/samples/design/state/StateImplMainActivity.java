package com.yunlong.samples.design.state;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.design.state.impl.StateFirstImplListActivity;
import com.yunlong.samples.design.state.impl.StateImplTestActivity;
import com.yunlong.samples.design.state.impl.StateSecondImplListActivity;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/8/1.
 * 状态设计模式主页面
 */

public class StateImplMainActivity extends BaseActivity {

    /**
     * 隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.design.pattern.state.StateImplMain";
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
        return R.layout.a_design_pattern_state_impl_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_design_pattern_state_impl_main);
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
        addStateImplTest();
        addStateFirstImplList();
        addStateSecondImplList();
    }

    /**
     * 添加策略模式列表代码
     */
    protected void addStateImplTest() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = StateImplTestActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_state_impl_test);
        ylMain.desc = getString(R.string.nav_title_design_pattern_state_impl_test_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加策略模式列表代码
     */
    protected void addStateFirstImplList() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = StateFirstImplListActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_state_first_impl_list);
        ylMain.desc = getString(R.string.nav_title_design_pattern_state_first_impl_list_desc);
        dataList.add(ylMain);
    }

    /**
     * 添加策略模式列表代码
     */
    protected void addStateSecondImplList() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = StateSecondImplListActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_design_pattern_state_second_impl_list);
        ylMain.desc = getString(R.string.nav_title_design_pattern_state_second_impl_list_desc);
        dataList.add(ylMain);
    }


}
