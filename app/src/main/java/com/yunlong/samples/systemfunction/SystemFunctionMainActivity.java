package com.yunlong.samples.systemfunction;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;
import com.yunlong.samples.systemfunction.multipoint.MultipointActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 系统功能主界面
 * Created by shiyunlong on 2017/2/7.
 */

public class SystemFunctionMainActivity extends BaseActivity {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.SystemFunctionMain";
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
        return R.layout.a_system_function_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_main);
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
        addLRMenuMain();
    }

    /**
     * 本地服务(当前应用中使用)
     */
    protected void addLRMenuMain() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = MultipointActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_system_function_multipoint);
        ylMain.desc = getString(R.string.nav_title_system_function_multipoint_desc);
        dataList.add(ylMain);
    }

}
