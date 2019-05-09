package com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class VerifyMessagePresenter extends BasePresenterImpl<VerifyMessageContract.View> implements VerifyMessageContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }

    @Override
    public void getAuthCode(String phone) {

    }
}
