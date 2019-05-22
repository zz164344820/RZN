package com.rzn.module_main.ui.addbankcard;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.addbankcard.bean.AddBankCardBean;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddBankCardPresenter extends BasePresenterImpl<AddBankCardContract.View> implements AddBankCardContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }

    @Override
    public void addBankCardData(Map<String, String> map) {
        reqData(mContext, "/fund/addUserInfo", map, 1717);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 1717:
                AddBankCardBean addBankCardBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<AddBankCardBean>() {
                }.getType());
                mView.addBankCardDataSuccess(addBankCardBean);

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
