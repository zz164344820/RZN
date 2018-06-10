package com.rzn.module_main.ui.mesagecenter;

/**
 * Created by zz on 2018/6/2.
 */

public class MessageInfo {
    private String isread;
    private String msgContent;
    private String msgType;
    private String msgTitle;
    private String userMsgId;
    private String userId;
    private String createTime;


    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getUserMsgId() {
        return userMsgId;
    }

    public void setUserMsgId(String userMsgId) {
        this.userMsgId = userMsgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
