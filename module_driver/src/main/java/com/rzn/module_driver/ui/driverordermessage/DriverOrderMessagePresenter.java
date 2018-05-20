package com.rzn.module_driver.ui.driverordermessage;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonParseUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_driver.ui.bean.OrederInfo;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.utils.MLog;

import java.util.HashMap;
import java.util.List;
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
        reqData(mContext,"farmHand/handler/queryKindTypeByHandlerId",map ,119);
    }

    @Override
    public void supplementOrderInfo(Map<String,String> map ) {
        mView.showLoading(false,"");
        reqData(mContext,"farmHand/handler/updateSaveHandlerInfo",map,111);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 119:
           // List<OrederInfo>  list = GsonParseUtils.GsonToList(response.getResult(),OrederInfo.class);
                List<OrederInfo>  list= gson.fromJson(gson.toJson(response.getResult()),new TypeToken<List<OrederInfo>>() {
            }.getType());
            mView.setOrderInfo(list);
            break;
            case 111:
                // TODO: 2018/5/19  开始接单完善信息请求成功处理
                break;
        }
    }


}
