package com.rzn.module_main.ui.drivercenter;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverCenterPresenter extends BasePresenterImpl<DriverCenterContract.View> implements DriverCenterContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void getDriverData(Map<String, String> map) {
        reqData(mContext, "/handler/queryHandlerCentre", map, 111);//farmHand/
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                DriverBean driverBean = GsonUtils.gsonParseBean(gson, response.getResult(), DriverBean.class);
                mView.getDataSuccess(driverBean);
                break;
        }
    }
}
