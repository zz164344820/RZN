package com.rzn.module_driver.ui.driverordermessage;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_driver.ui.bean.OrederInfo;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverOrderMessageContract {
    interface View extends BaseView {
        void postSuccessed();

        void postFailed();

        void  setOrderInfo(List<OrederInfo> list);
    }

    interface Presenter extends BasePresenter<View> {
        void httpPost();
    }
}
