package com.rzn.module_farmer.ui.sendworksuccess;


import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.RecommendDriver;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SendWorkSuccessPresenter extends BasePresenterImpl<SendWorkSuccessContract.View> implements SendWorkSuccessContract.Presenter{

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void httpLoadDriverMessage(String farmerTaskId) {
        mView.showLoading(false,"");
        Map<String,String> map = new HashMap<>();
        map.put("farmerTaskId",farmerTaskId);
        reqData(mContext,"farmHand/farmerTask/recommendHandler",map,145);

    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        if(requestId==145){
            Type type = new TypeToken<List<RecommendDriver>>(){}.getType();
            List<RecommendDriver> list= gson.fromJson(gson.toJson(response.getResult()), type);
            mView.loadDriverMessageSuccessed(list);
        }
    }
}
