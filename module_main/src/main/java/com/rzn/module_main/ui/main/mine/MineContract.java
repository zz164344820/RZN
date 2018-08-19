package com.rzn.module_main.ui.main.mine;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.drivercenter.DriverBean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MineContract {
    interface View extends BaseView {
      void   getDataSuccess(DriverBean driverBean);
    }

    interface  Presenter extends BasePresenter<View> {
        void getDriverData();
    }
}
