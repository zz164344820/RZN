package com.rzn.module_driver.ui.driverlist;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverListContract {
    interface View extends BaseView {
        //获取抢单列表成功
        void getListSuccess();

        //获取抢单列表失败
        void getListFailed();

    }

    interface Presenter extends BasePresenter<View> {
        //获取抢单列表
        void getList(String userId, String farmerId);
    }
}
