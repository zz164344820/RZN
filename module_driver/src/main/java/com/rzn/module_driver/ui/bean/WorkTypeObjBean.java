package com.rzn.module_driver.ui.bean;

/**
 * Created by 17662 on 2018/5/4.
 */

public class WorkTypeObjBean {


    private String kindId;
    private String typeId;
    private String typeName;
    private String typeUnitPrice;

    @Override
    public String toString() {
        return "WorkTypeObjBean{" +
                "kindId='" + kindId + '\'' +
                ", typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", typeUnitPrice='" + typeUnitPrice + '\'' +
                '}';
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeUnitPrice() {
        return typeUnitPrice;
    }

    public void setTypeUnitPrice(String typeUnitPrice) {
        this.typeUnitPrice = typeUnitPrice;
    }


}
