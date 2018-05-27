package com.rzn.module_driver.ui.driverlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
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
import com.rzn.commonbaselib.utils.SelectStatePopWindow;
import com.rzn.module_driver.R;
import com.rzn.module_driver.R2;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;
import com.rzn.module_driver.ui.bean.WorkTypeBean;
import com.rzn.module_driver.ui.driver_identification.Driver_identificationActivity;
import com.rzn.module_driver.ui.driver_identification.SendPopUpWindow;
import com.rzn.module_driver.ui.drivermaksure.DriverMakeSureActivity;
import com.rzn.module_driver.ui.driverordermessage.DriverOrderMessageActivity;
import com.rzn.module_driver.ui.jobOrder.myjoborder.MyjobOrderActivity;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;
import cn.finalteam.galleryfinal.GalleryFinal;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = "/driver/driverFargment")
public class DriverListFragment extends MVPBaseFragment<DriverListContract.View, DriverListPresenter> implements DriverListContract.View, OnLoadMoreListener, OnRefreshListener,OnAddressSelectedListener {

    View rootView;
    @BindView(R2.id.ll_rootView)
    AutoLinearLayout ll_rootView;
    @BindView(R2.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R2.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private TextView tvStartGet;
    private DriverListAdapter driverListAdapter;
    Unbinder unbinder;
    List<DriverGrabOrderInfo> list;
    BottomDialog bottomDialog;
    Province province;
    County county;
    City  city;
    final SelectMatchingPopWindow[] window = new SelectMatchingPopWindow[1];

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

    @Override
    public void initView() {
        super.initView();
        bottomDialog = new BottomDialog(getActivity());
        bottomDialog.setOnAddressSelectedListener(this);
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


        rootView.findViewById(R.id.ll_jobScreeningType).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.httpGetWorkType();
            }
        });

        rootView.findViewById(R.id.ll_jobScreeningRegion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.show();
            }
        });

        rootView.findViewById(R.id.ll_obScreeningcapacity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectPic(window);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 获取作业类型成功
     */
    @Override
    public void getWorkTypeSuccess(final List<WorkTypeBean> worTypeList) {

        //弹出选择作业类型弹窗
        SendPopUpWindow sendPopUpWindow = new SendPopUpWindow(getActivity(), worTypeList);
        sendPopUpWindow.setOnListener(new SendPopUpWindow.OnClickListener() {
            @Override
            public void onClick(int position, int typePosition) {
                //获取作业类型
               String   kindTypeId = worTypeList.get(position).getTypeArray().get(typePosition).getKindId();
                list.clear();
                Map<String,String> map =new HashMap<>();
                map.put("filterKindType",kindTypeId);
                mPresenter.getDriverList(map);
            }
        });
        if (sendPopUpWindow.isShowing()) {
            return;
        }
        sendPopUpWindow.showAtLocation(tvStartGet, Gravity.BOTTOM, 0, 0);
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
        this.list =list;
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



    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        this.province = province;
        this.city=city;
        this.county =county;
        bottomDialog.dismiss();
        list.clear();
        Map<String,String> map =new HashMap<>();
        map.put("filterTaskPlace",county.getId()+"");
        mPresenter.getDriverList(map);
    }

    private void showSelectPic(final SelectMatchingPopWindow[] window) {
        window[0] = new SelectMatchingPopWindow(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map =new HashMap<>();
                if (v.getId() == R.id.tv_closeRange) {
                    window[0].dismiss();
                    map.put("recently","1");
                } else if (v.getId() == R.id.tv_normal) {
                    window[0].dismiss();
                    map.put("recently","0");
                }
                list.clear();
                mPresenter.getDriverList(map);
            }
        });

        window[0].showAtLocation(ll_rootView, Gravity.BOTTOM, 10, 10);
    }
}
