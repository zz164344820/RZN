package com.rzn.module_main.ui.welcome;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.ui.main.home.HeWeather6;
import com.rzn.module_main.ui.main.home.WeaterList;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyhealth.expertlib.utils.MLog;

import okhttp3.Call;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WelcomePresenter extends BasePresenterImpl<WelcomeContract.View> implements WelcomeContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
    }


}
