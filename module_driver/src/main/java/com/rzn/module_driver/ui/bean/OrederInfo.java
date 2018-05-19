package com.rzn.module_driver.ui.bean;

/**
 * Created by zz on 2018/5/19.
 */

public class OrederInfo {
    private String  handlerId;//机手Id
    private String  isOrder;//是否(完善接单信息)接单  0: 未接单   1:接单
    private String  kindId; //作业种类Id
    private String  kindName;//作业种类名称
    private String  kindTypeId;//作业类型Id
    private String  kindTypeName;//作业类型名称
    private String  name;//作业种类-作业类型
    private String  unitPrice;//每种作业类型的单价

    public String getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(String handlerId) {
        this.handlerId = handlerId;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getKindTypeId() {
        return kindTypeId;
    }

    public void setKindTypeId(String kindTypeId) {
        this.kindTypeId = kindTypeId;
    }

    public String getKindTypeName() {
        return kindTypeName;
    }

    public void setKindTypeName(String kindTypeName) {
        this.kindTypeName = kindTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
