package com.rzn.module_farmer.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 17662 on 2018/5/9.
 */

public class FarmerDriverMessageBean   implements Serializable{


    private String belongs;//": "北京善天使合作社",
    private String birthday;//":"1990-11-12",
    private String carNo;//":"京N:8888",
    private String carPic1;//":"123.jpg",
    private String carType;//":"大众POLO",
    private String createTime;//":"2018-04-18 23:35:59",
    private String endDate;//":"2018-03-20",
    private String handlerId;//":"40289f6c6247d1a4016247d400d46660",
    private String icon;//":"http://localhost:80/farmHand/handler/downloadFile?fileName=default.jpg",
    private String idNo;//":"340403199011103456",
    private String isJoin;//":"1",
    private String machinePic1;//":"456.jpg",
    private String minUnitPrice;//":"50.50",
    private String mobile;//":"18880808080",
    private String name;//":"测试666",
    private String sex;//":"1",
    private String startDate;// ":"2018-03-10",
    private String status;//":"3",
    private List<MessageTypeBean> types;//
    private String updateTime;//":"2018-04-18 23:35:59",
    private String userId;//":"40288ad75c81124b015c8132bfe8666f",
    private String years;//":"10"

    @Override
    public String toString() {
        return "FarmerDriverMessageBean{" +
                "belongs='" + belongs + '\'' +
                ", birthday='" + birthday + '\'' +
                ", carNo='" + carNo + '\'' +
                ", carPic1='" + carPic1 + '\'' +
                ", carType='" + carType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", endDate='" + endDate + '\'' +
                ", handlerId='" + handlerId + '\'' +
                ", icon='" + icon + '\'' +
                ", idNo='" + idNo + '\'' +
                ", isJoin='" + isJoin + '\'' +
                ", machinePic1='" + machinePic1 + '\'' +
                ", minUnitPrice='" + minUnitPrice + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", startDate='" + startDate + '\'' +
                ", status='" + status + '\'' +
                ", types=" + types +
                ", updateTime='" + updateTime + '\'' +
                ", userId='" + userId + '\'' +
                ", years='" + years + '\'' +
                '}';
    }

    public String getBelongs() {
        return belongs;
    }

    public void setBelongs(String belongs) {
        this.belongs = belongs;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarPic1() {
        return carPic1;
    }

    public void setCarPic1(String carPic1) {
        this.carPic1 = carPic1;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(String isJoin) {
        this.isJoin = isJoin;
    }

    public String getMachinePic1() {
        return machinePic1;
    }

    public void setMachinePic1(String machinePic1) {
        this.machinePic1 = machinePic1;
    }

    public String getMinUnitPrice() {
        return minUnitPrice;
    }

    public void setMinUnitPrice(String minUnitPrice) {
        this.minUnitPrice = minUnitPrice;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MessageTypeBean> getTypes() {
        return types;
    }

    public void setTypes(List<MessageTypeBean> types) {
        this.types = types;
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

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }


}
