package com.rzn.module_main.ui.keepstationdetial.bean;

import java.util.List;

/**
 * RAY.LEE
 * Created by 17662on 2019/3/14.
 */

public class StationDetialBean {

    private String businessAddress;//": "安徽省淮南市田家庵区百花园路1032", //商家地址
    private String createTime;//":"2018-07-03 22:41:22", //创建时间
    private String factoryName;//":"君宝修理厂2", //维修站名称
    //    private String factoryPic1;//":"", //维修站图片
//    private String factoryPic2;//": "http://173rd88727.iok.la:80/farmHand/handler/downloadFile?fileName\u003drepairShopPic_1533729037639_G2M0M.png",
//    private String factoryPic3;//": "",
//    private String factoryPic4;//": "",
//    private String factoryPic5;//": "",
//    private String factoryPic6;//": "",
    private List<String> factoryPicList;
    private String homeTel;//":"", //家庭电话
    private String latitude;//":"98.32835", //维度
    private String linkman;//":"胡君宝2", //联系人名称
    private String longitude;//":"103.124235", //经度
    private String phone;//":"18063005664", //联系人手机
    private String repairId;//":"1234567891234567890987654121", //维修站id
    private String repairScope;//":"农用机械、各类轿车、吊车、各类大客车，农用机械、各类轿车、吊车、各类大电子设备", //维修范围
    private String tel;//":"0554-4444444", //座机
    private String workTimeEnd;//":"2017-06-18", //营业结束时间
    private String workTimeStart;//":"2017-06-17" //营业开始时间

    public List<String> getFactoryPicList() {
        return factoryPicList;
    }

    public void setFactoryPicList(List<String> factoryPicList) {
        this.factoryPicList = factoryPicList;
    }


    //    public String getFactoryPic2() {
//        return factoryPic2;
//    }
//
//    public void setFactoryPic2(String factoryPic2) {
//        this.factoryPic2 = factoryPic2;
//    }
//
//    public String getFactoryPic3() {
//        return factoryPic3;
//    }
//
//    public void setFactoryPic3(String factoryPic3) {
//        this.factoryPic3 = factoryPic3;
//    }
//
//    public String getFactoryPic4() {
//        return factoryPic4;
//    }
//
//    public void setFactoryPic4(String factoryPic4) {
//        this.factoryPic4 = factoryPic4;
//    }
//
//    public String getFactoryPic5() {
//        return factoryPic5;
//    }
//
//    public void setFactoryPic5(String factoryPic5) {
//        this.factoryPic5 = factoryPic5;
//    }
//
//    public String getFactoryPic6() {
//        return factoryPic6;
//    }
//
//    public void setFactoryPic6(String factoryPic6) {
//        this.factoryPic6 = factoryPic6;
//    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

//    public String getFactoryPic1() {
//        return factoryPic1;
//    }
//
//    public void setFactoryPic1(String factoryPic1) {
//        this.factoryPic1 = factoryPic1;
//    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getRepairScope() {
        return repairScope;
    }

    public void setRepairScope(String repairScope) {
        this.repairScope = repairScope;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWorkTimeEnd() {
        return workTimeEnd;
    }

    public void setWorkTimeEnd(String workTimeEnd) {
        this.workTimeEnd = workTimeEnd;
    }

    public String getWorkTimeStart() {
        return workTimeStart;
    }

    public void setWorkTimeStart(String workTimeStart) {
        this.workTimeStart = workTimeStart;
    }
}
