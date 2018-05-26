package com.rzn.module_driver.ui.posting;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PostingContract {
    interface View extends BaseView {
        void getListSuccess( List<DriverGrabOrderInfo> tempList);
    }

    interface  Presenter extends BasePresenter<View> {
        void getList();
    }
}
