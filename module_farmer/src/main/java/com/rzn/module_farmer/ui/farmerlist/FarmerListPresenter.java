package com.rzn.module_farmer.ui.farmerlist;

import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.WorkTypeBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerListPresenter extends BasePresenterImpl<FarmerListContract.View> implements FarmerListContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void httpLoadDriverMessage(String userId, String page,String areaCode,String kindTypeId) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("page", page);
        map.put("areaCode",areaCode);
        map.put("kindTypeId",kindTypeId);
        mView.showLoading(false,"");
        reqData(mContext, "farmHand/farmerTask/handlerList", map, 111);
    }

    @Override
    public void httpGetWorkType() {
        mView.showLoading(false,"");
        reqData(mContext, "farmHand/farmerTask/queryKind", null, 222);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                Type type = new TypeToken<List<FarmerDriverMessageBean>>(){}.getType();
                List<FarmerDriverMessageBean> list= gson.fromJson(gson.toJson(response.getResult()), type);
                mView.loadDriverMessageSuccessed(list);
                break;
            case 222:
                Type type2 = new TypeToken<List<WorkTypeBean>>(){}.getType();
                List<WorkTypeBean> list2= gson.fromJson(gson.toJson(response.getResult()), type2);
                mView.getWorkTypeSuccess(list2);
                break;

        }
    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
        mView.recycleViewRestore();
    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
        mView.recycleViewRestore();
    }
}
