package com.rzn.module_main.ui.setting.bean;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/18.
 */

public class SettingPasswordBean {

    private String fundId;//": "402880e76a6976dc016a697725260000",
    private boolean isSet;//": true // true 已设置密码 ,falst 未设置密码

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }
}
