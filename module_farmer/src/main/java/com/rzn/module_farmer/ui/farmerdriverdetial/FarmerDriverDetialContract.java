package com.rzn.module_farmer.ui.farmerdriverdetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerDriverDetialContract {
    interface View extends BaseView {
        //预约机手成功
        void appointmentSuccess();

        //预约机手失败
        void appointmentFailed();

    }

    interface Presenter extends BasePresenter<View> {
        void httpAppointmentDriver(String farmerTaskId, String handlerId);

    }
}
