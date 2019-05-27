package com.rzn.module_main.ui.mywallet;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.mywallet.bean.MyWalletBean;
import com.rzn.module_main.ui.setting.bean.SettingPasswordBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyWalletContract {
    interface View extends BaseView {

        void getWalletDetialSuccess(MyWalletBean myWalletBean);

        void getWalletDetialFailed();

        void getIsPasswordDataSuccess(SettingPasswordBean settingPasswordBean);

        void getIsPasswordDataFailed();
    }

    interface Presenter extends BasePresenter<View> {

        void getWalletDetial(Map<String, String> map);

        void getIsPasswordData(Map<String,String> map);
    }
}
