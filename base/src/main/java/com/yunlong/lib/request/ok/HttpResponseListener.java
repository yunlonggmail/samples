package com.yunlong.lib.request.ok;

import com.yunlong.lib.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by shiyunlong on 2017/4/12.
 */

public class HttpResponseListener implements Callback {

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {


        LogUtils.D("Call","call："+call.toString());

    }
}
