package com.rzn.njt.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rzn.njt.BuildConfig;
import com.zyhealth.expertlib.Constants;
import com.zyhealth.expertlib.LibApplication;
import com.zyhealth.expertlib.net.OkHttpLoader;

/**
 * Created by zz on 2018/1/12.
 */

public class RZNApplication extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Constants.SERVER = "production";//develop,QA，production,develop_yu
        OkHttpLoader.changServer();
        initArouter();
    }

    private void initArouter() {
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }




}
