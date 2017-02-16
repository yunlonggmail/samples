package com.yunlong.samples.systemfunction.sensor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;
import com.yunlong.samples.main.MainDataAdapter;
import com.yunlong.samples.model.YLMain;
import com.yunlong.samples.systemfunction.sensor.info.SensorInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 传感器主界面
 * Created by shiyunlong on 2017/2/7.
 */

public class SensorMainActivity extends BaseActivity {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.SensorMain";

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
        return R.layout.a_system_function_sensor_main;
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
        addSensorInfo();
        addMotionSensor();
    }

    /**
     * 传感器信息界面
     */
    protected void addSensorInfo() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = SensorInfoActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_system_function_sensor_info);
        ylMain.desc = getString(R.string.nav_title_system_function_sensor_info_desc);
        dataList.add(ylMain);
    }

    /**
     * 传感器信息界面
     */
    protected void addMotionSensor() {
        YLMain ylMain = new YLMain();
        ylMain.activityIntent = MotionSensorActivity.INTENT_ACTION;
        ylMain.name = getString(R.string.nav_title_system_function_motion_sensor);
        ylMain.desc = getString(R.string.nav_title_system_function_motion_sensor_desc);
        dataList.add(ylMain);
    }
}