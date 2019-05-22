package com.rzn.module_main.ui.getmoneydetial;


import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lonch.zyhealth.loadmorelibrary.LoadMoreUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.getmoneydetial.bean.DetialBean;
import com.rzn.module_main.ui.moneydetial.bean.AccountWaterListBean;
import com.rzn.module_main.ui.presentationdetial.PresentationDetialActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GetMoneyDetialActivity extends MVPBaseActivity<GetMoneyDetialContract.View, GetMoneyDetialPresenter> implements GetMoneyDetialContract.View, OnLoadMoreListener, OnRefreshListener {


    private GetMoneyDetialAdapter getMoneyDetialAdapter;
    private LoginResponseBean loginResponseBean;
    private int page = 1;
    private TextView tvLeiji;
    private TextView tvTixian;
    List<AccountWaterListBean> accountWaterListBeanList = new ArrayList<>();
    private SwipeToLoadLayout swipeToLoadLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getmoney_detial);
        mPresenter.onCreate();
        initViews();
        setTitle("提现明细");

        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(page + "");
    }

    private void initData(String page) {
//        userId	Y	    机手用户Id	String
//        page	Y	当前页	String
        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map<String, String> map = new HashMap<>();
        map.put("userId", loginResponseBean.getUserId());
        map.put("page", page);
        mPresenter.getDetialData(map);


    }

    private void initListener() {
        //刷新监听
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);
        getMoneyDetialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转详情
                Intent intent = new Intent(GetMoneyDetialActivity.this, PresentationDetialActivity.class);
                intent.putExtra("bean", accountWaterListBeanList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initViews() {

        tvLeiji = (TextView) findViewById(R.id.tv_leiji);
        tvTixian = (TextView) findViewById(R.id.tv_tixian);

        RecyclerView swipeTarget = findViewById(R.id.swipe_target);//swipe_target
        swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));


        getMoneyDetialAdapter = new GetMoneyDetialAdapter(R.layout.item_get_money_detial, accountWaterListBeanList);
        swipeTarget.setAdapter(getMoneyDetialAdapter);

    }


    @Override
    public void getDetialDataSuccess(DetialBean detialBean) {
//        private String balancePast;//": "12.0", //累计提现金额
//        private String withdrawing;//": "0" //提现中金额

        if (detialBean != null) {
            tvLeiji.setText(detialBean.getBalancePast());
            tvTixian.setText(detialBean.getWithdrawing());

            if (page == 1) {
                accountWaterListBeanList.clear();
            }
            accountWaterListBeanList.addAll(detialBean.getAccountWaterList());
            getMoneyDetialAdapter.notifyDataSetChanged();

        }
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void getDetialDataFailed() {
        LoadMoreUtils.recycleViewRestore(swipeToLoadLayout);
    }

    @Override
    public void onLoadMore() {
        initData((++page) + "");
    }

    @Override
    public void onRefresh() {
        page = 1;
        initData(page + "");
    }
}
