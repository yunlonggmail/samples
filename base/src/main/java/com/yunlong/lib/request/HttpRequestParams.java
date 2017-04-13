package com.yunlong.lib.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by shiyunlong on 2017/4/12.
 * 请求参数
 */

public class HttpRequestParams {

    Map<String, Object> parameters = new HashMap<>();

    /**
     * 请求参数
     *
     * @param key：key
     * @param value：value
     * @return
     */
    public HttpRequestParams addParameter(String key, Object value) {
        parameters.put(key, value);
        return this;
    }

    /**
     * 获取Query值
     *
     * @return
     */
    public Map<String, String> getQuery() {
        Map<String, String> query = new HashMap<String, String>();
        for (String key : parameters.keySet()) {
            query.put(key, parameters.get(key).toString());
        }
        return query;
    }

    /**
     * 得到Params中的Object数据
     *
     * @return
     */
    public Map<String, Object> getQueryObject() {
        Map<String, Object> query = new HashMap<String, Object>();
        for (String key : parameters.keySet()) {
            query.put(key, parameters.get(key));
        }
        return query;
    }

    /**
     * 转换成字符串
     *
     * @return
     */
    public String getString() {
        StringBuilder encodedParams = new StringBuilder();
        Map<String, String> params = getQuery();
        String paramsEncoding = getParamsEncoding();
        try {
            if (params.size() > 0) {
                Iterator var5 = params.entrySet().iterator();
                encodedParams.append("?");
                while (var5.hasNext()) {
                    java.util.Map.Entry uee = (java.util.Map.Entry) var5.next();
                    encodedParams.append(URLEncoder.encode((String) uee.getKey(), paramsEncoding));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode((String) uee.getValue(), paramsEncoding));
                    if (var5.hasNext())
                        encodedParams.append('&');
                }

                return encodedParams.toString();
            } else {
                return "";
            }
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, var6);
        }
    }

    /**
     * 得到参数编码
     */
    private String getParamsEncoding() {
        return "UTF-8";
    }

}
