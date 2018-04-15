package com.rzn.module_driver.ui.driverlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.module_driver.R2;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.driver_identification.Driver_identificationActivity;
import com.rzn.module_driver.ui.jobOrder.myjoborder.MyjobOrderActivity;
import com.rzn.module_driver.ui.jobdetails.JobdetailsActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

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
    private TextView tvStartGet;
    private DriverListAdapter driverListAdapter;

    public static DriverListFragment newInstance() {
        return new DriverListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.driver_fragment_driverlist, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter.onCreate();
        //开始抢单按钮
        tvStartGet = (TextView) rootView.findViewById(R.id.tv_start_get);
        initLinistener();
        return rootView;
    }

    private void initLinistener() {
        tvStartGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否认证机手
                if (false) {
                    //已认证机手
                    startActivity(new Intent(mContext, MyjobOrderActivity.class));
                } else {
                    //未认证机手
                    startActivity(new Intent(mContext, Driver_identificationActivity.class));
                }

            }
        });
    }

    @Override
    public void initView() {
        super.initView();
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


    //请求抢单列表成功
    @Override
    public void getListSuccess() {

    }

    //请求抢单列表失败
    @Override
    public void getListFailed() {

    }

    @Override
    public DriverListAdapter setAdapter(List<String> list) {
        rcDriverList.setLayoutManager(new LinearLayoutManager(getActivity()));
        DriverListAdapter driverListAdapter = new DriverListAdapter(R.layout.driver_item_driverlists, list);
        rcDriverList.setAdapter(driverListAdapter);
        return driverListAdapter;
    }
}
