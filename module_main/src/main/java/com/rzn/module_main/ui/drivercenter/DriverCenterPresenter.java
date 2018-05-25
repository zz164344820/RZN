package com.rzn.module_main.ui.drivercenter;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverCenterPresenter extends BasePresenterImpl<DriverCenterContract.View> implements DriverCenterContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
