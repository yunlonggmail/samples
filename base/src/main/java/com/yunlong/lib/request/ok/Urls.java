package com.yunlong.lib.request.ok;

/**
 * Created by shiyunlong on 2017/4/12.
 */

public class Urls {
    /**
     * HttpScheme
     */
    public static final String HTTP_SCHEME = "http";
    /**
     * HttpsScheme
     */
    public static final String HTTPS_SCHEME = "https";

    /**
     * 获取Scheme
     * @param isHttps：是否是API请求
     * @return
     */
    public static String getScheme(boolean isHttps){
        return isHttps?HTTPS_SCHEME:HTTP_SCHEME;
    }

    /**
     * BaseApi
     */
    public static final String BASE_API = "://api.dev.yuguo.cn";
}
