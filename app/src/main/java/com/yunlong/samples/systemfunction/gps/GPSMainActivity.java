package com.yunlong.samples.systemfunction.gps;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/24.
 * GPS主界面
 */

public class GPSMainActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.GPSMain";

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
        return R.layout.a_system_function_gps_main;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_gps_main);
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

    /**
     * 设置数据
     */
    private void setData() {
        addGPSListMain();
        addGPSLocationInfoMain();
    }

    /**
     * GPS列表主界面
     */
    protected void addGPSListMain() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = GPSLocationProviderActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_system_function_gps_location_provider);
        ylMain.desc = getString(R.string.nav_title_system_function_gps_location_provider_desc);
        dataList.add(ylMain);
    }

    /**
     * GPS信息界面
     */
    protected void addGPSLocationInfoMain() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = GPSLocationInfoActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_system_function_gps_location_info);
        ylMain.desc = getString(R.string.nav_title_system_function_gps_location_info_desc);
        dataList.add(ylMain);
    }
}
