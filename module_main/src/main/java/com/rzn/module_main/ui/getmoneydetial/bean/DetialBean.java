package com.rzn.module_main.ui.getmoneydetial.bean;

import com.rzn.module_main.ui.moneydetial.bean.AccountWaterListBean;

import java.util.List;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/20.
 */

public class DetialBean {

    private List<AccountWaterListBean> accountWaterList;
    private String balancePast;//": "12.0", //累计提现金额
    private String withdrawing;//": "0" //提现中金额


    public List<AccountWaterListBean> getAccountWaterList() {
        return accountWaterList;
    }

    public void setAccountWaterList(List<AccountWaterListBean> accountWaterList) {
        this.accountWaterList = accountWaterList;
    }

    public String getBalancePast() {
        return balancePast;
    }

    public void setBalancePast(String balancePast) {
        this.balancePast = balancePast;
    }

    public String getWithdrawing() {
        return withdrawing;
    }

    public void setWithdrawing(String withdrawing) {
        this.withdrawing = withdrawing;
    }
}
