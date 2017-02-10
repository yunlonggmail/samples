package com.yunlong.samples.systemfunction.sensor.info;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.samples.R;

import java.util.List;

import butterknife.Bind;

/**
 * 手机上所有传感器界面
 * Created by shiyunlong on 2017/2/7.
 */

public class SensorInfoActivity extends BaseActivity {
    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.SensorInfo";
    /**
     * 数据信息
     */
    @Bind(R.id.rv_list)
    RecyclerView rvData;
    /**
     * 适配器
     */
    SensorInfoAdapter sensorInfoAdapter;
    /**
     * 适配器列表
     */
    List<Sensor> sensorList;

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_sensor_info;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_sensor_info);
        super.initTitleBar();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        sensorInfoAdapter = new SensorInfoAdapter(mContext, sensorList);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(sensorInfoAdapter);
    }
}
