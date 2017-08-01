package com.yunlong.lib.utils;

import android.net.Uri;

/**
 * Created by shiyunlong on 2017/8/1.
 * Url相关处理
 */

public class UrlUtils {

    public static final String QI_NIU_DEFAULT_URL = "ospp4bm84.bkt.clouddn.com";

    /**
     * 将不含域名的地址处理成相应的URL
     *
     * @param url
     * @return
     */
    public static String convertUrl(String url) {
        if (StringsUtils.isEmpty(url)) {
            return "";
        } else if (null == Uri.parse(url).getScheme()) {
            return "http://" + QI_NIU_DEFAULT_URL + "/" + url;
        }
        return url;
    }
}
