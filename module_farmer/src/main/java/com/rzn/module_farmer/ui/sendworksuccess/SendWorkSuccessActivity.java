package com.rzn.module_farmer.ui.sendworksuccess;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.RecommendDriver;
import com.rzn.module_farmer.ui.farmerdriverdetial.FarmerDriverDetialActivity;
import com.rzn.module_farmer.ui.farmerlist.FarmerListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendWorkSuccessActivity extends MVPBaseActivity<SendWorkSuccessContract.View, SendWorkSuccessPresenter> implements SendWorkSuccessContract.View {


    private RecyclerView rvType;
    String  farmerTaskId;
    private List<RecommendDriver> list = new ArrayList<>();
    private RecommendFarmerListAdapter farmerListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_send_work_success);
        ButterKnife.bind(this);
        initViews();
        initData();
        initListener();
        mPresenter.onCreate();
        String  farmerTaskId = getIntent().getStringExtra("farmerTaskId");
        mPresenter.httpLoadDriverMessage(farmerTaskId);
    }



    private void initViews() {
        setTitle("发布作业需求");
        farmerTaskId = getIntent().getStringExtra("farmerTaskId");
        rvType = (RecyclerView) findViewById(R.id.rv_type);
        rvType.setLayoutManager(new LinearLayoutManager(this));
        farmerListAdapter= new RecommendFarmerListAdapter(list);
        rvType.setAdapter(farmerListAdapter);
        farmerListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent  = new Intent(SendWorkSuccessActivity.this,FarmerDriverDetialActivity.class);
                intent.putExtra("handlerId",list.get(position).getHandlerId());
                startActivity(intent);
            }
        });
    }

    /**
     * 加载数据成功
     */
    @Override
    public void loadDriverMessageSuccessed(List<RecommendDriver> list1) {
        list.clear();
        list.addAll(list1);
        farmerListAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 初始化监听
     */
    private void initListener() {
    }

}
