package com.rzn.module_driver.ui.posting;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PostingPresenter extends BasePresenterImpl<PostingContract.View> implements PostingContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
