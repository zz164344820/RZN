package com.rzn.module_main.ui.main.farmmachinery.wenzhang;


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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rzn.module_main.R2;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.webview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WenZhangFragment extends MVPBaseFragment<WenZhangContract.View, WenZhangPresenter> implements WenZhangContract.View, OnRefreshListener, OnLoadMoreListener {
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
//        initData();
        Map<String, String> map = new HashMap<>();
        map.put("type", "0");

        mPresenter.getWenZhangData(map);// TODO: 2018/8/12   需添加上传参数
        swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new FarmMachineryAdapter(list, getContext());
//        swipeTarget.setAdapter(adapter);
//        swipeToLoadLayout.setOnLoadMoreListener(this);
//        swipeToLoadLayout.setOnRefreshListener(this);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getContext(), WebViewActivity.class);
//                intent.putExtra("url", list.get(position).getUrl());
//                intent.putExtra("title", "热门文章");
//                startActivity(intent);
//            }
//        });
    }

    private void initData() {
//        list.clear();
//        list.add(new InfoBean("今年国家粮食最低价怎么收？咱们农民要注意啥？", "5月19日国家发改委等6部门颁发了《小麦和稻谷最低收购价执行预案》，作为国家政策执行主体", "2018-05-31 11:12", "http://p1.so.qhmsg.com/bdr/_240_/t015bced96590d4cbc6.jpg", "http://www.farmer.com.cn/xwpd/btxw/201805/t20180531_1380906.htm"));
//        list.add(new InfoBean("农业农村部：全国柑橘黄龙病防控现场会在广州召开", "5月30日，农业农村部在广州市召开全国柑橘黄龙病防控现场会。会议强调，柑橘黄龙病防控任务艰巨，责任重大。", "2018-05-30 16:43", "http://www.moa.gov.cn/xw/zwdt/201805/W020180530570858742070.jpg", "http://www.farmer.com.cn/xwpd/jjsn/201805/t20180530_1380733.htm"));
//        list.add(new InfoBean("小龙虾吃多体内会长寄生虫？ 5月谣言大盘点", "盘点5月份出现在朋友圈中的那些谣言，不难发现其中多数几乎在十年前便已出现。", "2018-05-30 09:10", "http://image1.chinanews.com.cn/cnsupload/big/2018/05-29/4-426/ee8ee3daf7654850a9e21d33fddd6925.jpg", "http://www.farmer.com.cn/xwpd/jjsn/201805/t20180530_1380572.htm"));
//        list.add(new InfoBean("今年汛期气象服务聚焦5大重点", "新华社北京5月31日电（记者刘诗平）中国气象局局长刘雅鸣近日在全国气象部门电视电话会议上强调，聚焦重", "2018-05-31 17:32", "http://p3.so.qhmsg.com/bdr/_240_/t01b94b802d6d43d988.jpg", "http://www.farmer.com.cn/xwpd/jjsn/201805/t20180531_1381061.htm"));
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


    @Override
    public void getWenZhangSuccess(final List<InfoBean> list) {
        adapter = new FarmMachineryAdapter(list, getContext());
        swipeTarget.setAdapter(adapter);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", list.get(position).getArticleUrl());
                intent.putExtra("title", "热门文章");
                startActivity(intent);
            }
        });
    }

    @Override
    public void getWenZhangFailed() {

    }
}
