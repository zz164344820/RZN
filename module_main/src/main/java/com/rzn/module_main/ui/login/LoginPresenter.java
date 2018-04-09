package com.rzn.module_main.ui.login;

import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.bean.LoginResponseBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("phone", phone);
        reqData(mContext, "farmHand/user/userRegister", map, 111);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                //登录成功后接收到的数据
                LoginResponseBean loginResponseBean = (LoginResponseBean) response.getResult();
                //将数据进行保存
                //登录成功回调
                mView.loginSuccess();
                break;
        }

    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
        switch (requestId) {
            case 111:
                mView.loginFailed(response.getMessage());
                break;
        }

    }
}
