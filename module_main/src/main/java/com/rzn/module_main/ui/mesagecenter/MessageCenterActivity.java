package com.rzn.module_main.ui.mesagecenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessageCenterActivity extends MVPBaseActivity<MesageCenterContract.View, MesageCenterPresenter> implements MesageCenterContract.View,OnLoadMoreListener{


    @BindView(R2.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R2.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    List<MessageInfo> MessageInfoList  = new ArrayList<>();
    MessageAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_messagecenter);
        ButterKnife.bind(this);
        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("消息中心");
        initData();//临时数据源，有接口以后删除
        swipeToLoadLayout.setRefreshEnabled(false);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        adapter=   new MessageAdapter(this,MessageInfoList);
        swipeTarget.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               //todo 根据角标 判断具体跳转页面
            }
        });

    }

    private void initData() {
        MessageInfoList.add(new MessageInfo("0","接单拉","1","预约一下"));
        MessageInfoList.add(new MessageInfo("1","接单拉","2","预约一下"));
        MessageInfoList.add(new MessageInfo("1","接单拉","4","预约一下"));
        MessageInfoList.add(new MessageInfo("1","接单拉","3","预约一下"));
        MessageInfoList.add(new MessageInfo("0","接单拉","3","预约一下"));
    }


    @Override
    public void setData(List<MessageInfo> list) {
        MessageInfoList.addAll(list);
        adapter.notifyDataSetChanged();
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getMessageList();
    }
}
