package chihane.jdaddressselector.model;

import org.litepal.crud.DataSupport;

/**
 * Created by zz on 2018/1/24.
 */

public class AddressItem  extends DataSupport{
    private String areaCode;
    private String areaName;
    private String parentId;
    private int areaLevel;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(int areaLevel) {
        this.areaLevel = areaLevel;
    }
}
