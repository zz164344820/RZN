package com.rzn.module_main.ui.moneydetial.bean;

import java.io.Serializable;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/20.
 */

public class AccountWaterListBean implements Serializable{

    private String accountWaterId;//": 402882ee6a7c1eda016a7c3b1bc70001, //账户流水id
    private String amt;//": 12, //金额
    private String createTime;//": "2019-05-03 13:46:50", //申请创建时间
    private String status;//": "3", //账户流水状态：1：支付成功入账到余额  2：提现中  3：提现成功    4：提现失败
    private String taskTypes;//": "", //订单作业类型(订单标题)
    private String type;//": "2", //流水类型：1：收益  2: 提现 (支出）
    private String userName;//": "支付测试" //用户姓名

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskTypes() {
        return taskTypes;
    }

    public void setTaskTypes(String taskTypes) {
        this.taskTypes = taskTypes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
