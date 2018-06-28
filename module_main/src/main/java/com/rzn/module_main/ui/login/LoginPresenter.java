package com.rzn.module_main.ui.login;

import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_main.ui.setting.SettingActivity;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.utils.GlideUtils;
import com.zyhealth.expertlib.utils.MLog;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 *
 *
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void login(String phone, String code) {
        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("phone", phone);
        reqData(mContext, "/user/userRegister", map, 111);//farmHand/
    }

    @Override
    public void getAuthCode(String phone) {
        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        reqData(mContext, "/user/getCode", map, 112);//farmHand/
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                //登录成功后接收到的数据
                LoginResponseBean loginResponseBean = GsonUtils.gsonParseBean(gson, response.getResult(), LoginResponseBean.class);
                //将数据进行保存
                FileSaveUtils.fileSaveObject(loginResponseBean, "loginBean");
                JPushInterface.setAlias(mContext, 111, loginResponseBean.getUserId());
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
