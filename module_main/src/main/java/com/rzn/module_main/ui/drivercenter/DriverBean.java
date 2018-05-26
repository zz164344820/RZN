package com.rzn.module_main.ui.drivercenter;

/**
 * Created by 17662 on 2018/5/26.
 */

public class DriverBean {
    private String addAreas;
    private String addIncome;
    private String handlerId;
    private String icon;
    private String level;
    private String name;
    private String sex;
    private String status;

    public String getAddAreas() {
        return addAreas;
    }

    public void setAddAreas(String addAreas) {
        this.addAreas = addAreas;
    }

    public String getAddIncome() {
        return addIncome;
    }

    public void setAddIncome(String addIncome) {
        this.addIncome = addIncome;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "DriverBean{" +
                "addAreas='" + addAreas + '\'' +
                ", addIncome='" + addIncome + '\'' +
                ", handlerId='" + handlerId + '\'' +
                ", icon='" + icon + '\'' +
                ", level='" + level + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
