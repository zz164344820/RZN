package com.rzn.module_main.ui.getmoneydetial;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.rzn.module_main.ui.getmoneydetial.bean.DetialBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GetMoneyDetialPresenter extends BasePresenterImpl<GetMoneyDetialContract.View> implements GetMoneyDetialContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }

    @Override
    public void getDetialData(Map<String, String> map) {
        reqData(mContext, "/fund/queryAccountWaterByUserIdAndType2", map, 121121);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 121121:
                DetialBean detialBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<DetialBean>() {
                }.getType());
                mView.getDetialDataSuccess(detialBean);
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
