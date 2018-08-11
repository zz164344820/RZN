package com.rzn.module_main.ui.main.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by zz on 2018/8/5.
 */

public class HeWeather6_getCity implements Serializable{


    public List<Basic> basic;


    public List<Basic> getBasic() {
        if (basic == null) {
            return new ArrayList<>();
        }
        return basic;
    }

    public void setBasic(List<Basic> basic) {
        this.basic = basic;
    }

    public class Basic implements Serializable{
       private String  location;//
        private String cid;//	地区／城市ID	CN101080402
        private String  parent_city;//	该地区／城市的上级城市	乌兰察布
        private String admin_area;//	该地区／城市所属行政区域	内蒙古
        private String cnty	;//该地区／城市所属国家名称	中国
        private String tz;//	该地区／城市所在时区	+8.0
        private String lat;
        private String lon;

        public String getLat() {
            return lat == null ? "" : lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon == null ? "" : lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLocation() {
            return location == null ? "" : location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCid() {
            return cid == null ? "" : cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getParent_city() {
            return parent_city == null ? "" : parent_city;
        }

        public void setParent_city(String parent_city) {
            this.parent_city = parent_city;
        }

        public String getAdmin_area() {
            return admin_area == null ? "" : admin_area;
        }

        public void setAdmin_area(String admin_area) {
            this.admin_area = admin_area;
        }

        public String getCnty() {
            return cnty == null ? "" : cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getTz() {
            return tz == null ? "" : tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }
    }










}
