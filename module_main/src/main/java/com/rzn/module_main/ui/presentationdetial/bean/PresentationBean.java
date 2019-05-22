package com.rzn.module_main.ui.presentationdetial.bean;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/20.
 */

public class PresentationBean {

    private String accountWaterId;//": "402882ee6a7c1eda016a7c3b1bc70001", //账户流水id
    private String amt;//": 12,//金额
    private String bankCard;//": "6227***********3321", //银行卡号
    private String bankName;//": "建设银行", //开户行
    private String branchBankName;//": "北京市地址北街一号支行", //支行
    private String cardholder;//": "胡君宝", //持卡人
    private String createTime;//": "2019-05-03 13:46:50", //申请提现创建时间
    private String reason;//": "",
    private String picProff;//": "http://173rd88727.iok.la/farmHand/handler/downloadFile?fileName=carPic_1553501587601_V4T0R.png", //已转账凭证图片全路径 (当且仅当verdict=1时,字段存在且有效)
    private String processTime;//": "2019-05-05 15:43:19", //提现处理时间
    private String status;//": 3,  //账户流水状态：1：支付成功入账到余额  2：提现中  3：提现成功    4：提现失败
    private String verdict;//": "1" //提现结论 1:已转账  2:驳回申请


    public String getAccountWaterId() {
        return accountWaterId;
    }

    public void setAccountWaterId(String accountWaterId) {
        this.accountWaterId = accountWaterId;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPicProff() {
        return picProff;
    }

    public void setPicProff(String picProff) {
        this.picProff = picProff;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }
}
