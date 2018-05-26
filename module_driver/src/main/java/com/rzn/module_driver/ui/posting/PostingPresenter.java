package com.rzn.module_driver.ui.posting;


import android.text.TextUtils;
import android.widget.Switch;

import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PostingPresenter extends BasePresenterImpl<PostingContract.View> implements PostingContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);
    }

    @Override
    public void getList() {
        Map<String, String> map = new HashMap<>();
        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if (!TextUtils.isEmpty(loginResponseBean.getHandlerId())) {
            mView.showLoading(false, "");
            map.put("handlerId", loginResponseBean.getHandlerId());
            reqData(mContext, "farmHand/handler/recommendFarmerTask", map, 122);
        }
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 122:
                List<DriverGrabOrderInfo> tempList = gson.fromJson(gson.toJson(response.getResult()), new TypeToken<List<DriverGrabOrderInfo>>() {
                }.getType());
                mView.getListSuccess(tempList);
                break;
        }
    }
}
