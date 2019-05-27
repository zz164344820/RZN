package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.sellagriculturalgoods.SellAgriculturalGoodsActivity;
import com.rzn.module_main.ui.sellagriculturalgoods.goodsinfo.GoodsInfoActitiy;
import com.zyhealth.expertlib.utils.MLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 致医健康 MVPPlugin
 * @author zz
 */
public class CommodityListFragment extends MVPBaseFragment<CommodityListContract.View, CommodityListPresenter> implements CommodityListContract.View,OnLoadMoreListener,OnRefreshListener {

    View rootView;
    private String mTitle;
    RecyclerView recyclerView;
    SwipeToLoadLayout swipeToLoadLayout;
    List<CommodityListBean> commodityList = new ArrayList<>();
    int pager=1;
    ClassifyAdapter  classifyAdapter;
    public static CommodityListFragment getInstance(String title) {

        CommodityListFragment sf = new CommodityListFragment();
        sf.mTitle = title;
        return sf;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_commoditlist, container, false);
        mPresenter.onCreate();
        MLog.e(mTitle);
        return rootView;
    }

    @Override
    public void initView() {
        super.initView();
        recyclerView=(RecyclerView) rootView.findViewById(R.id.swipe_target);
        swipeToLoadLayout=(SwipeToLoadLayout) rootView.findViewById(R.id.swipeToLoadLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    private void setAdapter() {
        classifyAdapter =new ClassifyAdapter(R.layout.classifyadapter,commodityList);
        recyclerView.setAdapter(classifyAdapter);
        classifyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(),GoodsInfoActitiy.class);
                intent.putExtra("url",commodityList.get(position).getUrl());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onLoadMore() {
        SellAgriculturalGoodsActivity activity=(SellAgriculturalGoodsActivity) getActivity();
        mPresenter.getCommodityList((++pager)+"",activity.getType(),activity.getquery());
    }

    @Override
    public void onRefresh() {
      commodityList.clear();
      pager=1;
      SellAgriculturalGoodsActivity activity=(SellAgriculturalGoodsActivity) getActivity();
      mPresenter.getCommodityList((++pager)+"",activity.getType(),activity.getquery());
    }

    public void onRefresh(int type,String name ) {
        pager=1;
        commodityList.clear();
        mPresenter.getCommodityList((++pager)+"",type,name);
    }

    @Override
    public void refreshList(List<CommodityListBean> list) {

        commodityList.addAll(list);
        try {
           classifyAdapter.notifyDataSetChanged();

        }catch (Exception e){
            MLog.e(e.getMessage());
        }
        recycleViewRestore();
    }

    @Override
    public void recycleViewRestore() {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }
}
