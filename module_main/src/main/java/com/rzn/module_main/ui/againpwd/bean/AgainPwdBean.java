package com.rzn.module_main.ui.againpwd.bean;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/18.
 */

public class AgainPwdBean {
    private String fundId;//": "402880e76a6976dc016a697725260000", //资产表id
    private boolean isResetComplete;//": true //是否重置成功 true:成功   false:失败

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public boolean isResetComplete() {
        return isResetComplete;
    }

    public void setResetComplete(boolean resetComplete) {
        isResetComplete = resetComplete;
    }
}
