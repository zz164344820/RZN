package com.rzn.module_main.ui.mesagecenter;

/**
 * Created by zz on 2018/6/2.
 */

public class MessageInfo {
    private String isread;
    private String content;
    private String type;
    private String title;


    public MessageInfo(String isread, String content, String type, String title) {
        this.isread = isread;
        this.content = content;
        this.type = type;
        this.title = title;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
