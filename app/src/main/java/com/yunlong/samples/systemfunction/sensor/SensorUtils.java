package com.yunlong.samples.systemfunction.sensor;

import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Created by shiyunlong on 2017/2/9.
 * 传感器工具类
 */

public class SensorUtils {

    private static final String SENSOR_TYPE_DEFAULT = "其他传感器";

    private static final String SENSOR_TYPE_ACCELEROMETER_CN = "加速传感器";
    private static final String SENSOR_TYPE_MAGNETIC_FIELD_CN = "磁力传感器";
    private static final String SENSOR_TYPE_ORIENTATION_CN = "方向传感器(4.0之后被替代)";
    private static final String SENSOR_TYPE_GYROSCOPE_CN = "陀螺仪传感器";
    private static final String SENSOR_TYPE_LIGHT_CN = "光线传感器";
    private static final String SENSOR_TYPE_PRESSURE_CN = "压力传感器";
    private static final String SENSOR_TYPE_TEMPERATURE_CN = "温度传感器传感器(4.0之后被替代)";
    private static final String SENSOR_TYPE_PROXIMITY_CN = "近距离传感器";
    private static final String SENSOR_TYPE_GRAVITY_CN = "重力传感器";
    private static final String SENSOR_TYPE_LINEAR_ACCELERATION_CN = "线性加速传感器";
    private static final String SENSOR_TYPE_ROTATION_VECTOR_CN = "旋转角度传感器";
    private static final String SENSOR_TYPE_RELATIVE_HUMIDITY_CN = "湿度传感器";
    private static final String SENSOR_TYPE_AMBIENT_TEMPERATURE_CN = "温度传感器(4.0新)";
    private static final String SENSOR_TYPE_MAGNETIC_FIELD_UNCALIBRATED_CN = "未校准磁力传感器";
    private static final String SENSOR_TYPE_GAME_ROTATION_VECTOR_CN = "游戏动作传感器";//不受电磁干扰影响
    private static final String SENSOR_TYPE_GYROSCOPE_UNCALIBRATED_CN = "未校准陀螺仪传感器";
    private static final String SENSOR_TYPE_SIGNIFICANT_MOTION_CN = "特殊动作触发传感器";
    private static final String SENSOR_TYPE_STEP_DETECTOR_CN = "步行检测传感器";
    private static final String SENSOR_TYPE_STEP_COUNTER_CN = "计步传感器";
    private static final String SENSOR_TYPE_GEOMAGNETIC_ROTATION_VECTOR_CN = "地磁旋转矢量传感器";//提供手机的旋转矢量，当手机处于休眠状态时，仍可以记录设备的方位
    private static final String SENSOR_TYPE_HEART_RATE_CN = "心率传感器";


    /**
     * 根据传感器类型获取传感器中文名称
     *
     * @param sensorType：传感器类型
     */
    public static String getSensorCNName(int sensorType) {
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                return SENSOR_TYPE_ACCELEROMETER_CN;
            case Sensor.TYPE_MAGNETIC_FIELD:
                return SENSOR_TYPE_MAGNETIC_FIELD_CN;
            case Sensor.TYPE_ORIENTATION:
                return SENSOR_TYPE_ORIENTATION_CN;
            case Sensor.TYPE_GYROSCOPE:
                return SENSOR_TYPE_GYROSCOPE_CN;
            case Sensor.TYPE_LIGHT:
                return SENSOR_TYPE_LIGHT_CN;
            case Sensor.TYPE_PRESSURE:
                return SENSOR_TYPE_PRESSURE_CN;
            case Sensor.TYPE_TEMPERATURE:
                return SENSOR_TYPE_TEMPERATURE_CN;
            case Sensor.TYPE_PROXIMITY:
                return SENSOR_TYPE_PROXIMITY_CN;
            case Sensor.TYPE_GRAVITY:
                return SENSOR_TYPE_GRAVITY_CN;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return SENSOR_TYPE_LINEAR_ACCELERATION_CN;
            case Sensor.TYPE_ROTATION_VECTOR:
                return SENSOR_TYPE_ROTATION_VECTOR_CN;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return SENSOR_TYPE_RELATIVE_HUMIDITY_CN;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return SENSOR_TYPE_AMBIENT_TEMPERATURE_CN;
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return SENSOR_TYPE_MAGNETIC_FIELD_UNCALIBRATED_CN;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return SENSOR_TYPE_GAME_ROTATION_VECTOR_CN;
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                return SENSOR_TYPE_GYROSCOPE_UNCALIBRATED_CN;
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return SENSOR_TYPE_SIGNIFICANT_MOTION_CN;
            case Sensor.TYPE_STEP_DETECTOR:
                return SENSOR_TYPE_STEP_DETECTOR_CN;
            case Sensor.TYPE_STEP_COUNTER:
                return SENSOR_TYPE_STEP_COUNTER_CN;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return SENSOR_TYPE_GEOMAGNETIC_ROTATION_VECTOR_CN;
            case Sensor.TYPE_HEART_RATE:
                return SENSOR_TYPE_HEART_RATE_CN;
            default:
                return SENSOR_TYPE_DEFAULT;
        }
    }


    /**
     * 根据传感器类型获取传感器中文名称
     *
     * @param sensorLight：光照强度
     */
    public static String getSensorLightDesc(float sensorLight) {
        if (sensorLight >= SensorManager.LIGHT_SUNLIGHT_MAX) {
            return "你在沙漠里吗？";
        } else if (sensorLight >= SensorManager.LIGHT_SUNLIGHT) {
            return "今天一定是万里无云。";
        } else if (sensorLight >= SensorManager.LIGHT_SHADE) {
            return "看天上有云！";
        } else if (sensorLight >= SensorManager.LIGHT_OVERCAST) {
            return "天气预报说：今天多云";
        } else if (sensorLight >= SensorManager.LIGHT_SUNRISE) {
            return "太阳出来了。";
        } else if (sensorLight >= SensorManager.LIGHT_CLOUDY) {
            return "多云?阴天吧！";
        } else if (sensorLight >= SensorManager.LIGHT_FULLMOON) {
            return "出月亮了";
        } else {
            return "卧槽！咋这么黑，你在哪？";
        }
    }


}
