package com.rzn.module_farmer.bean;

/**
 * Created by 17662 on 2018/5/2.
 */

public class AppointmentDriverBean {


    private String farmerTaskId;
    private String handlerId;
    private String updateTime;
    private String userId;

    @Override
    public String toString() {
        return "AppointmentDriverBean{" +
                "farmerTaskId='" + farmerTaskId + '\'' +
                ", handlerId='" + handlerId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getFarmerTaskId() {
        return farmerTaskId;
    }

    public void setFarmerTaskId(String farmerTaskId) {
        this.farmerTaskId = farmerTaskId;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
