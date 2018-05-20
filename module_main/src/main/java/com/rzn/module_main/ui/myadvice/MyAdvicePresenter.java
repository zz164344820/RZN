package com.rzn.module_main.ui.myadvice;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyAdvicePresenter extends BasePresenterImpl<MyAdviceContract.View> implements MyAdviceContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
