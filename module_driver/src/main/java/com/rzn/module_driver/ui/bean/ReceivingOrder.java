package com.rzn.module_driver.ui.bean;

/**
 * Created by zz on 2018/5/27.
 */

public class ReceivingOrder {
    private String conent;//该机手无接单信息,请完善接单信息
    private String handlerId;
    private String handlerInfoId;//没有接单信息的时候

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerInfoId() {
        return handlerInfoId;
    }

    public void setHandlerInfoId(String handlerInfoId) {
        this.handlerInfoId = handlerInfoId;
    }
}
