package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;

/**
 * 致医健康 MVPPlugin
 * @author zz
 */
public class CommodityListFragment extends MVPBaseFragment<CommodityListContract.View, CommodityListPresenter> implements CommodityListContract.View,OnLoadMoreListener,OnRefreshListener {

    View rootView;
    private String mTitle;
    RecyclerView recyclerView;
    SwipeToLoadLayout swipeToLoadLayout;

    public static CommodityListFragment getInstance(String title) {
        CommodityListFragment sf = new CommodityListFragment();
        sf.mTitle = title;
        return sf;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_commoditlist, container, false);
        mPresenter.onCreate();
        return rootView;
    }

    @Override
    public void initView() {
        super.initView();
        recyclerView=(RecyclerView) rootView.findViewById(R.id.swipe_target);
        swipeToLoadLayout=(SwipeToLoadLayout) rootView.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
