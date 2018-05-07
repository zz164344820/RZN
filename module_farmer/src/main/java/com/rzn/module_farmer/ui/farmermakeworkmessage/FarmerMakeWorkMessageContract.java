package com.rzn.module_farmer.ui.farmermakeworkmessage;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerMakeWorkMessageContract {
    interface View extends BaseView {
        //确认预约成功
        void configSuccess();

        //确认预约失败
        void configFailed();

    }

    interface Presenter extends BasePresenter<View> {
        //网络请求确认预约机手
        void httpConfig(String farmerTaskId,String handlerId);
    }
}
