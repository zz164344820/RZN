package com.rzn.module_driver.ui.driver_identification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2018/5/14.
 */

public class JobTypes {
    private String kindId;
    private String kindName;
    private List<Kind> typeArray;

    class  Kind{
        private String kindId;
        private String typeId;
        private String typeName;
        private String typeUnitPrice;


        public String getKindId() {
            return kindId == null ? "" : kindId;
        }

        public void setKindId(String kindId) {
            this.kindId = kindId;
        }

        public String getTypeId() {
            return typeId == null ? "" : typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName == null ? "" : typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeUnitPrice() {
            return typeUnitPrice == null ? "" : typeUnitPrice;
        }

        public void setTypeUnitPrice(String typeUnitPrice) {
            this.typeUnitPrice = typeUnitPrice;
        }
    }


    public String getKindId() {
        return kindId == null ? "" : kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName == null ? "" : kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public List<Kind> getTypeArray() {
        if (typeArray == null) {
            return new ArrayList<>();
        }
        return typeArray;
    }

    public void setTypeArray(List<Kind> typeArray) {
        this.typeArray = typeArray;
    }
}
