package com.rzn.module_main.ui.mesagecenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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


                //todo 根据角标 判断具体跳转页面
//                1、接单提醒（“您发布的作业需求有人接单啦！”）
//                2、预约提醒（“有人请你作业，快去看看吧！”）
//                3、订单取消提醒（“预约2017-11-02作业的‘水稻-联合整地’订单已取消”）
//                4、机手认证审核进度通知（审核通过提示：“审核通过，您可以开始接单了！”
//                审核未通过提示：“审核未通过，未通过原因：‘xxxxxxxx’”）
//                以上4种提醒在发送极光的同时，保存到数据库当中，在消息中心界面展示这四种消息列表。点击跳转：
//                接单提醒----->订单列表页面
//                预约提醒----->订单列表页面
//                订单取消----->订单列表页面
//                机手认证----->机手认证页面

            }
        });

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
