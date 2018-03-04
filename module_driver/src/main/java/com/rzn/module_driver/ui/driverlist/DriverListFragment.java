package com.rzn.module_driver.ui.driverlist;


import com.rzn.commonbaselib.mvp.MVPBaseFragment;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverListFragment extends MVPBaseFragment<DriverListContract.View, DriverListPresenter> implements DriverListContract.View {

    public static DriverListFragment newInstance() {
        return new DriverListFragment();
    }
}
