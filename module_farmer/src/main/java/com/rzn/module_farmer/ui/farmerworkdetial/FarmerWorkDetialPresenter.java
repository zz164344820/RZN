package com.rzn.module_farmer.ui.farmerworkdetial;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_farmer.bean.FarmerOrderDetialBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerWorkDetialPresenter extends BasePresenterImpl<FarmerWorkDetialContract.View> implements FarmerWorkDetialContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void httpLoadData(String farmerTaskId) {
        Map<String, String> map = new HashMap<>();
        map.put("farmerTaskId", farmerTaskId);
        reqData(mContext, "/farmerTask/farmerTaskInfo", map, 111);//farmHand/

    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                FarmerOrderDetialBean farmerOrderDetialBean = GsonUtils.gsonParseBean(gson, response.getResult(), FarmerOrderDetialBean.class);
                mView.onLoadSuccess(farmerOrderDetialBean);
                break;
        }
    }
}
