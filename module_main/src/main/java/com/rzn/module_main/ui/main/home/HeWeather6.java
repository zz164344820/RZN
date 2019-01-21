package com.rzn.module_main.ui.main.home;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by zz on 2018/8/5.
 */

public class HeWeather6  implements Serializable{

    public List<Daily_forecast> daily_forecast;
    public Now now;
    public Basic basic;


    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public  List<Daily_forecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<Daily_forecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
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











    public class  Now implements  Serializable{


        private String fl;
        private String tmp;
        private String cond_code;
        private String cond_txt;
        private String hum;
        private String pcpn;
        private String pres;
        private String vis;
        private String cloud;
        private String wind_deg;
        private String wind_dir;
        private String wind_sc;
        private String wind_spd;

        public String getFl() {
            return fl == null ? "" : fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getTmp() {
            return tmp == null ? "" : tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getCond_code() {
            return cond_code == null ? "" : cond_code;
        }

        public void setCond_code(String cond_code) {
            this.cond_code = cond_code;
        }

        public String getCond_txt() {
            return cond_txt == null ? "" : cond_txt;
        }

        public void setCond_txt(String cond_txt) {
            this.cond_txt = cond_txt;
        }

        public String getHum() {
            return hum == null ? "" : hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn == null ? "" : pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPres() {
            return pres == null ? "" : pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getVis() {
            return vis == null ? "" : vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getCloud() {
            return cloud == null ? "" : cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getWind_deg() {
            return wind_deg == null ? "" : wind_deg;
        }

        public void setWind_deg(String wind_deg) {
            this.wind_deg = wind_deg;
        }

        public String getWind_dir() {
            return wind_dir == null ? "" : wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public String getWind_sc() {
            return wind_sc == null ? "" : wind_sc;
        }

        public void setWind_sc(String wind_sc) {
            this.wind_sc = wind_sc;
        }

        public String getWind_spd() {
            return wind_spd == null ? "" : wind_spd;
        }

        public void setWind_spd(String wind_spd) {
            this.wind_spd = wind_spd;
        }
    }

    public class Daily_forecast  implements Serializable{

        private String cond_code_d;
        private String cond_code_n;
        private String cond_txt_d;
        private String cond_txt_n;
        private Date date;
        private String hum;
        private String pcpn;
        private String pop;
        private String pres;
        private String tmp_max;
        private String tmp_min;
        private String uv_index;
        private String vis;
        private String wind_deg;
        private String wind_dir;
        private String wind_sc;
        private String wind_spd;

        public String getCond_code_d() {
            return cond_code_d == null ? "" : cond_code_d;
        }

        public void setCond_code_d(String cond_code_d) {
            this.cond_code_d = cond_code_d;
        }

        public String getCond_code_n() {
            return cond_code_n == null ? "" : cond_code_n;
        }

        public void setCond_code_n(String cond_code_n) {
            this.cond_code_n = cond_code_n;
        }

        public String getCond_txt_d() {
            return cond_txt_d == null ? "" : cond_txt_d;
        }

        public void setCond_txt_d(String cond_txt_d) {
            this.cond_txt_d = cond_txt_d;
        }

        public String getCond_txt_n() {
            return cond_txt_n == null ? "" : cond_txt_n;
        }

        public void setCond_txt_n(String cond_txt_n) {
            this.cond_txt_n = cond_txt_n;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getHum() {
            return hum == null ? "" : hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn == null ? "" : pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPop() {
            return pop == null ? "" : pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres == null ? "" : pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp_max() {
            return tmp_max == null ? "" : tmp_max;
        }

        public void setTmp_max(String tmp_max) {
            this.tmp_max = tmp_max;
        }

        public String getTmp_min() {
            return tmp_min == null ? "" : tmp_min;
        }

        public void setTmp_min(String tmp_min) {
            this.tmp_min = tmp_min;
        }

        public String getUv_index() {
            return uv_index == null ? "" : uv_index;
        }

        public void setUv_index(String uv_index) {
            this.uv_index = uv_index;
        }

        public String getVis() {
            return vis == null ? "" : vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getWind_deg() {
            return wind_deg == null ? "" : wind_deg;
        }

        public void setWind_deg(String wind_deg) {
            this.wind_deg = wind_deg;
        }

        public String getWind_dir() {
            return wind_dir == null ? "" : wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public String getWind_sc() {
            return wind_sc == null ? "" : wind_sc;
        }

        public void setWind_sc(String wind_sc) {
            this.wind_sc = wind_sc;
        }

        public String getWind_spd() {
            return wind_spd == null ? "" : wind_spd;
        }

        public void setWind_spd(String wind_spd) {
            this.wind_spd = wind_spd;
        }
    }
}
