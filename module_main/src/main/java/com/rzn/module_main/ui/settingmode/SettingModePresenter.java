package com.rzn.module_main.ui.settingmode;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SettingModePresenter extends BasePresenterImpl<SettingModeContract.View> implements SettingModeContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
