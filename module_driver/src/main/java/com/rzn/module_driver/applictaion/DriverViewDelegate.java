package com.rzn.module_driver.applictaion;

import android.view.View;

import com.rzn.commonbaselib.applictaion.IViewDelegate;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_driver.ui.driverlist.DriverListFragment;

/**
 * Created by zz on 2018/3/4.
 */

public class DriverViewDelegate implements IViewDelegate {
    @Override
    public MVPBaseFragment getFragment(String name) {
        return DriverListFragment.newInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
