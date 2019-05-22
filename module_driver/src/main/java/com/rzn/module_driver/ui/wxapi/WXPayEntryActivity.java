package com.rzn.module_driver.ui.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.net.GenericsCallback;
import com.zyhealth.expertlib.net.JsonGenericsSerializator;
import com.zyhealth.expertlib.net.OkHttpLoader;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Gaoliangchao on 2017/6/30.
 * <p>
 * Description :    微信支付相应的回调类 ，用于处理微信支付的各种回调事件
 * 支付完成后，微信APP会返回到商户APP并回调onResp函数
 * 开发者需要在该函数中接收通知，判断返回错误码，
 * 如果支付成功则去后台查询支付结果再展示用户实际支付结果
 * 注意一定不能以客户端返回作为用户支付的结果，
 * 应以服务器端的接收的支付通知或查询API返回的结果为准
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wxa3c01a3a041e3939");//WxPayUtilV2.getAppId()
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {


        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    Toast.makeText(this, "微信支付:认证出现错误", Toast.LENGTH_SHORT).show();//      ToastUtils.showShortToastSafe("微信支付:认证出现错误");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_COMM:
                    //可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
                    Toast.makeText(this, "微信支付：一般错误", Toast.LENGTH_SHORT).show();//  ToastUtils.showShortToastSafe("微信支付：一般错误");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_SENT_FAILED:
                    Toast.makeText(this, "微信支付:发送失败", Toast.LENGTH_SHORT).show();//   ToastUtils.showShortToastSafe("微信支付:发送失败");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_UNSUPPORT:
                    Toast.makeText(this, "微信支付:不支持错误", Toast.LENGTH_SHORT).show();// ToastUtils.showShortToastSafe("微信支付:不支持错误");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    //无需处理。发生场景：用户不支付了，点击取消，返回APP。
                    Toast.makeText(this, "用户取消了支付", Toast.LENGTH_SHORT).show();//   ToastUtils.showShortToastSafe("用户取消了支付");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_OK:
                    //支付成功
//                    httpSure();
//                    Toast.makeText(this, "222222", Toast.LENGTH_SHORT).show();//   ToastUtils.showShortToastSafe("用户取消了支付");
                    finish();
                    break;
            }
        }

    }







//    private void httpSure() {
//
/////app_queryWeiXinOrZFBStateImpl.jspx
////        参数: userid
////        参数: token
////        参数: tokenkey
////        参数: orderid
////        参数: type（0支付宝，1微信）
//
//        Map<String, String> map = new HashMap<>();
//        map.put("userid", UserUtil.getUserLogin().getUserid());
//        map.put("token", UserUtil.getUserLogin().getToken());
//        map.put("tokenkey", UserUtil.getUserLogin().getTokenkey());
////        map.put("orderid", );
////        map.put("type", );
//
//        OkHttpUtils.post()
//                .url(OkHttpLoader.BASEURL + ModelConstant.ONLIVE_UPDATALOAD_LOGO_IMPL)//farmHand/  todo  /app_uploadActivityLogoImpl.jspx
//                .params(map)//
//                .build()//.
//                .execute(new GenericsCallback<ResponseBean>(new JsonGenericsSerializator()) {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(ResponseBean response, int id) {
//
//
//                    }
//                });
//
//
//    }


}
