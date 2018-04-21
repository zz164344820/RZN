package com.rzn.module_farmer.applictaion;

import com.rzn.commonbaselib.applictaion.ApplicationDelegate;
import com.rzn.commonbaselib.applictaion.ViewManager;
import com.rzn.module_farmer.ui.farmerlist.FarmerListFragment;

/**
 * Created by zz on 2018/3/4.
 */

public class FarmerDelegate implements ApplicationDelegate {
    @Override
    public void onCreate() {
        ViewManager.getInstance().addFragment(0, FarmerListFragment.newInstance());
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
