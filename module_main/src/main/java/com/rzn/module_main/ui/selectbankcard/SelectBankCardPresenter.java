package com.rzn.module_main.ui.selectbankcard;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;

import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectBankCardPresenter extends BasePresenterImpl<SelectBankCardContract.View> implements SelectBankCardContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }

    @Override
    public void getBankCardList(Map<String, String> map) {
        reqData(mContext, "/fund/queryUserInfo", map, 1616);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 1616:
                BankListBean bankListBeanList = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<BankListBean>() {
                }.getType());
                mView.getBankCardListSuccess(bankListBeanList.getUserInfoList());
                //                ModiffyBean modiffyBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<ModiffyBean>() {
//                }.getType());
//                mView.pushPasswordDataSuccess(modiffyBean);
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
