package com.yunlong.lib.request.ok;

import com.yunlong.lib.request.HttpRequestParams;


/**
 * Created by shiyunlong on 2017/4/12.
 */

public class GETRequest extends OKHttpRequest {


    public GETRequest(String path, HttpRequestParams params, HttpResponseListener httpResponseListener) {
        super(path, Method.GET, params, httpResponseListener);
    }
}
