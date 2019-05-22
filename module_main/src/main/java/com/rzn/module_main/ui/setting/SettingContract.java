package com.rzn.module_main.ui.setting;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.setting.bean.SettingPasswordBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingContract {
    interface View extends BaseView {

        void getIsPasswordDataSuccess(SettingPasswordBean settingPasswordBean);

        void getIsPasswordDataFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void getIsPasswordData(Map<String, String> map);
    }
}
