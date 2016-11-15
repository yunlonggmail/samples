package com.yunlong.lib.utils;

import android.util.Log;
import com.yunlong.base.BuildConfig;



/**
 * Created by shiyunlong on 15/9/28.
 */
public class LogUtils {
    /**
     * 是否是Debug模式
     */
    public static final boolean IS_DEBUG = BuildConfig.LOG_DEBUG;

    /**
     * Log输出错误信息
     *
     * @param tag：log输出的表情
     * @param message：输出消息
     */
    public static void V(String tag, String message) {
        if (IS_DEBUG) {
            Log.v(tag, message);
        }
    }

    /**
     * Log输出错误信息
     *
     * @param tag：log输出的表情
     * @param message：输出消息
     */
    public static void I(String tag, String message) {
        if (IS_DEBUG) {
            Log.i(tag, message);
        }
    }

    /**
     * Log输出错误信息
     *
     * @param tag：log输出的表情
     * @param message：输出消息
     */
    public static void D(String tag, String message) {
        if (IS_DEBUG) {
            Log.d(tag, message);
        }
    }

    /**
     * Log输出错误信息
     *
     * @param tag：log输出的表情
     * @param message：输出消息
     */
    public static void W(String tag, String message) {
        if (IS_DEBUG) {
            Log.w(tag, message);
        }
    }

    /**
     * Log输出错误信息
     *
     * @param tag：log输出的表情
     * @param message：输出消息
     */
    public static void E(String tag, String message) {
        if (IS_DEBUG) {
            Log.e(tag, message);
        }
    }

}
