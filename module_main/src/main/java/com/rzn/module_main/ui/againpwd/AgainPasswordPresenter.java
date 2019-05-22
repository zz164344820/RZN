package com.rzn.module_main.ui.againpwd;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.againpwd.bean.AgainPwdBean;
import com.rzn.module_main.ui.setting.bean.SettingPasswordBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AgainPasswordPresenter extends BasePresenterImpl<AgainPasswordContract.View> implements AgainPasswordContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }

    @Override
    public void setNewPassword(Map<String, String> map) {
        reqData(mContext, "/fund/resetPayPassword", map, 1515);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);

        switch (requestId) {
            case 1515:
                AgainPwdBean againPwdBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<AgainPwdBean>() {
                }.getType());
                mView.setNewPasswordSuccess(againPwdBean);
                break;
        }


    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
    }
}
