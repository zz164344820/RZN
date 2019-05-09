package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 致医健康 MVPPlugin
 * @author zz
 */
public class CommodityListPresenter extends BasePresenterImpl<CommodityListContract.View> implements CommodityListContract.Presenter{


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void getCommodityList(String text) {
        Map<String,String> map = new HashMap<>();
        reqData(mContext,"",map,199);

    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        if(requestId==199){

        }
    }
}
