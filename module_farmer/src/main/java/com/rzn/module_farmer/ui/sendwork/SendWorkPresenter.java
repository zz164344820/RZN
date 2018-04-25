package com.rzn.module_farmer.ui.sendwork;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SendWorkPresenter extends BasePresenterImpl<SendWorkContract.View> implements SendWorkContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
