package com.rzn.module_main.ui.login;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginContract {

    interface View extends BaseView {

        void loginSuccess();

        void  restoreTextView();
        void  restoreClickTextView();
        void  startRun();
    }

    interface  Presenter extends BasePresenter<View> {
        void login(String phone ,String code);
        void getAuthCode(String phone);
    }
}
