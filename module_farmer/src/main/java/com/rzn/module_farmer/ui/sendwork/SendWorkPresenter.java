package com.rzn.module_farmer.ui.sendwork;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.SendWorkBean;
import com.rzn.module_farmer.bean.WorkTypeBean;
import com.rzn.module_farmer.bean.WorkTypeObjBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendWorkPresenter extends BasePresenterImpl<SendWorkContract.View> implements SendWorkContract.Presenter {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void httpGetWorkType() {
        mView.showLoading(false,"");
        reqData(mContext, "farmHand/farmerTask/queryKind", null, 222);
    }

    @Override
    public void httpSendWork(String userId, String farmerTaskId, String name, String mobile, String address,
                             String kind, String kindType, String kindTypeId, String unitPrice, String areas, String flag, String flagNum,
                             String startDate, String endDate, String taskPlace, String remark, Province province,City city,County county
    ) {
        //请求提交发布作业接口
        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("farmerTaskId", farmerTaskId);
        map.put("name", name);
        map.put("mobile", mobile);
        map.put("address", address);
        map.put("kind", kind);
        map.put("kindType", kindType);
        map.put("kindTypeId", kindTypeId);
        map.put("unitPrice", unitPrice);
        map.put("areas", areas);
        map.put("flag", flag);
        map.put("flagNum", flagNum);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("taskPlace", taskPlace);
        map.put("remark", remark);
        map.put("provinceName", province.getName());
        map.put("provinceCode", province.getId()+"");
        map.put("cityName", city.getName());
        map.put("cityCode", city.getId()+"");
        map.put("areaName", county.getName());
        map.put("areaCode", county.getId()+"");
        reqData(mContext, "farmHand/farmerTask/updateSaveTaskToF", map, 111);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                SendWorkBean sendWorkBean = GsonUtils.gsonParseBean(gson, response.getResult(), SendWorkBean.class);
                mView.sendSuccess(sendWorkBean);
                break;
            case 222:
                Type type = new TypeToken<List<WorkTypeBean>>(){}.getType();
                List<WorkTypeBean> list= gson.fromJson(gson.toJson(response.getResult()), type);
                mView.getWorkTypeSuccess(list);
                break;
        }
    }

}
