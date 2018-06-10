package com.rzn.module_main.ui.main.farmmachinery.zixun;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.ui.main.farmmachinery.FarmMachineryAdapter;
import com.rzn.module_main.ui.main.farmmachinery.InfoBean;
import com.rzn.module_main.R2;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ZixunFragment extends MVPBaseFragment<ZixunContract.View, ZixunPresenter> implements ZixunContract.View,OnLoadMoreListener,OnRefreshListener {
    View rootView;
    @BindView(R2.id.swipe_target)
    RecyclerView swipeTarget;
    @BindView(R2.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    Unbinder unbinder;
    List<InfoBean> list = new ArrayList<>();
    FarmMachineryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_twitter_recycler, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter.onCreate();
        return rootView;
    }

    @Override
    public void initView() {
        super.initView();
        initData();
        swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter= new FarmMachineryAdapter(list,getContext());
        swipeTarget.setAdapter(adapter);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=  new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                intent.putExtra("title","农业资讯");
                startActivity(intent);
            }
        });
    }

    private void initData() {
        list.clear();
        list.add(new InfoBean("200名甘肃农机手组团提机 再踏三夏掘金路","5月15日,“2018年雷沃谷神甘肃用户批量交机暨雷沃三夏全程智能服务启动仪式”","2018-05-26 10:08","https://mmbiz.qpic.cn/mmbiz_jpg/W3OkHlCc5AGC9H85eeGATLpGibS5qJl6vgFhp7OA4ezGxFu6XT41PsBM9ZOSkQ5IYJDUgFJKoxib2JKPorbbiaukA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1","https://mp.weixin.qq.com/s/EG0tXoFHa_2nZpEwQjOJ2w"));
        list.add(new InfoBean("跨区机手看到这里都沉默了，有人还偷偷抹了抹眼泪","该外出跨区了，看到妻儿留恋的眼神，眼角不自觉热了","2018-05-24 11:12","https://mmbiz.qpic.cn/mmbiz_jpg/W3OkHlCc5AEicUJicdgQkFPXWxYjJW3oKqBm4ukwdSaS9KRNUecZsnFRtSlSWyLvgQjZeasibXiaGPwu07y6pepl2g/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1","https://mp.weixin.qq.com/s/FTjFYzPFMNTdSidFNOGrug"));
        list.add(new InfoBean("盯紧！农业农村部对跨区作业有新要求，很多第一次提","全国大规模冬小麦跨区机收工作即将全面展开。“三夏”农时紧、任务重，确保夏粮颗粒归仓、秋粮适时播种","2018-05-17 13:10","https://mmbiz.qpic.cn/mmbiz_jpg/W3OkHlCc5AEbELnArQReAG5WRDJib8eVkQNEjxhCPnkf8ibTBKEO8GyxJcd7Zktrv5EmR5ibv6TuQh7dCFias9H0Fw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1","https://mp.weixin.qq.com/s/XNyyAZEqzbxMnk6cI8qYsg"));
        list.add(new InfoBean("江苏省2018年农机购置补贴机具分类分档及补贴额一览表的公示！","根据农业部、财政部《2018-2020年农机购置补贴实施指导意见》、农业部办公厅","2018-04-23 15:24","http://p2.so.qhimgs1.com/bdr/_240_/t0134ceeb6d6b2d5272.jpg","http://bbs.xbnj.net/t/d-29876095?from=timeline&auth_time=636633832619155721"));
        list.add(new InfoBean("精准农业 让中国预见未来","在未来的国家竞争中，种质资源争夺将更趋激烈。欧洲、美国、日本、澳大利亚等发达国家已先后建立作物表型组学研究机构","2018-03-26 13:04","https://mmbiz.qpic.cn/mmbiz_png/5TQ95ZkO7aY3BdlbnVtwGcAnK9JhYEDswFzPefmXqJUHxNv8Yx5Xyfm9ftOHz4OEoiaOxEYQU2uicAZMAKq3K7lg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1","https://mp.weixin.qq.com/s/s7untuI0XABSpBZuwEWh1Q"));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadMore() {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void onRefresh() {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }
}
