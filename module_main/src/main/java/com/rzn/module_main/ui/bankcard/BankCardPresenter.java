package com.rzn.module_main.ui.bankcard;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BankCardPresenter extends BasePresenterImpl<BankCardContract.View> implements BankCardContract.Presenter {
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
    public void detelBankCard(Map<String, String> map) {
        mView.showLoading(false, "");
        reqData(mContext, "/fund/deleteUserInfoByUserInfoId", map, 1818);
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
            case 1818:
                mView.detelBankCardSuccess();
                break;
        }
    }
}
