package com.rzn.njt.application;

import android.app.Notification;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.applictaion.BaseApplication;
import com.rzn.module_main.ui.login.LoginActivity;
import com.rzn.njt.BuildConfig;
import com.rzn.njt.R;
import com.tencent.bugly.Bugly;
import com.zyhealth.expertlib.Constants;
import com.zyhealth.expertlib.LibApplication;
import com.zyhealth.expertlib.net.OkHttpLoader;
import com.zyhealth.expertlib.utils.MLog;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;


/**
 * Created by zz on 2018/1/12.
 */

public class RZNApplication extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Constants.SERVER = "develop";//develop,QA，production,develop_yu
        OkHttpLoader.changServer();
        initArouter();
        initJPush();
        initBugly();

    }

    private void initBugly() {
        Bugly.init(getApplicationContext(), "082e2de220", false);
    }

    private void initJPush() {
        JPushInterface.init(this);
        JPushInterface.setDebugMode(false);
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
