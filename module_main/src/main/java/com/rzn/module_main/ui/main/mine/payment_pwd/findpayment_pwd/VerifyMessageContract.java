package com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class VerifyMessageContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        void getAuthCode(String phone);
    }
}
