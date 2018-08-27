package com.rzn.module_main.ui.personalinfo;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalInfoContract {
    interface View extends BaseView {
        void setInfo(UserInfo userInfo);
        void savePic();
        
    }

    interface  Presenter extends BasePresenter<View> {
        void setUserInfo(Map map);
        
    }
}
