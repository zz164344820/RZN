package com.rzn.module_driver.ui.joborderdetial;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobOrderDetialPresenter extends BasePresenterImpl<JobOrderDetialContract.View> implements JobOrderDetialContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void getData(String farmerTaskId) {
//        15：作业订单详情（机手）  已完成
//
//        请求地址：
//        http://1724l9l212.iask.in/farmHand/handler/taskInfo


        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("farmerTaskId", "farmerTaskId");
        reqData(mContext, "farmHand/handler/taskInfo", map, 111);
    }

    @Override
    public void getFarmerData(String farmerTaskId) {
        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("farmerTaskId", farmerTaskId);
        reqData(mContext, "farmHand/farmerTask/farmerTaskInfo", map, 222);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                JobOrderDetialBean jobOrderDetialBean = GsonUtils.gsonParseBean(gson, response.getResult(), JobOrderDetialBean.class);
                mView.getDataSuccess(jobOrderDetialBean);
                break;
            case 222:
                JobOrderDetialBean jobOrderDetialBeans = GsonUtils.gsonParseBean(gson, response.getResult(), JobOrderDetialBean.class);
                mView.getDataSuccess(jobOrderDetialBeans);
                break;
        }
    }
}
