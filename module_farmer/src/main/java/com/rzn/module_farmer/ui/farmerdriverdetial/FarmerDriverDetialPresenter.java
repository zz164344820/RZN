package com.rzn.module_farmer.ui.farmerdriverdetial;


import com.jaeger.library.StatusBarUtil;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.AppointmentDriverBean;
import com.rzn.module_farmer.bean.DriverDetialMessageBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 农户端机手详情
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerDriverDetialPresenter extends BasePresenterImpl<FarmerDriverDetialContract.View> implements FarmerDriverDetialContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
        StatusBarUtil.setTransparent(mContext);
    }

    //预约机手
    @Override
    public void httpAppointmentDriver(String farmerTaskId, String handlerId) {

        Map<String, String> map = new HashMap<>();
        map.put("farmerTaskId", farmerTaskId);
        map.put("handlerId", handlerId);
        reqData(mContext, "farmHand/farmerTask/confirmAppoint", map, 111);

    }

    //获取机手信息
    @Override
    public void httpDriverMessage(String handlerId) {
        Map<String, String> map = new HashMap<>();
        map.put("handlerId", handlerId);
        reqData(mContext, "farmHand/handler/queryhandlerInfo", null, 222);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {

            case 111:
                AppointmentDriverBean appointmentDriverBean = GsonUtils.gsonParseBean(gson, response.getResult(), AppointmentDriverBean.class);
                mView.appointmentSuccess();
                break;
            case 222:
                DriverDetialMessageBean driverDetialMessageBean = GsonUtils.gsonParseBean(gson, response.getResult(), DriverDetialMessageBean.class);
                mView.driverMessageSuccess(driverDetialMessageBean);
                break;
        }
    }
}
