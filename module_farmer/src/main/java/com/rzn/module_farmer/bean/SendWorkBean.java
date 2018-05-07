package com.rzn.module_farmer.bean;

/**
 * 发布订单
 * Created by 17662 on 2018/5/4.
 */

public class SendWorkBean {
    private String farmerTaskId;

    @Override
    public String toString() {
        return "SendWorkBean{" +
                "farmerTaskId='" + farmerTaskId + '\'' +
                '}';
    }

    public String getFarmerTaskId() {
        return farmerTaskId;
    }

    public void setFarmerTaskId(String farmerTaskId) {
        this.farmerTaskId = farmerTaskId;
    }
}
