package com.rzn.module_main.ui.mywallet.bean;

import java.io.Serializable;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/17.
 */

public class MyWalletBean implements Serializable {

    private String balance;//": 92.04,
    private String userId;//": "40289f376536de0401653734d39d0000",
    private String viewTotalPrice;//": 92.04,
    private String withdrawing;//": "0"


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getViewTotalPrice() {
        return viewTotalPrice;
    }

    public void setViewTotalPrice(String viewTotalPrice) {
        this.viewTotalPrice = viewTotalPrice;
    }

    public String getWithdrawing() {
        return withdrawing;
    }

    public void setWithdrawing(String withdrawing) {
        this.withdrawing = withdrawing;
    }
}
