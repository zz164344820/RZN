package com.rzn.module_driver.ui.wxapi;

import android.content.Context;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by Gaoliangchao on 2017/11/29.
 * <p>
 * Description :   新版优化后的微信支付
 * 微信支付的工具类 对应jar包 ==>wechat-sdk-android-without-mta-1.4.0.jar.jar
 */

public class WxPayUtilV2 {

    //微信开放平台审核通过的应用APPID
    private static final String APP_ID = "wxa3c01a3a041e3939";

    /**
     * 发起微信支付
     *
     * @param context
     * @param bean
     */
    public static boolean startPay(Context context, WechatPrePayInfoEntity bean) {
        //创建微信
        IWXAPI wxapi = WXAPIFactory.createWXAPI(context, null);
        //判断当前是支持微信支付的微信客户端
        if (wxapi.getWXAppSupportAPI() < Build.EMOJI_SUPPORTED_SDK_INT) {
            Toast.makeText(context, "请安装微信客户端最新版本", Toast.LENGTH_SHORT).show();// ToastUtils.showShortToastSafe("请安装微信客户端最新版本");
            return false;
        }
        //注册
        boolean isRegit = wxapi.registerApp(APP_ID);
        if (!isRegit) {
            Toast.makeText(context, "注册微信失败", Toast.LENGTH_SHORT).show();//   ToastUtils.showShortToastSafe("注册微信失败");
            return false;
        }
        //发起支付
        PayReq req = new PayReq();
        //应用ID
        req.appId = bean.getAppid();
        //商户号
        req.partnerId = bean.getPartnerid();
        //预支付交易会话ID
        req.prepayId = bean.getPrepayid();
        //随机字符串
        req.nonceStr = bean.getNoncestr();
        //时间戳
        req.timeStamp = bean.getTimestamp();
        //扩展字段
        req.packageValue = "Sign=WXPay";//bean.getExtra();
        // req.extData			= "app data"; // optional
        //签名
        req.sign = bean.getSign();

//        //检查
        if (!req.checkArgs()) {
            Toast.makeText(context, "请求微信支付,发送请求不合法", Toast.LENGTH_SHORT).show();// ToastUtils.showShortToastSafe("请求微信支付,发送请求不合法");
            return false;
        }
        boolean isSuc = wxapi.sendReq(req);
        if (!isSuc) {
            Toast.makeText(context, "请求微信支付失败", Toast.LENGTH_SHORT).show();//     ToastUtils.showShortToastSafe("请求微信支付失败");
        }
        return isSuc;
    }

    public static String getAppId() {
        return APP_ID;
    }


}

