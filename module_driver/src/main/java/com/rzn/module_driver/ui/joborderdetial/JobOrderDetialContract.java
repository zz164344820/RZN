package com.rzn.module_driver.ui.joborderdetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobOrderDetialContract {
    interface View extends BaseView {
        void getDataSuccess(JobOrderDetialBean jobOrderDetialBean);

        void getDataFaile();

    }

    interface Presenter extends BasePresenter<View> {
        void getData(String farmerTaskId);
    }
}
