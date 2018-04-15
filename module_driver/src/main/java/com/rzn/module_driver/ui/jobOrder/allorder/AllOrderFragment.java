package com.rzn.module_driver.ui.jobOrder.allorder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_driver.R;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 全部订单
 */

public class AllOrderFragment extends MVPBaseFragment<AllOrderContract.View, AllOrderPresenter> implements AllOrderContract.View {


    private View rootView;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_all_order,container,false);
        mPresenter.onCreate();
        initViews();
        return rootView;
    }

    private void initViews() {
        RecyclerView rcWorkList = (RecyclerView) rootView.findViewById(R.id.rc_work_List);
        rcWorkList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");

        AllOrderAdapter allOrderAdapter = new AllOrderAdapter(R.layout.driver_item_work_order, list);
        rcWorkList.setAdapter(allOrderAdapter);


    }
}
