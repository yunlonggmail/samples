package com.yunlong.lib.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by shiyunlong on 2017/2/27.
 * 硬件工具类
 */

public class HardwareUtils {
    /**
     * 检查摄像机硬件
     *
     * @param context
     * @return
     */
    public static boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}
