package com.rzn.module_main.ui.main.farmmachinery;

/**
 * Created by zz on 2018/6/10.
 */

public class InfoBean {
    private  String title;
    private  String conent;
    private  String time;
    private  String picUrl;
    private  String url;

    public InfoBean(String title, String conent, String time, String picUrl, String url) {
        this.title = title;
        this.conent = conent;
        this.time = time;
        this.picUrl = picUrl;
        this.url = url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
