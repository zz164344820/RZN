package com.rzn.module_main.ui.applygetmoney;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.rzn.module_main.ui.setting.bean.SettingPasswordBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ApplyGetMoneyPresenter extends BasePresenterImpl<ApplyGetMoneyContract.View> implements ApplyGetMoneyContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }

    @Override
    public void getBankListData(Map<String, String> map) {
        reqData(mContext, "/fund/queryUserInfo", map, 1616);
    }

    @Override
    public void getMoneyData(Map<String, String> map) {
        reqData(mContext,"/fund/applyWithdraw",map,1919);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 1616:
                BankListBean bankListBeanList = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<BankListBean>() {
                }.getType());
                mView.getBankListDataSuccess(bankListBeanList.getUserInfoList());
                break;
            case 1919:
                mView.getMoneyDataSuccess();
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
