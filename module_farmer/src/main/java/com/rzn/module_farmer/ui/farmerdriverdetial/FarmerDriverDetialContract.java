package com.rzn.module_farmer.ui.farmerdriverdetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_farmer.bean.DriverDetialMessageBean;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerDriverDetialContract {
    interface View extends BaseView {
        //获取机手信息成功
        void driverMessageSuccess(DriverDetialMessageBean driverDetialMessageBean);



        //预约机手成功
        void appointmentSuccess();



    }

    interface Presenter extends BasePresenter<View> {

        //获取机手信息成功
        void httpDriverMessage(String handlerId);

        /*获取农民发布订单list*/
        void  getFarmerOreder();

    }
}
