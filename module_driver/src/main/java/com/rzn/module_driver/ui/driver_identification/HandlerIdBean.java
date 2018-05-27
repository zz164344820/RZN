package com.rzn.module_driver.ui.driver_identification;

/**
 * Created by 17662 on 2018/5/27.
 */

public class HandlerIdBean {
    private String handlerId;

    @Override
    public String toString() {
        return "HandlerIdBean{" +
                "handlerId='" + handlerId + '\'' +
                '}';
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }
}
