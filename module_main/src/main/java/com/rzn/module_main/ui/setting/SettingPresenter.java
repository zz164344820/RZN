package com.rzn.module_main.ui.setting;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.mywallet.bean.MyWalletBean;
import com.rzn.module_main.ui.setting.bean.SettingPasswordBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingPresenter extends BasePresenterImpl<SettingContract.View> implements SettingContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getIsPasswordData(Map<String, String> map) {
        reqData(mContext, "/fund/isSetPayPassword", map, 1313);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 1313:
                SettingPasswordBean settingPasswordBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<SettingPasswordBean>() {
                }.getType());
                mView.getIsPasswordDataSuccess(settingPasswordBean);
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
