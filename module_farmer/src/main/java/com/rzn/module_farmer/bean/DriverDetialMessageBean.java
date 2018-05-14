package com.rzn.module_farmer.bean;

import java.util.List;

/**
 * 机手详情界面
 * Created by 17662 on 2018/5/11.
 */

public class DriverDetialMessageBean {


    private String anytim;//": "1", //是否随时作业 0：否  1：是
    private String anywhere;//": "1", //是否随地作业 0：否 1：是
    private String belongs;//": "北京善天使合作社", //所属合作社
    private String birthday;//": "1990-11-12", //出生日期
    private String carNo;//": "京N:8888", //车牌号
    private String carType;//": "大众POLO", //车型
    private String createTime;//": "2018-03-21 17:11:38", //机手的创建时间
    private String createTimeInfo;//": "2018-03-28 11:27:43", //机手详情创建的时间
    private String endDate;//": "2018-03-20", //从业结束时间
    private String endDateInfo1;//": "2018-03-20", //从业结束时间
    private String handlerId;//": "40289f6c6247d1a4016247d400d40001", //机手
    private String handlerInfoId;//": "40289fab626a9fa101626aa12b0b0001", //机手详情id
    private String icon;//": "http://localhost:80/farmHand/handler/downloadFile?fileName=default.jpg", //图片回显地址
    private String idNo;//": "340403199011103456", //身份号
    private String isJoin;//": "1", //是否接单 0：不接  1：接单
    private String mobile;//": "18880808080", //手机号码
    private String name;//": "胡机手1",
    private String remark;//": "看见了看", //补充说明
    private String sex;//": "1", //性别0女1男
    private String startDate;//": "2018-03-10", //从业开始时间
    private String startDateInfo1;//": "2018-10-21",
    private String status;//": "3", // '状态0禁用1审核中2审核拒绝3审核通过'
    private String updateTime;//": "2018-03-28 12:10:07",
    private String updateTimeInfo;//": "2018-03-28 11:47:07",
    private String userId;//": "40288ad75c81124b015c8132bfe8006f",
    private String years;//": "10" //从业年限
    private List<KindTypeBean> JsonArrayKindType;
    private List<TaskPlaceBean> JsonArrayTaskPlace;

    public String getAnytim() {
        return anytim;
    }

    public void setAnytim(String anytim) {
        this.anytim = anytim;
    }

    public String getAnywhere() {
        return anywhere;
    }

    public void setAnywhere(String anywhere) {
        this.anywhere = anywhere;
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

    public String getCreateTimeInfo() {
        return createTimeInfo;
    }

    public void setCreateTimeInfo(String createTimeInfo) {
        this.createTimeInfo = createTimeInfo;
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

    public String getHandlerInfoId() {
        return handlerInfoId;
    }

    public void setHandlerInfoId(String handlerInfoId) {
        this.handlerInfoId = handlerInfoId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getStartDateInfo() {
        return startDateInfo1;
    }

    public void setStartDateInfo(String startDateInfo) {
        this.startDateInfo1 = startDateInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeInfo() {
        return updateTimeInfo;
    }

    public void setUpdateTimeInfo(String updateTimeInfo) {
        this.updateTimeInfo = updateTimeInfo;
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

    public List<KindTypeBean> getJsonArrayKindType() {
        return JsonArrayKindType;
    }

    public void setJsonArrayKindType(List<KindTypeBean> jsonArrayKindType) {
        JsonArrayKindType = jsonArrayKindType;
    }

    public List<TaskPlaceBean> getJsonArrayTaskPlace() {
        return JsonArrayTaskPlace;
    }

    public void setJsonArrayTaskPlace(List<TaskPlaceBean> jsonArrayTaskPlace) {
        JsonArrayTaskPlace = jsonArrayTaskPlace;
    }




}
