package com.rzn.module_driver.ui.driverlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.module_driver.R2;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_driver.R;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverListFragment extends MVPBaseFragment<DriverListContract.View, DriverListPresenter> implements DriverListContract.View {

    View rootView;
    @BindView(R2.id.ll_jobScreeningType)
    AutoLinearLayout llJobScreeningType;
    @BindView(R2.id.ll_jobScreeningRegion)
    AutoLinearLayout llJobScreeningRegion;
    @BindView(R2.id.rc_driverList)
    RecyclerView rcDriverList;
    Unbinder unbinder;

    public static DriverListFragment newInstance() {
        return new DriverListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.driver_fragment_driverlist, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter.onCreate();
        return rootView;
    }

    @Override
    public void initView() {
        super.initView();
        rcDriverList.setLayoutManager(new LinearLayoutManager(getActivity()));
       // rcDriverList.setAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.ll_jobScreeningType, R2.id.ll_jobScreeningRegion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.ll_jobScreeningType:
                break;
            case R2.id.ll_jobScreeningRegion:
                break;
        }
    }
}
