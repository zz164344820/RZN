package com.rzn.module_main.ui.myadvice;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyAdvicePresenter extends BasePresenterImpl<MyAdviceContract.View> implements MyAdviceContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void commitInfo(String feedback, String contactWay) {
        Map<String,String> map = new HashMap<>();
        reqData(mContext,"Test/index",map,111);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        mContext.finish();
    }
}
