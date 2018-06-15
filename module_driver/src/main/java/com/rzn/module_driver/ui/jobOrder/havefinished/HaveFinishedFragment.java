package com.rzn.module_driver.ui.jobOrder.havefinished;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.MyWorkDetialBean;
import com.rzn.module_driver.ui.jobOrder.allorder.AllOrderAdapter;
import com.rzn.module_driver.ui.jobOrder.myjoborder.MyjobOrderActivity;
import com.rzn.module_driver.ui.joborderdetial.JobOrderDetialActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 已完成订单
 */

public class HaveFinishedFragment extends MVPBaseFragment<HaveFinishedContract.View, HaveFinishedPresenter> implements HaveFinishedContract.View {
    List<MyWorkDetialBean> mylist = new ArrayList<>();
    private AllOrderAdapter allOrderAdapter;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_all_order, container, false);
        mPresenter.onCreate();
        initViews();


        return rootView;
    }

    private void initData() {

        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if (loginResponseBean == null) {
            return;
        }
        if ("farmer".equals(((MyjobOrderActivity) getActivity()).getLabel())) {
            Map<String, String> map = new HashMap<>();
            if (TextUtils.isEmpty(loginResponseBean.getUserId())) {
                return;
            }
            map.put("userId", loginResponseBean.getUserId());
            map.put("status", "1");
            mPresenter.getFarmerListSuccess(map);
        } else if ("driver".equals(((MyjobOrderActivity) getActivity()).getLabel())) {
            Map<String, String> map = new HashMap<>();
            if (TextUtils.isEmpty(loginResponseBean.getHandlerId())) {
                return;
            }
            map.put("handlerId", loginResponseBean.getHandlerId());
            map.put("status", "4");
            mPresenter.getList(map);
        }

    }

    private void initViews() {
        RecyclerView rcWorkList = (RecyclerView) rootView.findViewById(R.id.rc_work_List);
        rcWorkList.setLayoutManager(new LinearLayoutManager(getActivity()));


        allOrderAdapter = new AllOrderAdapter(R.layout.driver_item_work_order, mylist);
        rcWorkList.setAdapter(allOrderAdapter);
        allOrderAdapter.setEmptyView(R.layout.driverorder_nullpager, (ViewGroup) rcWorkList.getParent());
        allOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if ("farmer".equals(((MyjobOrderActivity) getActivity()).getLabel())) {
                    Intent intent = new Intent(getContext(), JobOrderDetialActivity.class);
                    intent.putExtra("flag", "farmer");
                    intent.putExtra("farmerTaskId", mylist.get(position).getFarmerTaskId());
                    startActivity(intent);
                } else if ("driver".equals(((MyjobOrderActivity) getActivity()).getLabel())) {
                    Intent intent = new Intent(getContext(), JobOrderDetialActivity.class);
                    intent.putExtra("flag", "driver");
                    intent.putExtra("farmerTaskId", mylist.get(position).getFarmerTaskId());
                    startActivity(intent);
                }
//                startActivity(new Intent(mContext, JobOrderDetialActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
//        initData();
//        Log.d("fragment", "zhoule-------------------------------------");
    }

    @Override
    public void getListSuccess(List<MyWorkDetialBean> list) {
        mylist.clear();
        mylist.addAll(list);
        allOrderAdapter.notifyDataSetChanged();
    }
}
