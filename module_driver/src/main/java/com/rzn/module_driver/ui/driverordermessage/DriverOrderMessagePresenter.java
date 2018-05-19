package com.rzn.module_driver.ui.driverordermessage;


import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverOrderMessagePresenter extends BasePresenterImpl<DriverOrderMessageContract.View> implements DriverOrderMessageContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        getOrderType();

    }

    private void getOrderType() {
        mView.showLoading(false,"");
        Map<String ,String> map = new HashMap<>();
        LoginResponseBean  loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        map.put("handlerId",loginResponseBean.getHandlerId());
        reqData(mContext,"farmHand/handler/queryHandler",map ,119);
    }

    @Override
    public void httpPost() {

        reqData(mContext,"farmHand/handler/updateSaveHandlerInfo",null,111);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 119:
                break;
            case 111:
                break;
        }
    }
}
