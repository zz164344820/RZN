package com.rzn.module_driver.ui.alipayV2;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * Created by Gaoliangchao on 2017/11/27.
 * <p>
 * Description :    支付宝支付工具类 新版支付宝支付的工具类
 * 1.加签过程放在乘客端
 * 2.升级支付宝调用接口
 */

public class AlipayUtilV2 {

    private static AlipayUtilV2 alipayUtilV2 = new AlipayUtilV2();

    private static String orderStr;

    //支付宝同步支付后的回调通知
    private static OnPayResult2 payResult2;

    private static final int PAY_RESULT = 1;

    //支付结果获取和处理 ——同步返回
    //    返回码 	含义
    //    9000 	订单支付成功
    //    8000 	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    //    4000 	订单支付失败
    //    5000 	重复请求
    //    6001 	用户中途取消
    //    6002 	网络连接出错
    //    6004 	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    //    其它 	其它支付错误
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PAY_RESULT: {
                    PayResultV2 resultObj = new PayResultV2((Map<String, String>) msg.obj);
                    String resultStatus = resultObj.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功， 具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        //                        ToastUtils.showShortToastSafe("支付成功");
                        if (payResult2 != null) {
                            payResult2.onPaySuccess();
                        }

                    } else {
                        if (payResult2 != null) {
                            if (TextUtils.equals(resultStatus, "6001")) {
                                payResult2.onPayFail("用户取消支付");
                            } else {
                                payResult2.onPayFail("支付失败");
                            }
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 私有化构造
     */
    private AlipayUtilV2() {
    }

    /**
     * 工具类初始化方法
     *
     * @param orderInfo 服务器加密签名后的订单信息
     * @param callback  支付宝同步支付的回调通知
     * @return
     */
    public static AlipayUtilV2 getInstance(String orderInfo, OnPayResult2 callback) {
        orderStr = orderInfo;
        payResult2 = callback;
        return alipayUtilV2;
    }

    /**
     * 支付方法
     */
    public void startPayV2(final Activity context) {

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(context);
                Map<String, String> result = alipay.payV2(orderStr, true);
                Message msg = new Message();
                msg.what = PAY_RESULT;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 用于支付宝支付的同步通知
     */
    public interface OnPayResult2 {
        /**
         * 支付成功
         */
        void onPaySuccess();

        /**
         * 支付失败
         *
         * @param errorMsg 错误信息
         */
        void onPayFail(String errorMsg);
    }
}
