package com.yunlong.lib.request.ok;

import android.net.Uri;

import com.yunlong.lib.request.HttpRequestParams;
import com.yunlong.lib.utils.StringsUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by shiyunlong on 2017/4/12.
 * OKHttp网络请求
 */

public class OKHttpRequest {
    /**
     * OkHttp请求
     */
    private OkHttpClient mOKHttpClient;
    /**
     * Post请求的类型
     */
    protected int mPostType;
    /**
     * 方法
     */
    protected int mMethod;

    public OKHttpRequest(String path, int method, HttpRequestParams params, Callback callback) {
        mOKHttpClient = new OkHttpClient();
        Request request = build(method, path, params);
        mOKHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 检查Path
     *
     * @return：检查后的路径
     */
    private String checkPath(String path) {
        if (StringsUtils.isEmpty(path)) {
            return null;
        } else if (null == Uri.parse(path).getScheme()) {
            return Urls.HTTP_SCHEME + Urls.BASE_API + path;
        } else {
            return path;
        }
    }

    /**
     * 检查方法
     */
    private Request build(int method, String path, HttpRequestParams params) {
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.headers(getHeaders());
        switch (method) {
            case Method.GET:
                requestBuilder.url(checkPath(path) + params.getString());
                requestBuilder.get();
                break;
            case Method.POST:
                requestBuilder.url(path);
                requestBuilder.post(PostUtil.postJson(params));
                break;
        }
        return requestBuilder.build();
    }

    /**
     * 获取请求头
     *
     * @return
     */
    private Headers getHeaders() {
        Map<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("User-Agent", "Android-map-V2.9.4");
        mapHeaders.put("Accept-Encoding", "gzip");
        return Headers.of(mapHeaders);
    }

    /**
     * 请求方法
     */
    public class Method {
        public static final int GET = 0;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int DELETE = 3;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int TRACE = 6;
        public static final int PATCH = 7;
    }

}
