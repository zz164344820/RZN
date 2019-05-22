package com.rzn.module_main.ui.presentationdetial;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.moneydetial.bean.MoneyDataBean;
import com.rzn.module_main.ui.presentationdetial.bean.PresentationBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PresentationDetialPresenter extends BasePresenterImpl<PresentationDetialContract.View> implements PresentationDetialContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);



    }

    @Override
    public void getDetialData(Map<String, String> map) {
        reqData(mContext,"/fund/getWdrResultByAccountWaterId",map,122122);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 122122:
                PresentationBean presentationBean = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<PresentationBean>() {
                }.getType());
                mView.getDetialDataSuccess(presentationBean);
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
