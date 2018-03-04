package com.rzn.module_driver.ui.driverlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_driver.R;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverListFragment extends MVPBaseFragment<DriverListContract.View, DriverListPresenter> implements DriverListContract.View {

    View  rootView;
    public static DriverListFragment newInstance() {
        return new DriverListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.driver_fragment_driverlist,container,false);
        return rootView;
    }
}
