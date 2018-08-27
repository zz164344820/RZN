package com.rzn.module_main.ui.main.mine;

import android.text.TextUtils;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_main.ui.drivercenter.DriverBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MinePresenter extends BasePresenterImpl<MineContract.View> implements MineContract.Presenter{

    @Override
    public void onCreate() {
        super.onCreate();
        getDriverData();
    }


    @Override
    public void getDriverData() {
        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if (loginResponseBean==null ||TextUtils.isEmpty(loginResponseBean.getHandlerId())) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("handlerId", loginResponseBean.getHandlerId());
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
