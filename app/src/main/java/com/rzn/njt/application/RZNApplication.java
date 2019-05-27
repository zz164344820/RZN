package com.rzn.njt.application;

import android.app.ActivityManager;
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
import com.v5kf.client.lib.Logger;
import com.v5kf.client.lib.V5ClientAgent;
import com.v5kf.client.lib.V5ClientConfig;
import com.v5kf.client.lib.callback.V5InitCallback;
import com.zyhealth.expertlib.Constants;
import com.zyhealth.expertlib.LibApplication;
import com.zyhealth.expertlib.net.OkHttpLoader;
import com.zyhealth.expertlib.utils.MLog;

import java.util.List;

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
        initV5();
    }

    private void initV5() {

        if (isMainProcess()) { // 判断为主进程，在主进程中初始化，多进程同时初始化可能导致不可预料的后果
            // 初始化V5 SDK
            V5ClientConfig.FILE_PROVIDER = "com.rzn.njt.fileprovider";
            V5ClientAgent.init(this, "163975", "28087080142b6", new V5InitCallback() {

                @Override
                public void onSuccess(String response) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onFailure(String response) {
                    // TODO Auto-generated method stub
                }
            });
        }
    }




    public boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
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
