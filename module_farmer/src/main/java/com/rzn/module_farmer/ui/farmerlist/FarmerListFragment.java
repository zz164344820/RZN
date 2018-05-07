package com.rzn.module_farmer.ui.farmerlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.ui.farmerdriverdetial.FarmerDriverDetialActivity;
import com.rzn.module_farmer.ui.sendwork.SendWorkActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = "/farmer/farmerFargment")
public class FarmerListFragment extends MVPBaseFragment<FarmerListContract.View, FarmerListPresenter> implements FarmerListContract.View ,OnLoadMoreListener,OnRefreshListener{


    private View rootView;
    private RecyclerView swipeTarget;
    private SwipeToLoadLayout swipeToLoadLayout;
    private TextView tvStartGet;

    public static FarmerListFragment newInstance() {
        return new FarmerListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.farmer_fragment_farmerlist, container, false);
        initViews();
        mPresenter.onCreate();
        initLinistener();
        return rootView;
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
        List<String> list = new ArrayList<>();
        list.add("s");
        list.add("s");
        list.add("s");
        list.add("s");
        list.add("s");
        FarmerListAdapter farmerListAdapter = new FarmerListAdapter(R.layout.farmer_item_farmerlist, list);
        swipeTarget.setAdapter(farmerListAdapter);

        farmerListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                startActivity(new Intent(mContext, FarmerDriverDetialActivity.class));
            }
        });

        farmerListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
