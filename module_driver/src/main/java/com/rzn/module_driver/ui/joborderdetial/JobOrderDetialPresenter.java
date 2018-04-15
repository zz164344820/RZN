package com.rzn.module_driver.ui.joborderdetial;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class JobOrderDetialPresenter extends BasePresenterImpl<JobOrderDetialContract.View> implements JobOrderDetialContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
