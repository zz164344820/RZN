package com.rzn.module_main.ui.drivercenter;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverCenterContract {
    interface View extends BaseView {
        void getDataSuccess(DriverBean driverBean);
    }

    interface  Presenter extends BasePresenter<View> {
        void getDriverData(Map<String, String> map);
    }
}
