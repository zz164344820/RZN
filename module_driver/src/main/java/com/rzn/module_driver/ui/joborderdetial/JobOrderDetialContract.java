package com.rzn.module_driver.ui.joborderdetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobOrderDetialContract {
    interface View extends BaseView {
        void getDataSuccess(JobOrderDetialBean jobOrderDetialBean);

        void getDataFaile();

        void cancelPostSuccess();


        void finishWorkSuccess();

    }

    interface Presenter extends BasePresenter<View> {
        void getData(String farmerTaskId);

        void getFarmerData(String farmerTaskId);

        void cancelPost(Map<String, String> map);

        void finishWork(Map<String, String> map);

    }
}
