package com.rzn.module_main.ui.modiffypassword.modiffypwd;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.modiffypassword.modiffypwd.bean.ModiffyBean;
import com.rzn.module_main.ui.setting.bean.SettingPasswordBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ModiffyPasswordPresenter extends BasePresenterImpl<ModiffyPasswordContract.View> implements ModiffyPasswordContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }

    @Override
    public void pushPasswordData(Map<String, String> map) {
        reqData(mContext, "/fund/checkPayPassword", map, 1414);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 1414:
                ModiffyBean modiffyBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<ModiffyBean>() {
                }.getType());
                mView.pushPasswordDataSuccess(modiffyBean);
                break;
        }
    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
    }
}
