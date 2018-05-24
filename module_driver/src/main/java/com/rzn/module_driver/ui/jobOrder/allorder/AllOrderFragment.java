package com.rzn.module_driver.ui.jobOrder.allorder;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.MyWorkDetialBean;
import com.rzn.module_driver.ui.joborderdetial.JobOrderDetialActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 全部订单
 */

public class AllOrderFragment extends MVPBaseFragment<AllOrderContract.View, AllOrderPresenter> implements AllOrderContract.View {


    private View rootView;
    List<MyWorkDetialBean> mylist = new ArrayList<>();
    private AllOrderAdapter allOrderAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_order, container, false);
        mPresenter.onCreate();
        initViews();
        initData();
        return rootView;
    }

    public void initData() {

        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map<String, String> map = new HashMap<>();
//        map.put("handlerId",loginResponseBean.getHandlerId());
        map.put("status", "");
        mPresenter.getList(map);
    }

    private void initViews() {
        RecyclerView rcWorkList = (RecyclerView) rootView.findViewById(R.id.rc_work_List);
        rcWorkList.setLayoutManager(new LinearLayoutManager(getActivity()));


        allOrderAdapter = new AllOrderAdapter(R.layout.driver_item_work_order, mylist);
        rcWorkList.setAdapter(allOrderAdapter);
        allOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, JobOrderDetialActivity.class));
            }
        });


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //显示
        } else {
            //隐藏
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("fragment", "zhoule-------------------------------------");
    }

    @Override
    public void getListSuccess(List<MyWorkDetialBean> list) {
        mylist.addAll(list);
        allOrderAdapter.notifyDataSetChanged();

    }
}
