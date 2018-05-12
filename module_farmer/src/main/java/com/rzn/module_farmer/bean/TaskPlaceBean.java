package com.rzn.module_farmer.bean;

/**
 * Created by 17662 on 2018/5/11.
 */

public class TaskPlaceBean {
    private String address;//":"可以不填写,该字段不参加匹配", //详细地址（扩展字段）
    private String areaCode;//":"340404",
    private String areaName;//":"谢家集区",
    private String cityCode;//":"340400",
    private String cityName;//":"淮南市",
    private String provinceCode;//":"340000",
    private String provinceName;//":"安徽省"

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


}
