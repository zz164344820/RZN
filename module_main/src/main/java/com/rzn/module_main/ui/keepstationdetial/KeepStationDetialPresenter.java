package com.rzn.module_main.ui.keepstationdetial;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_main.ui.keepstationdetial.bean.StationDetialBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KeepStationDetialPresenter extends BasePresenterImpl<KeepStationDetialContract.View> implements KeepStationDetialContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);
    }

    @Override
    public void getDetialData(Map<String, String> map) {
        reqData(mContext, "/repair/repairInfo", map, 11111111);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 11111111:
                StationDetialBean stationDetialBean = GsonUtils.gsonParseBean(gson, response.getResult(), StationDetialBean.class);
                mView.getDetialDataSuccess(stationDetialBean);
                break;
        }
    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
    }
}
