package com.rzn.module_farmer.ui.selectaddress;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SelectAddressPresenter extends BasePresenterImpl<SelectAddressContract.View> implements SelectAddressContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
