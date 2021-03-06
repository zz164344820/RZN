package com.rzn.commonbaselib.applictaion;


import com.zyhealth.expertlib.LibApplication;

import java.util.ArrayList;
import java.util.List;


/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:Utils.getContext()，不允许其他写法；
 *
 * @author 2016/12/2 17:02
 * @version V1.0.0
 * @name BaseApplication
 */
public class BaseApplication extends LibApplication {

    public static final String ROOT_PACKAGE = "com.rzn";

    private static BaseApplication sInstance;

    private List<ApplicationDelegate> mAppDelegateList;


    public static BaseApplication getIns() {
        return sInstance;
    }
    List<String> pathList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
       /* addPath();
        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, ApplicationDelegate.class, pathList);
        for (ApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }*/
    }

    private void addPath() {
        pathList.add("com.rzn.module_main.applictaion");
        pathList.add("com.rzn.module_farmer.applictaion");
        pathList.add("com.rzn.module_driver.applictaion");
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        for (ApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (ApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (ApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }
}
