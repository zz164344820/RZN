package com.rzn.module_main.ui.driverhome;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverHomePresenter extends BasePresenterImpl<DriverHomeContract.View> implements DriverHomeContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
