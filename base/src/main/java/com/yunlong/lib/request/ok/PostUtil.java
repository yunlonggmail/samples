package com.yunlong.lib.request.ok;

import com.google.gson.Gson;
import com.yunlong.lib.request.HttpRequestParams;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by shiyunlong on 2017/4/12.
 */

public class PostUtil {
    /**
     * 传输类型Json
     */
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    /**
     * 传输类型Json
     */
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");

    /**
     * 转换为Json
     * @param params
     * @return
     */
    public static RequestBody postJson(HttpRequestParams params) {
        return RequestBody.create(MEDIA_TYPE_JSON, new Gson().toJson(params.getQueryObject()));
    }


}
