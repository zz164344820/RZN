package com.rzn.module_driver.ui.driverlist;

import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverListPresenter extends BasePresenterImpl<DriverListContract.View> implements DriverListContract.Presenter {

    @Override
    public void getList(String userId, String farmerId) {
        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        reqData(mContext, "", map, 111);

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
