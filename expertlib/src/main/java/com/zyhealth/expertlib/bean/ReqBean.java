package com.zyhealth.expertlib.bean;

import android.content.Context;

import java.util.Map;

/**
 * 网络请求失败胡点击按钮重新加载，保存之前请求的对象实体
 * （专用）
 */
public class ReqBean {

    private Context context;
    private String url;
   // private Object object;//传统的json
    private Map<String,String> map;//表单形式传递
    private int requestId;


    public ReqBean(Context context, String url, Map<String, String> map, int requestId) {
        this.context = context;
        this.url = url;
        this.map = map;
        this.requestId = requestId;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
