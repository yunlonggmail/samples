package com.yunlong.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by shiyunlong on 2017/3/3.
 * SharePreference工具类
 */

public class SPUtils {
    /**
     * SharePreferenceUtils
     */
    private static SPUtils spUtils;
    /**
     * 默认的XML名称
     */
    private static final String DEFAULT_XML_NAME = "yl_config";

    /**
     * 获取SPUtils
     *
     * @return
     */
    public static SPUtils getInstance() {
        if (spUtils == null) {
            spUtils = new SPUtils();
        }
        return spUtils;
    }

    private SPUtils() {
    }

    /**
     * 保存字符串到SP-XML文件中
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveStrPre(Context context, String name, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 保存字符串到默认SP-XML文件中
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveDefaultStrPre(Context context, String key, String value) {
        saveStrPre(context, DEFAULT_XML_NAME, key, value);
    }

    /**
     * 获取SP-XML中对应的字符串
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public String getStrPre(Context context, String name, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * 获取默认SP-XML中对应的字符串
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param defaultValue：默认保存的值
     * @return :KEY对应的Value
     */
    public String getDefaultStrPre(Context context, String key, String defaultValue) {
        return getStrPre(context, DEFAULT_XML_NAME, key, defaultValue);
    }

    /**
     * 保存字符串到SP-XML文件中
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param values：保存的值的集合
     */
    public void saveStrSetPre(Context context, String name, String key, Set<String> values) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, values);
        editor.apply();
    }

    /**
     * 保存字符串到默认SP-XML文件中
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param values：保存的值的集合
     */
    public void saveDefaultStrPre(Context context, String key, Set<String> values) {
        saveStrSetPre(context, DEFAULT_XML_NAME, key, values);
    }

    /**
     * 获取SP-XML中对应的字符串
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param defaultValues：默认的值的集合
     * @return :KEY对应的Value
     */
    public Set<String> getStrSetPre(Context context, String name, String key, Set<String> defaultValues) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(key, defaultValues);
    }

    /**
     * 获取默认SP-XML中对应的字符串
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param defaultValues：默认的值的集合
     * @return :KEY对应的Value
     */
    public Set<String> getDefaultStrPre(Context context, String key, Set<String> defaultValues) {
        return getStrSetPre(context, DEFAULT_XML_NAME, key, defaultValues);
    }

    /**
     * 保存boolean值到SP-XML文件中
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveBooleanPre(Context context, String name, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 保存boolean值到默认SP-XML文件中
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveDefaultBooleanPre(Context context, String key, boolean value) {
        saveBooleanPre(context, DEFAULT_XML_NAME, key, value);
    }

    /**
     * 获取SP-XML中对应的boolean值
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public boolean getBooleanPre(Context context, String name, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * 获取默认SP-XML中对应的boolean值
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public boolean getDefaultBooleanPre(Context context, String key, boolean defaultValue) {
        return getBooleanPre(context, DEFAULT_XML_NAME, key, defaultValue);
    }

    /**
     * 保存float值到SP-XML文件中
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveFloatPre(Context context, String name, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * 保存float值到默认SP-XML文件中
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveDefaultFloatPre(Context context, String key, float value) {
        saveFloatPre(context, DEFAULT_XML_NAME, key, value);
    }

    /**
     * 获取SP-XML中对应的float值
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public float getFloatPre(Context context, String name, String key, float defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * 获取默认SP-XML中对应的float值
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public float getDefaultFloatPre(Context context, String key, float defaultValue) {
        return getFloatPre(context, DEFAULT_XML_NAME, key, defaultValue);
    }

    /**
     * 保存int值到SP-XML文件中
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveIntPre(Context context, String name, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 保存int值到默认SP-XML文件中
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveDefaultIntPre(Context context, String key, int value) {
        saveIntPre(context, DEFAULT_XML_NAME, key, value);
    }

    /**
     * 获取SP-XML中对应的int值
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public int getIntPre(Context context, String name, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * 获取默认SP-XML中对应的int值
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public int getDefaultIntPre(Context context, String key, int defaultValue) {
        return getIntPre(context, DEFAULT_XML_NAME, key, defaultValue);
    }

    /**
     * 保存long值到SP-XML文件中
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveLongPre(Context context, String name, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 保存long值到默认SP-XML文件中
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param value：保存的值
     */
    public void saveDefaultLongPre(Context context, String key, long value) {
        saveLongPre(context, DEFAULT_XML_NAME, key, value);
    }

    /**
     * 获取SP-XML中对应的long值
     *
     * @param context：上下文
     * @param name：名称，XML文件的名称
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public long getLongPre(Context context, String name, String key, long defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * 获取默认SP-XML中对应的long值
     *
     * @param context：上下文
     * @param key：KEY，关键字
     * @param defaultValue：默认值
     * @return :KEY对应的Value
     */
    public long getDefaultLongPre(Context context, String key, long defaultValue) {
        return getLongPre(context, DEFAULT_XML_NAME, key, defaultValue);
    }
}

