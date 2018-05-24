package com.rzn.module_driver.ui.jobOrder.await_job;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_driver.R;

/**
 * MVPPlugin
 *  等待作业订单
 */

public class Await_jobFragment extends MVPBaseFragment<Await_jobContract.View, Await_jobPresenter> implements Await_jobContract.View {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout.act_driver_attestation, container, false);
//        mPresenter.onCreate();
////        initViews();
////        initData();
//        return rootView;

        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
