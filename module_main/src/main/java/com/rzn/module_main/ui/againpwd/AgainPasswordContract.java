package com.rzn.module_main.ui.againpwd;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.againpwd.bean.AgainPwdBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AgainPasswordContract {
    interface View extends BaseView {

        void setNewPasswordSuccess(AgainPwdBean againPwdBean);

        void setNewPasswordFailed();

    }

    interface Presenter extends BasePresenter<View> {
        void setNewPassword(Map<String, String> map);
    }
}
