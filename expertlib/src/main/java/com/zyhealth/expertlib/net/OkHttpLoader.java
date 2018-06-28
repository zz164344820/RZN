package com.zyhealth.expertlib.net;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zyhealth.expertlib.Constants;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.utils.MLog;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Created by Administrator on 2016-12-08.
 */
public class OkHttpLoader {
    public static Gson gson = new Gson();
    public static String BASEURL = "http://47.98.62.7/";//服务器的入口

    public static void changServer() {
        if ("production".equals(Constants.SERVER)) {
            BASEURL = "http://1724l9l212.iask.in/";
        } else if ("develop".equals(Constants.SERVER)) {
            BASEURL = "http://1724l9l212.iask.in/";
        }
    }


    public static void postJson(Context context, final String api, final Object obj, int id, final HttpRequestListener requestListener) {
        Map<String, String> map = new HashMap<>();
        map.put("SDKVersion", DeviceUtils.getSDKVersion() + "");
        map.put("phone_model", DeviceUtils.getModel());
        map.put("app_version", AppUtils.getAppVersionName());
        map.put("network_type", NetworkUtils.getNetworkType().name());
        map.put("device_type", "android");
        OkHttpUtils.postString()
                .url(BASEURL + api)
                .headers(map)
                .content(gson.toJson(obj))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new GenericsCallback<ResponseBean>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MLog.e("http", "请求的地址：" + BASEURL + api);
                        MLog.e("http", "上传的参数：" + gson.toJson(obj));
                        MLog.e("http", "错误信息：" + e.getMessage());
                        requestListener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onResponse(ResponseBean response, int id) {
                        MLog.e("http", "请求的地址：" + BASEURL + api);
                        MLog.e("http", "上传的参数：" + gson.toJson(obj));
                        MLog.e("http", "返回参数：" + gson.toJson(response));
                        requestListener.onSuccess(response);
                    }
                });
    }


    /**
     * @param context
     * @param api
     * @param obj
     * @param id
     * @param requestListener 本项目请求以表单形式提交
     */
    public static void postformJson(Context context, final String api, final Map<String, String> obj, int id, final HttpRequestListener requestListener) {

       /* Map<String, String> headers = new HashMap<>();
        headers.put("SDKVersion", DeviceUtils.getSDKVersion() + "");
        headers.put("phone_model", DeviceUtils.getModel());
        headers.put("app_version", AppUtils.getAppVersionName());
        headers.put("network_type", NetworkUtils.getNetworkType().name());
        headers.put("User-Agent", "android_userAgent");
        headers.put("mobilId", PhoneUtils.getIMEI());
*/

        OkHttpUtils.post()//
                .url(BASEURL + api )
                .params(obj)//
                .build()//
                .execute(new GenericsCallback<ResponseBean>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MLog.e("http", "请求的地址：" + BASEURL + api );
                        MLog.e("http", "上传的参数：" + gson.toJson(obj));
                        MLog.e("http", "错误信息：" + e.getMessage());
                        requestListener.onFailure(e.getMessage());
                    }

                    @Override
                    public void onResponse(ResponseBean response, int id) {
                        MLog.e("http", "请求的地址：" + BASEURL + api );
                        MLog.e("http", "上传的参数：" + gson.toJson(obj));
                        MLog.e("http", "返回参数：" + gson.toJson(response));
                        requestListener.onSuccess(response);
                    }
                });
    }






    public static void downloade(String url ,String filePath,String fileName){
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(filePath,fileName)//
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MLog.e( "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        MLog.e( "onResponse :" + response.getAbsolutePath());
                    }
                });
    }


}
