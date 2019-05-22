package com.rzn.module_main.ui.moneydetial;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.moneydetial.bean.AccountWaterListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoneyDetialActivity extends MVPBaseActivity<MoneyDetialContract.View, MoneyDetialPresenter> implements MoneyDetialContract.View, OnLoadMoreListener, OnRefreshListener {


    private LoginResponseBean loginResponseBean;
    private int page = 1;
    List<AccountWaterListBean> accountListBeanList = new ArrayList<>();
    private MoneyDetialAdapter moneyDetialAdapter;
    private SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_detial);
        mPresenter.onCreate();
        initViews();
        setTitle("余额明细");
        initData(page + "");
        initListener();

    }

    private void initListener() {

        //刷新监听
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);

    }

    private void initData(String page) {

//        userId	Y	    机手用户Id	String
//        page	Y	当前页	String


        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map<String, String> map = new HashMap<>();
        map.put("userId", loginResponseBean.getUserId());
        map.put("page", page);
        mPresenter.getListData(map);

    }

    private void initViews() {
        RecyclerView swipeTarget = findViewById(R.id.swipe_target);//swipe_target
        swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));


        moneyDetialAdapter = new MoneyDetialAdapter(R.layout.item_money_detial, accountListBeanList);
        swipeTarget.setAdapter(moneyDetialAdapter);

    }


    @Override
    public void getListDataSuccess(List<AccountWaterListBean> accountWaterListBeanList) {
        if (accountWaterListBeanList != null) {
            if (page == 1) {
                accountListBeanList.clear();
            }
            accountListBeanList.addAll(accountWaterListBeanList);
            moneyDetialAdapter.notifyDataSetChanged();

        }
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void getListDataFailed() {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void onLoadMore() {
//        if (page < max) {
        initData((++page) + "");
//        } else {
//            Toast.makeText(this, "暂无更多数据", Toast.LENGTH_SHORT).show();
//            swipeToLoadLayout.setLoadingMore(false);
//        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        initData(page + "");
    }
}
