package com.rzn.module_driver.ui.driverordermessage;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverOrderMessagePresenter extends BasePresenterImpl<DriverOrderMessageContract.View> implements DriverOrderMessageContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
