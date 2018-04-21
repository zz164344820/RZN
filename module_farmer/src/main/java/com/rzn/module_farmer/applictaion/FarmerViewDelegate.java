package com.rzn.module_farmer.applictaion;

import android.view.View;

import com.rzn.commonbaselib.applictaion.IViewDelegate;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_farmer.ui.farmerlist.FarmerListFragment;

/**
 * Created by zz on 2018/3/4.
 */

public class FarmerViewDelegate implements IViewDelegate {
    @Override
    public MVPBaseFragment getFragment(String name) {
        return FarmerListFragment.newInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
