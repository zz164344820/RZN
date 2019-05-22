package com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class VerifyMessageContract {
    interface View extends BaseView {

        void loginSuccess();

        void restoreTextView();

        void restoreClickTextView();

        void startRun();

//        void nextDataSuccess();
//
//        void nextDataFailed();

    }

    interface Presenter extends BasePresenter<View> {
        void getAuthCode(String phone);


        void nextData(Map<String, String> map);
    }
}
