package com.yunlong.lib.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 展示
 */
public class DisplayUtils {
    private static DisplayMetrics sDisplayMetrics;

    private static final float ROUND_DIFFERENCE = 0.5f;

    /**
     * 初始化操作
     *
     * @param context context
     */
    public static void init(Context context) {
        sDisplayMetrics = context.getResources().getDisplayMetrics();
//        checkScreen();
    }


    /**
     * 获取屏幕宽度 单位：像素
     *
     * @return 屏幕宽度
     */
    public static int getWidthPixels() {
        return sDisplayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度 单位：像素
     *
     * @return 屏幕高度
     */
    public static int getHeightPixels() {
        return sDisplayMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度 单位：像素
     *
     * @return 屏幕宽度
     */
    public static float getDensity() {
        return sDisplayMetrics.density;
    }

    /**
     * dp 转 px
     *
     * @param dp dp值
     * @return 转换后的像素值
     */
    public static int dp2px(int dp) {
        return (int) (dp * sDisplayMetrics.density + ROUND_DIFFERENCE);
    }

    /**
     * dp 转 px
     *
     * @param dp dp值
     * @return 转换后的像素值
     */
    public static float dp2px(float dp) {
        return dp * sDisplayMetrics.density + ROUND_DIFFERENCE;
    }

    /**
     * 根据手机的分辨率从dp 的单位 转成为px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + ROUND_DIFFERENCE);
    }

    /**
     * 根据手机的分辨率从px(像素) 的单位 转成为dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + ROUND_DIFFERENCE);
    }

    /**
     * px 转 dp
     *
     * @param px px值
     * @return 转换后的dp值
     */
    public static int px2dp(int px) {
        return (int) (px / sDisplayMetrics.density + ROUND_DIFFERENCE);
    }

    public static int getScreenHeight() {
        return sDisplayMetrics.heightPixels;
    }

    @SuppressWarnings("deprecation")
    public static int getScreenWidth() {
        return sDisplayMetrics.widthPixels;
    }

    /**
     * px转sp
     *
     * @param px px
     * @return sp
     */
    public static float px2sp(float px) {
        return px / sDisplayMetrics.scaledDensity + ROUND_DIFFERENCE;
    }

    public static float sp2px(float sp) {
        return sp * sDisplayMetrics.scaledDensity + ROUND_DIFFERENCE;
    }
}