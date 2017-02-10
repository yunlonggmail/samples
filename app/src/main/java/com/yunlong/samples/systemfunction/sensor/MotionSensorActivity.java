package com.yunlong.samples.systemfunction.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yunlong.lib.base.BaseActivity;
import com.yunlong.lib.utils.LogUtils;
import com.yunlong.samples.R;

import butterknife.Bind;

/**
 * Created by shiyunlong on 2017/2/9.
 * 动作传感器界面
 */

public class MotionSensorActivity extends BaseActivity implements SensorEventListener, View.OnClickListener {

    /**
     * 该界面的隐式意图
     */
    public static final String INTENT_ACTION = "com.yunlong.samples.system.function.MotionSensor";
    /**
     * Sensor管理器
     */
    SensorManager sensorManager;
    /**
     * 加速度传感器
     */
    @Bind(R.id.tv_sensor_accelerometer)
    TextView tvSensorAccelerometer;

    /**
     * 近距离传感器
     */
    @Bind(R.id.tv_sensor_proximity)
    TextView tvSensorProximity;

    /**
     * 光照传感器
     */
    @Bind(R.id.tv_sensor_light)
    TextView tvSensorLight;

    @Bind(R.id.btn_log)
    Button btnLog;
    /**
     * 是否开始记录日志
     */
    private boolean startLog;

    /**
     * 记录重力加速度
     */
    private float[] gravity = new float[3];

    @Override
    protected int getResourceId() {
        return R.layout.a_system_function_sensor_motion_activity;
    }

    @Override
    protected void initTitleBar() {
        titleBar.setTitle(R.string.nav_title_system_function_motion_sensor);
        super.initTitleBar();
    }

    @Override
    protected void initView() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        btnLog.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                if (event.values.length > 2) {
                    gravity[0] = event.values[0];
                    gravity[1] = event.values[1];
                    gravity[2] = event.values[2];
                }
                break;
            case Sensor.TYPE_ACCELEROMETER:
                if (event.values.length > 2) {
                    float alpha = 0.8f;
                    gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
                    gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
                    gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

                    String sb = "加速度\n";
                    float x = event.values[0] - gravity[0];
                    sb += "X:";
                    sb += x;
                    sb += "\n";
                    float y = event.values[1] - gravity[1];
                    sb += "Y:";
                    sb += y;
                    sb += "\n";
                    float z = event.values[2] - gravity[2];
                    sb += "Z:";
                    sb += z;
                    tvSensorAccelerometer.setText(sb);
                    if (startLog)
                        LogUtils.D(MotionSensorActivity.this.getClass().getName(), sb);
                }
                break;
            case Sensor.TYPE_PROXIMITY:
                String distance = "物体距离屏幕的距离：\n";
                distance += event.values[0];
                distance += "CM";
                tvSensorProximity.setText(distance);
                break;
            case Sensor.TYPE_LIGHT:
                String light = "光照强度";
                light += event.values[0];
                light += "\n";
                light += SensorUtils.getSensorLightDesc(event.values[0]);
                tvSensorLight.setText(light);
                break;
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_log:
                startLog = !startLog;
                btnLog.setText(startLog ? R.string.common_stop_record_log : R.string.common_start_record_log);
                break;
        }
    }
}
