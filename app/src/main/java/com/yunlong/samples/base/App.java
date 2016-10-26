package com.yunlong.samples.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.yunlong.samples.utils.DisplayUtils;
import com.yunlong.samples.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiyunlong on 2016/10/26.
 * Application
 */
public class App extends Application {
    /**
     * ActivityList
     */
    private List<Activity> activityList;

    /**
     * 应用
     */
    protected static App app;

    /**
     * 拆分DEX
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        LogUtils.D("Application", "onCreate");
        super.onCreate();
        initWithoutNetWork();
    }

    /**
     * 虚拟机上可能会执行该方法
     */
    @Override
    public void onTerminate() {
        LogUtils.D("Application", "onTerminate");
        super.onTerminate();
    }

    /**
     * 低内存
     */
    @Override
    public void onLowMemory() {
        LogUtils.D("Application", "onLowMemory");
        super.onLowMemory();
    }

    /**
     * 内存释放,内存回收
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        LogUtils.D("Application", "onLowMemory");
        super.onTrimMemory(level);
    }

    /**
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtils.D("Application", "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 初始化不带网络的
     */
    private void initWithoutNetWork() {
        app = this;
        initDisplayUtil();
    }

    /**
     * 初始化工具类型
     */
    private void initDisplayUtil() {
        DisplayUtils.init(this);
    }

    /**
     * 注册Activity
     */
    public void registerActivity(Activity activity) {
        if (activity == null)
            return;
        if (activityList == null)
            activityList = new ArrayList<>();
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    /**
     * 移除Activity
     */
    public void removeActivity(Activity activity) {
        if (activity == null || activityList == null)
            return;
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    /**
     * 清除
     */
    public void clear() {
        if (activityList == null)
            return;
        activityList.clear();
    }

    /**
     * 关闭
     */
    public void finish() {
        if (activityList == null)
            return;
        for (int i = 0; i < activityList.size(); ) {
            Activity activity = activityList.get(i);
            if (activity != null) {
                activityList.remove(i);
                activity.finish();
            }
        }
    }

    /**
     * 获取APp
     * @return
     */
    public static App getApplication() {
        return app;
    }
}
