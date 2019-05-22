package com.rzn.module_main.ui.applygetmoney.bean;

import com.rzn.module_main.ui.selectbankcard.SelectBankCardActivity;

import java.io.Serializable;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/19.
 */

public class BankMessageBean implements Serializable {

    private String bankCard;//": "6227***********4890",//卡号
    private String bankName;//": "北京银行",//银行名称
    private String branchBankName;//": "北京市地址北街二号支行",//支行
    private String cardholder;//": "胡君宝", //磁持人
    private String createTime;//": "2019-05-03 13:39:59", //创建时间
    private String userId;//": "40289f376536de0401653734d39d0000", //用户id
    private String userInfoId;//": "402882ee6a7c1eda016a7c34d61e0000" //用户详情银行卡id


    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchBankName() {
        return branchBankName;
    }

    public void setBranchBankName(String branchBankName) {
        this.branchBankName = branchBankName;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }
}
