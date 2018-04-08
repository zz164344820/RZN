package com.rzn.module_driver.applictaion;

import com.rzn.commonbaselib.applictaion.ApplicationDelegate;
import com.rzn.commonbaselib.applictaion.ViewManager;
import com.rzn.module_driver.ui.driverlist.DriverListFragment;

/**
 * Created by zz on 2018/3/4.
 */

public class DriverDelegate implements ApplicationDelegate {
    @Override
    public void onCreate() {
        ViewManager.getInstance().addFragment(0, DriverListFragment.newInstance());
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}
