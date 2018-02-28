package com.zyhealth.expertlib.bean;

/**
 * 用于封装服务器返回的json信息。RBResponse 包含公共的response字段。
 */
public class RBResponse {

    /**
     * response的类型，topic？home？error？或者其他
     */
    protected String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


}
