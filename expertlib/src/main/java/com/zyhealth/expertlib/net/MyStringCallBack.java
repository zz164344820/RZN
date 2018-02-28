package com.zyhealth.expertlib.net;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by zz on 2017/4/7.
 */

public class MyStringCallBack extends StringCallback {

    public MyStringCallBack(HttpRequestListener requestListener) {
    }



    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(String response, int id) {

    }


    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
    }

    @Override
    public void inProgress(float progress, long total, int id) {
        super.inProgress(progress, total, id);
    }
}
