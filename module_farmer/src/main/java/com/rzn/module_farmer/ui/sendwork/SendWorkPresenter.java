package com.rzn.module_farmer.ui.sendwork;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_farmer.bean.SendWorkBean;
import com.rzn.module_farmer.bean.WorkTypeBean;
import com.rzn.module_farmer.bean.WorkTypeObjBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendWorkPresenter extends BasePresenterImpl<SendWorkContract.View> implements SendWorkContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext, "Test/index", null, 111);
    }

    @Override
    public void httpGetWorkType() {
        reqData(mContext, "farmHand/farmerTask/queryKind", null, 222);
    }

    @Override
    public void httpSendWork(String userId, String farmerTaskId, String name, String mobile, String address,
                             String kind, String kindType, String kindTypeId, String unitPrice, String areas, String flag, String flagNum,
                             String startDate, String endDate, String taskPlace, String remark, String provinceName, String provinceCode,
                             String cityName, String cityCode, String areaName, String areaCode
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
        map.put("provinceName", provinceName);
        map.put("provinceCode", provinceCode);
        map.put("cityName", cityName);
        map.put("cityCode", cityCode);
        map.put("areaName", areaName);
        map.put("areaCode", areaCode);

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

                List<WorkTypeBean> list1 = GsonUtils.gsonParseList(gson, response.getResult());
                List<WorkTypeObjBean> list = list1.get(0).getTypeArray();
//                List<WorkTypeObjBean> list = GsonUtils.gsonParseList(gson, list1);
//                WorkTypeObjBean workTypeObjBean = GsonUtils.gsonParseBean(gson, list.get(0), WorkTypeObjBean.class);
//                WorkTypeBean workTypeBean = GsonUtils.gsonParseBean(gson, response.getResult(),WorkTypeBean.class);
                mView.getWorkTypeSuccess(list);
                break;
        }
    }
}
