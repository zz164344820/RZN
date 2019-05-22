package com.rzn.module_main.ui.mywallet;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.mywallet.bean.MyWalletBean;
import com.rzn.module_main.ui.personalinfo.UserInfo;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyWalletPresenter extends BasePresenterImpl<MyWalletContract.View> implements MyWalletContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }

    @Override
    public void getWalletDetial(Map<String, String> map) {
        reqData(mContext, "/fund/getMyWalletByUserId", map, 1212);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 1212:
                MyWalletBean myWalletBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<MyWalletBean>() {
                }.getType());
                mView.getWalletDetialSuccess(myWalletBean);
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
