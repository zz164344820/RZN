package com.rzn.module_farmer.ui.farmerdriverdetial;


import android.content.Intent;

import com.jaeger.library.StatusBarUtil;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.AppointmentDriverBean;
import com.rzn.module_farmer.bean.DriverDetialMessageBean;
import com.rzn.module_farmer.bean.IsHasSendPostOreder;
import com.rzn.module_farmer.ui.sendwork.SendWorkActivity;
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
    public void getFarmerOreder() {
        Map<String ,String >  map = new HashMap<>();
        LoginResponseBean  bean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        map.put("userId",bean.getUserId());
        reqData(mContext, "farmerTask/existTask",map,123);
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

            case  123:
                IsHasSendPostOreder  isHasSendPostOreder = GsonUtils.gsonParseBean(gson,response.getResult(), IsHasSendPostOreder.class);
                if("ture".equals(isHasSendPostOreder.getFlag())){

                    //跳转道有农民发布的列表页面
                }else{
                   //todo 跳转发布信息页面
                    mContext.startActivity(new Intent(mContext, SendWorkActivity.class));
                }

        }
    }
}
