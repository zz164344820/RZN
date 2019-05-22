package com.rzn.commonbaselib.bean;

import java.io.Serializable;

/**
 * Created by a111 on 2018/4/19.
 */

public class JobOrderDetialBean implements Serializable {
    private String address;//": "河北省石家庄天上马街1处", //家庭地址
    private String areaCode;//": "340406", //区Code
    private String areaName;//": "潘集区", //区名称
    private String areas;//": "25.58", //亩数
    private String cityCode;//": "340400", //城市Code
    private String cityName;//": "淮南市", //城市名称
    private String createTime;//": "2018-03-20 21:36:47", //创建时间
    private String endDate;//": "2018-06-21",//作业单结束时间
    private String farmerTaskId;//": "40289f6c62439f1d016243a0653c0002",//
    private String flag;//": "1",
    private String flagNum;//": "",
    private String handlerId;//": "40289f6c6247d1a4016247d400d40001",
    private String kindTypeId;//": "1666",
    private String mobile;//": "18080808081",
    private String name;//": "君宝1",
    private String orderNo;//": "18040700002",
    private String provinceCode;//": "340000",
    private String provinceName;//": "安徽省",
    private String realAreas;//": "50.50",
    private String realTotalprice;//": "2042.73",
    private String remark;//": "记得来早点呀!",
    private String startDate;//": "2018-05-28",
    private String status;//": "4",
    private String taskPlace;//": "河北石家庄地下天帝2路",
    private String totalprice;//": "280.01",
    private String types;//": "小麦-播种",
    private String unitPrice;//": "40.45",
    private String updateTime;//": "2018-05-12 18:00:53",
    private String userId;//": "40288ad75c81124b015c8132bfe8000f"
    private String kind;
    private String kindId;
    private String kindType;


    private String payPrice;//”:”108.5”,//实际付款金额
    private String payTime;//”:”2018-05-12 18:00:53”,//支付时间
    private String paymentType;//”:1,//支付方式：1：支付宝 2：微信
    private String discountAmount;//”:1,//优惠金额


    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getKindType() {
        return kindType;
    }

    public void setKindType(String kindType) {
        this.kindType = kindType;
    }


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getFarmerTaskId() {
        return farmerTaskId;
    }

    public void setFarmerTaskId(String farmerTaskId) {
        this.farmerTaskId = farmerTaskId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlagNum() {
        return flagNum;
    }

    public void setFlagNum(String flagNum) {
        this.flagNum = flagNum;
    }

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getKindTypeId() {
        return kindTypeId;
    }

    public void setKindTypeId(String kindTypeId) {
        this.kindTypeId = kindTypeId;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getRealAreas() {
        return realAreas;
    }

    public void setRealAreas(String realAreas) {
        this.realAreas = realAreas;
    }

    public String getRealTotalprice() {
        return realTotalprice;
    }

    public void setRealTotalprice(String realTotalprice) {
        this.realTotalprice = realTotalprice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getTaskPlace() {
        return taskPlace;
    }

    public void setTaskPlace(String taskPlace) {
        this.taskPlace = taskPlace;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
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

    @Override
    public String toString() {
        return "JobOrderDetialBean{" +
                "address='" + address + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areas='" + areas + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", endDate='" + endDate + '\'' +
                ", farmerTaskId='" + farmerTaskId + '\'' +
                ", flag='" + flag + '\'' +
                ", flagNum='" + flagNum + '\'' +
                ", handlerId='" + handlerId + '\'' +
                ", kindTypeId='" + kindTypeId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", realAreas='" + realAreas + '\'' +
                ", realTotalprice='" + realTotalprice + '\'' +
                ", remark='" + remark + '\'' +
                ", startDate='" + startDate + '\'' +
                ", status='" + status + '\'' +
                ", taskPlace='" + taskPlace + '\'' +
                ", totalprice='" + totalprice + '\'' +
                ", types='" + types + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userId='" + userId + '\'' +
                ", kind='" + kind + '\'' +
                ", kindId='" + kindId + '\'' +
                ", kindType='" + kindType + '\'' +
                '}';
    }
}
