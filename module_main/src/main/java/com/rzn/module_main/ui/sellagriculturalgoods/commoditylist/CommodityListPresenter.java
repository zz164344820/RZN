package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 致医健康 MVPPlugin
 * @author zz
 */
public class CommodityListPresenter extends BasePresenterImpl<CommodityListContract.View> implements CommodityListContract.Presenter{


    @Override
    public void onCreate() {
        super.onCreate();
        getCommodityList("1",0,"");

    }

    @Override
    public void getCommodityList(String page,int type ,String name ) {

        Map<String,String> map = new HashMap<>();
        map.put("goodsName",name);
        map.put("page",page);
        map.put("rows","20");
        map.put("goodsBreedType",type==0?"0":type+"");
        reqData(mContext,"/boss/queryGoodsList",map,199);

    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        if(requestId==199){
            ComodityBean comodityBean = gson.fromJson(gson.toJson(response.getResult()), ComodityBean.class);
            List<CommodityListBean> list = comodityBean.getRows();
            mView.refreshList(list);
        }
    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
        if(requestId==199){
            mView.recycleViewRestore();
        }
    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
        if(requestId==199){
           mView.recycleViewRestore();
        }
    }
}
