package com.rzn.module_main.ui.modiffypassword.modiffypwd.bean;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/18.
 */

public class ModiffyBean {

    private String fundId;//": "402880e76a6976dc016a697725260000", //资产表Id (fundId)
    private boolean isRight;//":true //支付密码是否正确


    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
