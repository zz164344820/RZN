package com.rzn.module_main.ui.mesagecenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
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

public class MessageCenterActivity extends MVPBaseActivity<MesageCenterContract.View, MesageCenterPresenter> implements MesageCenterContract.View, OnLoadMoreListener {


    @BindView(R2.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R2.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    List<MessageInfo> MessageInfoList = new ArrayList<>();
    MessageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_messagecenter);
        ButterKnife.bind(this);
        mPresenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getMessageList();
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("消息中心");
        swipeToLoadLayout.setRefreshEnabled(false);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MessageAdapter(this, MessageInfoList);
        adapter.setEmptyView(R.layout.act_message,(ViewGroup)swipeTarget.getParent());
        swipeTarget.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //1: 预约  2：取消  3：认证   4：抢单
                if ("0".equals(MessageInfoList.get(position).getIsread())) {
                    mPresenter.setRead(MessageInfoList.get(position).getUserMsgId());
                }
                String type = MessageInfoList.get(position).getMsgType();
                if ("1".equals(type)) {
                    //预约
                    ARouter.getInstance().build("/driver/myjobdetial").withString("type", "1").navigation();
                } else if ("2".equals(type)) {
                    //取消
                    ARouter.getInstance().build("/driver/myjobdetial").navigation();
                } else if ("3".equals(type)) {
                    //认证
                    ARouter.getInstance().build("/driver/makesure").navigation();//审核中界面
                } else if ("4".equals(type)) {
                    //抢单
                    ARouter.getInstance().build("/driver/myjobdetial").navigation();
                }

            }
        });

    }


    @Override
    public void setData(List<MessageInfo> list) {
        MessageInfoList.clear();
        MessageInfoList.addAll(list);
        adapter.notifyDataSetChanged();
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void onLoadMore() {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
       // mPresenter.getMessageList();
    }
}
