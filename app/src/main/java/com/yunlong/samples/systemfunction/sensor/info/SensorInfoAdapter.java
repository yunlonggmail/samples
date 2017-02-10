package com.yunlong.samples.systemfunction.sensor.info;

import android.content.Context;
import android.hardware.Sensor;
import android.view.View;
import android.widget.TextView;

import com.yunlong.lib.base.BaseAdapter;
import com.yunlong.samples.R;
import com.yunlong.samples.systemfunction.sensor.SensorUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 传感器信息界面
 * Created by shiyunlong on 2017/2/7.
 */

public class SensorInfoAdapter extends BaseAdapter<Sensor> {

    /**
     * 传感器名称
     */
    @Bind(R.id.tv_sensor_name)
    TextView tvSensorName;

    /**
     * 传感器类型
     */
    @Bind(R.id.tv_sensor_type)
    TextView tvSensorType;

    /**
     * 传感器描述
     */
    @Bind(R.id.tv_desc)
    TextView tvDesc;


    public SensorInfoAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    public int getResourceId(int viewType) {
        return R.layout.i_system_function_sensor_info;
    }

    @Override
    public void bindView(View itemView, int position) {
        ButterKnife.bind(this, itemView);
        Sensor sensor = getItem(position);
        if (sensor == null)
            return;
        //名称
        tvSensorName.setText(sensor.getName());

        int sensorTypeI = sensor.getType();
        String sensorType = sensorTypeI + "_" + SensorUtils.getSensorCNName(sensorTypeI);
        //传感器类型
        tvSensorType.setText(sensorType);

        StringBuilder sb = new StringBuilder();
        sb.append("开发商：");
        sb.append(sensor.getVendor());
        sb.append("\n");
        sb.append("版本：");
        sb.append(sensor.getVersion());
        sb.append("\n");
        sb.append("最大测量范围：");
        sb.append(sensor.getMaximumRange());
        sb.append("\n");
        sb.append("精度，分辨率：");
        sb.append(sensor.getResolution());
        sb.append("\n");
        sb.append("功耗：");
        sb.append(sensor.getPower());
        sb.append("\n");
        sb.append("最小延迟：");
        sb.append(sensor.getMinDelay());
        sb.append("\n");


        tvDesc.setText(sb.toString());
    }

    @Override
    public OnItemClickListener getOnItemClickListener() {
        return null;
    }
}
