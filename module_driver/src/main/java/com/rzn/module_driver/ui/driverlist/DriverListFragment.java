package com.rzn.module_driver.ui.driverlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_driver.R;
import com.rzn.module_driver.R2;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;
import com.rzn.module_driver.ui.driver_identification.Driver_identificationActivity;
import com.rzn.module_driver.ui.driverordermessage.DriverOrderMessageActivity;
import com.rzn.module_driver.ui.jobOrder.myjoborder.MyjobOrderActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = "/driver/driverFargment")
public class DriverListFragment extends MVPBaseFragment<DriverListContract.View, DriverListPresenter> implements DriverListContract.View, OnLoadMoreListener, OnRefreshListener {

    View rootView;
    @BindView(R2.id.ll_jobScreeningType)
    AutoLinearLayout llJobScreeningType;
    @BindView(R2.id.ll_jobScreeningRegion)
    AutoLinearLayout llJobScreeningRegion;
    @BindView(R2.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R2.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private TextView tvStartGet;
    private DriverListAdapter driverListAdapter;
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
        //开始抢单按钮
        tvStartGet = (TextView) rootView.findViewById(R.id.tv_start_get);
        initLinistener();
        return rootView;
    }

    private void initLinistener() {
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        tvStartGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否认证机手
            LoginResponseBean  loginResponseBean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                if (!TextUtils.isEmpty(loginResponseBean.getHandlerId())) {
                    //已认证机手
                    startActivity(new Intent(mContext, DriverOrderMessageActivity.class));
                } else {
                    //未认证机手
                    startActivity(new Intent(mContext, Driver_identificationActivity.class));
                }

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public DriverListAdapter setAdapter(List<DriverGrabOrderInfo> list) {
        swipeTarget.setLayoutManager(new LinearLayoutManager(getActivity()));
        DriverListAdapter driverListAdapter = new DriverListAdapter(list);
        swipeTarget.setAdapter(driverListAdapter);
        driverListAdapter.setEmptyView(R.layout.driverorder_nullpager,(ViewGroup)swipeTarget.getParent());
        return driverListAdapter;
    }

    @Override
    public void recycleViewRestore() {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void onLoadMore() {
        recycleViewRestore();
    }

    @Override
    public void onRefresh() {
        recycleViewRestore();
    }


    @OnClick({R2.id.ll_jobScreeningType, R2.id.ll_jobScreeningRegion, R2.id.ll_obScreeningcapacity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.ll_jobScreeningType:
                break;
            case R2.id.ll_jobScreeningRegion:
                break;
            case R2.id.ll_obScreeningcapacity:
                break;
        }
    }
}
