package com.yunlong.lib.utils;

import android.text.TextUtils;

/**
 * Created by shiyunlong on 2017/2/17.
 * 字符串工具类
 */

public class StringsUtils {

    /**
     * 针对api返回字符串是否为"空"
     *
     * @param str：如参
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str) || "null".equals(str.toString());
    }

}
