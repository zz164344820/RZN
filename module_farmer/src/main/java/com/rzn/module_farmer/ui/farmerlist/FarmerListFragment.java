package com.rzn.module_farmer.ui.farmerlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.WorkTypeBean;
import com.rzn.module_farmer.ui.farmerdriverdetial.FarmerDriverDetialActivity;
import com.rzn.module_farmer.ui.sendwork.SendPopUpWindow;
import com.rzn.module_farmer.ui.sendwork.SendWorkActivity;

import java.util.ArrayList;
import java.util.List;

import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = "/farmer/farmerFargment")
public class FarmerListFragment extends MVPBaseFragment<FarmerListContract.View, FarmerListPresenter> implements FarmerListContract.View, OnLoadMoreListener, OnRefreshListener ,OnAddressSelectedListener{


    private View rootView;
    private RecyclerView swipeTarget;
    private SwipeToLoadLayout swipeToLoadLayout;
    private TextView tvStartGet;
    private List<FarmerDriverMessageBean> list = new ArrayList<>();
    private FarmerListAdapter farmerListAdapter;
    BottomDialog bottomDialog;
    String kindTypeId="";
    Province province;//省
    County county;    //市
    City  city;       //县
    int pager=0;
    LoginResponseBean loginResponseBean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
    public static FarmerListFragment newInstance() {
        return new FarmerListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.farmer_fragment_farmerlist, container, false);
        mPresenter.onCreate();
        initViews();
        initData();
        initLinistener();
        return rootView;
    }

    /**
     * 初始化网络数据
     */
    private void initData() {


        mPresenter.httpLoadDriverMessage(loginResponseBean.getUserId(), (++pager)+"", county==null?"":county.getId()+"", kindTypeId);
    }

    private void initLinistener() {
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        tvStartGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SendWorkActivity.class));
            }
        });
    }

    //初始化view
    private void initViews() {
        tvStartGet = (TextView) rootView.findViewById(R.id.tv_start_get);
        swipeTarget = (RecyclerView) rootView.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout) rootView.findViewById(R.id.swipeToLoadLayout);
        swipeTarget.setLayoutManager(new LinearLayoutManager(getActivity()));
        farmerListAdapter = new FarmerListAdapter(list);
        farmerListAdapter.setEmptyView(R.layout.farmerorder_nullpager,(ViewGroup)swipeTarget.getParent());
        swipeTarget.setAdapter(farmerListAdapter);
        bottomDialog = new BottomDialog(getActivity());
        bottomDialog.setOnAddressSelectedListener(this);

        farmerListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent  = new Intent(getContext(),FarmerDriverDetialActivity.class);
                intent.putExtra("handlerId",list.get(position).getHandlerId());
                startActivity(intent);
            }
        });

        farmerListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        rootView.findViewById(R.id.tv_orderArea).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.show();
            }
        });

        rootView.findViewById(R.id.tv_orderType).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.httpGetWorkType();
            }
        });
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
                kindTypeId = worTypeList.get(position).getTypeArray().get(typePosition).getKindId();
                LoginResponseBean  loginResponseBean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                list.clear();
                pager=0;
                mPresenter.httpLoadDriverMessage(loginResponseBean.getUserId(), (++pager)+"", "", kindTypeId);
            }
        });
        if (sendPopUpWindow.isShowing()) {
            return;
        }
        sendPopUpWindow.showAtLocation(tvStartGet, Gravity.BOTTOM, 0, 0);
    }
    @Override
    public void onLoadMore() {
        mPresenter.httpLoadDriverMessage(loginResponseBean.getUserId(), (++pager)+"", county==null?"":county.getId()+"", kindTypeId);
    }

    @Override
    public void onRefresh() {
        list.clear();
        pager=0;
        mPresenter.httpLoadDriverMessage(loginResponseBean.getUserId(), (++pager)+"", "", "");
    }

    /**
     * 加载数据成功
     */
    @Override
    public void loadDriverMessageSuccessed(List<FarmerDriverMessageBean> list1) {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
        list.addAll(list1);
        farmerListAdapter.notifyDataSetChanged();
    }

    /**
     * 加载数据失败
     */
    @Override
    public void loadDriverMessageFailed() {

    }

    @Override
    public void recycleViewRestore() {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        this.province = province;
        this.city=city;
        this.county =county;
        bottomDialog.dismiss();
        LoginResponseBean  loginResponseBean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        list.clear();
        pager=0;
        mPresenter.httpLoadDriverMessage(loginResponseBean.getUserId(), (++pager)+"", county.getId()+"", "");
    }
}
