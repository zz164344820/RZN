package com.rzn.module_farmer.bean;

import java.io.Serializable;

/**
 * Created by 17662 on 2018/5/9.
 */

public class MessageTypeBean implements Serializable {
    private String isOrder;//":"1",
    private String kindId;//":"40289fab626a9fa101626aa12b0b0004",
    private String kindName;//":"棉花",
    private String kindTypeId;//":"1666",
    private String kindTypeName;//":"棉花收获",
    private String unitPrice;//":"50.50"

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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }




}
