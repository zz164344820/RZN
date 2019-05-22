package com.rzn.module_driver.ui.wxapi;

/**
 * RAY.LEE
 * Created by 17662on 2018/8/21.
 */

public class WechatPrePayInfoEntity {
    /**
     * prePayId : d1215f91-38f4-44b3-af4f-a4e6fcb4272d
     * sign : d1215f91-38f4-44b3-af4f-a4e6fcb4272d
     * partnerId : d1215f91-38f4-44b3-af4f-a4e6fcb4272d
     * nonceStr : d1215f91-38f4-44b3-af4f-a4e6fcb4272d
     * timestamp : d1215f91-38f4-44b3-af4f-a4e6fcb4272d
     */

//    "package": "Sign=WXPay",
//            "appid": "wx80b21491f1baca35",
//            "sign": "A4F21DCF447A365D28AE9435C7BFAD4F",
//            "partnerid": "1512183481",
//            "prepayid": "wx21173425959003e39937d1611881401189",
//            "noncestr": "6FlgkDnXemLwu2Ry",
//            "timestamp": "1534844066046"

    //微信SDK 对应appid
    private String appid;
    //微信接口扩展值
    private String extra;
    //微信预支付订单ID
    private String prepayid;
    //商户ID
    private String partnerid;
    //随机字符串
    private String noncestr;
    //时间戳
    private String timestamp;
    //签名
    private String sign;

    //订单id
    private String orderid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


}
