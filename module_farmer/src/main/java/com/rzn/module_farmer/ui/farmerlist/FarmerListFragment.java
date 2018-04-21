package com.rzn.module_farmer.ui.farmerlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_farmer.R;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerListFragment extends MVPBaseFragment<FarmerListContract.View, FarmerListPresenter> implements FarmerListContract.View {


    public static FarmerListFragment newInstance() {
        return new FarmerListFragment();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.farmer_fragment_farmerlist, container, false);
        return rootView;
    }
}
