package com.rzn.module_main.ui.main.searcharticle;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.main.farmmachinery.FarmMachineryAdapter;
import com.rzn.module_main.ui.main.farmmachinery.InfoBean;
import com.rzn.module_main.ui.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SearchArticleActivity extends MVPBaseActivity<SearchArticleContract.View, SearchArticlePresenter> implements SearchArticleContract.View {

//    SwipeToLoadLayout swipeToLoadLayout;
    private EditText ed_text_search;
    List<InfoBean> list = new ArrayList<>();
    private RecyclerView swipeTarget;
    private FarmMachineryAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search_article);
        mPresenter.onCreate();
        initViews();
        initListener();

    }

    private void initListener() {
        ed_text_search.setOnKeyListener(onKey);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SearchArticleActivity.this, WebViewActivity.class);
                intent.putExtra("url", list.get(position).getArticleUrl());
                intent.putExtra("title", "热门文章");
                startActivity(intent);
            }
        });
    }

    private void initViews() {
//        swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
//        swipeToLoadLayout.setOnLoadMoreListener(this);
//        swipeToLoadLayout.setOnRefreshListener(this);
        ed_text_search = (EditText) findViewById(R.id.ed_text_search);
        setTitle("文章搜索");
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FarmMachineryAdapter(list, SearchArticleActivity.this);
        swipeTarget.setAdapter(adapter);
    }

    @Override
    public void complete_enter() {
//        super.complete_enter();
//        page = 1;
        initData(ed_text_search.getText().toString(), page + "");
    }

    private void initData(String name, String page) {
//        type	Y	文章类型	String	(文章类型  0：热门文章   1:农业咨询   )
//        name	N	搜索的文章标题	String
//        page	Y	当前页	String	1
//        row	Y	每页条数	String	10

        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("page", page);
        map.put("row", 10 + "");
        mPresenter.getData(map);

    }

//    @Override
//    public void onLoadMore() {
//
//        initData(ed_text_search.getText().toString(), 1 + "");// TODO: 2019/5/30 胡君宝说这地方没分页，接口是错的，我擦了，无语了袄
//        Toast.makeText(this, "暂无更多数据", Toast.LENGTH_SHORT).show();
//        swipeToLoadLayout.setLoadingMore(false);
//    }
//
//    @Override
//    public void onRefresh() {
//        page = 1;
//        initData(ed_text_search.getText().toString(), page + "");
//    }

    @Override
    public void getDataSuccess(List<InfoBean> lists) {
        if (list != null) {
//            if (page == 1) {
//                list.clear();
//            }
            list.addAll(lists);
            adapter.notifyDataSetChanged();
        }
//        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void getDataFailed() {

    }
}
