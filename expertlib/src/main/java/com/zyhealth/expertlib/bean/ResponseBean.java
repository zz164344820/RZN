package com.zyhealth.expertlib.bean;

/**
 * Created by Administrator on 2016-08-02.
 */
public class ResponseBean extends  RBResponse{
    private  int code;
    private  String message;//提示信息（异常，正确）
    private  Object result ;//响应结果


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
