package com.rzn.module_driver.ui.bean;

/**
 * Created by 17662 on 2018/5/27.
 */

public class DriverTypeBean {
    private int isOrder;//":1,
    private String kindId;//;":"40289fab626a9fa101626aa12b0b0002",
    private String kindName;//":"玉米",
    private String kindTypeId;//":"1666",
    private String kindTypeName;//":"玉米烘干",
    private int unitPrice;//":30.3

    public int getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(int isOrder) {
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "DriverTypeBean{" +
                "isOrder=" + isOrder +
                ", kindId='" + kindId + '\'' +
                ", kindName='" + kindName + '\'' +
                ", kindTypeId='" + kindTypeId + '\'' +
                ", kindTypeName='" + kindTypeName + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
