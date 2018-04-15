package com.rzn.module_driver.ui.drivermaksure;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverMakeSurePresenter extends BasePresenterImpl<DriverMakeSureContract.View> implements DriverMakeSureContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
