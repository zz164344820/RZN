package com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd;


import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class VerifyMessagePresenter extends BasePresenterImpl<VerifyMessageContract.View> implements VerifyMessageContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void getAuthCode(String phone) {
        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        reqData(mContext, "/user/getCode", map, 112);//farmHand/
    }

    @Override
    public void nextData(Map<String, String> map) {
        reqData(mContext,"/user/checkUserPhone",map,111);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                //登录成功后接收到的数据

                //登录成功回调
                mView.loginSuccess();
                break;
            case 112:
                mView.startRun();
                ToastUtils.showShort("短信验证码下发成功!");
                break;
        }

    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
        mView.restoreClickTextView();
    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
        mView.restoreTextView();
    }
}
