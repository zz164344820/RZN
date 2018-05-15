package com.rzn.module_driver.ui.bean;

import java.util.List;

/**
 * Created by 17662 on 2018/5/4.
 */

public class WorkTypeBean {
    private String kindId;
    private String kindName;
    private List<WorkTypeObjBean> typeArray;

    @Override
    public String toString() {
        return "WorkTypeBean{" +
                "kindId='" + kindId + '\'' +
                ", kindName='" + kindName + '\'' +
                ", typeArray=" + typeArray +
                '}';
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

    public List<WorkTypeObjBean> getTypeArray() {
        return typeArray;
    }

    public void setTypeArray(List<WorkTypeObjBean> typeArray) {
        this.typeArray = typeArray;
    }
}
