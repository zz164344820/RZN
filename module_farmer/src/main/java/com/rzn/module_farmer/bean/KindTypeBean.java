package com.rzn.module_farmer.bean;

/**
 * Created by 17662 on 2018/5/11.
 */

public class KindTypeBean {
    private String isOrder;//":"1", //该类型作物是否(完善接单信息)接单  0: 未接单   1:接单
    private String kindId;//":"40289fab626a9fa101626aa12b0b0002", //作业种类Id
    private String kindName;//":"玉米", //作业种类名称
    private String kindTypeId;//":"111", //作业类型 ID
    private String kindTypeName;//":"小麦打捆", //作业类型名称

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

    private String types;//":"玉米-小麦打捆", //作业种类 和 作业类型 组合字段（方便展示）
    private String unitPrice;//":"40.30" //单价

    @Override
    public String toString() {
        return "KindTypeBean{" +
                "isOrder='" + isOrder + '\'' +
                ", kindId='" + kindId + '\'' +
                ", kindName='" + kindName + '\'' +
                ", kindTypeId='" + kindTypeId + '\'' +
                ", kindTypeName='" + kindTypeName + '\'' +
                ", types='" + types + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }
}
