package com.rzn.module_main.ui.moneydetial;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.rzn.module_main.ui.moneydetial.bean.MoneyDataBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoneyDetialPresenter extends BasePresenterImpl<MoneyDetialContract.View> implements MoneyDetialContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }

    @Override
    public void getListData(Map<String, String> map) {
        reqData(mContext, "/fund/queryAccountWaterByUserId", map, 120120);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 120120:
                MoneyDataBean moneyDataBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<MoneyDataBean>() {
                }.getType());
                mView.getListDataSuccess(moneyDataBean.getAccountWaterList());

                break;
        }
    }
}
