package com.rzn.module_driver.ui.bean;

/**
 * Created by 17662 on 2018/5/23.
 */

public class MyWorkDetialBean {


    private String address;//":"河北省石家庄天上马街1处", //家庭地址
    private String areaCode;//":"340403", //区code
    private String areaName;//":"田家庵区", //区名称
    private String areas;//":"25.58", //亩数
    private String cityCode;//":"340400", //城市Code
    private String cityName;//":"淮南市", //城市名称
    private String createTime;//":"2018-03-20 21:36:16", //作业单的创建时间
    private String endDate;//":"2018-04-01", //作业单的结束时间
    private String farmerTaskId;//":"40289f6c62439f1d0162439fec3d0001", //作业订单单Id
    private String flag;//":"2", //地块分布 1集中连片 2零星分散
    private String flagNum;//":"", //当是零星分散时的分散快数
    private String handlerId;//":"40289f6c6247d1a4016247d400d40001",//接这个作业单的机手的Id
    private String mobile;//":"18080808080", //联系电话
    private String name;//":"胡君宝", //作业订单的联系人
    private String orderNo;//":"18040700003", //该作业订单编号
    private String provinceCode;//":"340000", //省Code
    private String provinceName;//":"安徽省", //省名称
    private String remark;//":"记得来早点呀!", //作业订单的备注
    private String startDate;//":"2018-04-09", //作业单的开始时间
    private String status;//":"1", //订单状态 1: 待接单  2:  待作业  3:已取消  4:已完成
    private String taskPlace;//":"河北石家庄地下天帝2路",//作业的详细地址，没有的话请忽略
    private String totalprice;//":"280.20",//作业总价格
    private String types;//":"小麦-播种",//作业种类-作业类型 展示参数
    private String unitPrice;//":"41.35",//作业类型的单价

    @Override
    public String toString() {
        return "MyWorkDetialBean{" +
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
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", remark='" + remark + '\'' +
                ", startDate='" + startDate + '\'' +
                ", status='" + status + '\'' +
                ", taskPlace='" + taskPlace + '\'' +
                ", totalprice='" + totalprice + '\'' +
                ", types='" + types + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", userId='" + userId + '\'' +
                '}';
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

    private String updateTime;//":"2018-04-08 19:36:11",//更新时间
    private String userId;//":"40288ad75c81124b015c8132bfe8000f" //农户关联用户的Id


}
