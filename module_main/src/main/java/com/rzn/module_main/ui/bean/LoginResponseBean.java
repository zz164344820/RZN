package com.rzn.module_main.ui.bean;

/**
 * Created by Ray.lee on 2018/4/8.
 * Description:  登录成功返回bean实体
 */

public class LoginResponseBean {

    private String createTime;
    private String loginTime;
    private String phone;
    private int role;
    private int status;
    private String updateTime;
    private String userId;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoginResponseBean{" +
                "createTime='" + createTime + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", updateTime='" + updateTime + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
