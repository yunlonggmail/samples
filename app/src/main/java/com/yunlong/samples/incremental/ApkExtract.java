package com.yunlong.samples.incremental;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by shiyunlong on 2016/10/11.
 */
public class ApkExtract {
    /**
     * 获取当前APK的路径信息
     * @param context
     * @return
     */
    public static String extract(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo.sourceDir;
    }

}
