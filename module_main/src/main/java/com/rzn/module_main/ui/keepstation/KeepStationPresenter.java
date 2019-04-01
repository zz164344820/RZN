package com.rzn.module_main.ui.keepstation;


import com.google.gson.reflect.TypeToken;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KeepStationPresenter extends BasePresenterImpl<KeepStationContract.View> implements KeepStationContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getKeepData(String searchStr, String longitude, String latitude ,int pager) {
        mView.showLoading(false, "");
        LoginResponseBean responseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map<String, String> map = new HashMap<>();
        map.put("userId", responseBean.getUserId());
        map.put("page", pager+"");
        map.put("name", searchStr);
        map.put("longitude", longitude);//longitude机手所在经度
        map.put("latitude", latitude);//latitude机手所在维度
        reqData(mContext, "/repair/queryRepair", map, 111);//farmHand/
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                Type type = new TypeToken<List<KeepStationBean>>() {
                }.getType();
                List<KeepStationBean> list = gson.fromJson(gson.toJson(response.getResult()), type);
                if(list!=null){
                    mView.getKeepDataSuccess(list);
                }

                break;
        }
    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
        if (requestId == 111) {
            mView.getKeepDataFailed();
        }
    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
        if (requestId == 111) {
            mView.getKeepDataFailed();
        }

    }
}
