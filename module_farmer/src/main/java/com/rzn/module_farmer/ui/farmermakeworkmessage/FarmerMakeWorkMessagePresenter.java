package com.rzn.module_farmer.ui.farmermakeworkmessage;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerMakeWorkMessagePresenter extends BasePresenterImpl<FarmerMakeWorkMessageContract.View> implements FarmerMakeWorkMessageContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void httpConfig(String farmerTaskId, String handlerId) {
        Map<String, String> map = new HashMap<>();
        map.put("farmerTaskId", farmerTaskId);
        map.put("handlerId", handlerId);
        reqData(mContext, "farmHand/farmerTask/confirmAppoint", map, 111);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {

            case 111:

                break;
        }
    }
}
