package com.rzn.module_main.ui.bankcard;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.addbankcard.AddBankCardActivity;
import com.rzn.module_main.ui.applygetmoney.bean.BankMessageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * lilei
 * 银行卡列表
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BankCardActivity extends MVPBaseActivity<BankCardContract.View, BankCardPresenter> implements BankCardContract.View {

    private List<BankMessageBean> list = new ArrayList<>();
    private BankCardAdapter bankCardAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        mPresenter.onCreate();
        initViews();
        setTitle("银行卡");
        setRightText("添加");
        initListner();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map map = new HashMap();
        map.put("userId", loginResponseBean.getUserId());
        mPresenter.getBankCardList(map);
    }

    private void initListner() {
        findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(BankCardActivity.this, AddBankCardActivity.class));
            }
        });


        bankCardAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (view.getId() == R.id.iv_modiffy) {

                    Intent intent=new Intent(BankCardActivity.this, AddBankCardActivity.class);
                    intent.putExtra("bean", (Serializable) list.get(position));
                    startActivity(intent);

                } else if (view.getId() == R.id.iv_delete) {
                    Map<String, String> map = new HashMap<>();
                    map.put("userInfoId", list.get(position).getUserInfoId());
                    mPresenter.detelBankCard(map);
                }


            }
        });
    }

    private void initViews() {


//        @BindView(R2.id.swipe_target)
//        RecyclerView swipeTarget;
//        @BindView(R2.id.swipeToLoadLayout)
//        SwipeToLoadLayout swipeToLoadLayout;


        RecyclerView swipeTarget = findViewById(R.id.swipe_target);//swipe_target
//        SwipeToLoadLayout swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));


        bankCardAdapter = new BankCardAdapter(R.layout.item_bank_card, list);
        swipeTarget.setAdapter(bankCardAdapter);


    }


    @Override
    public void getBankCardListSuccess(List<BankMessageBean> bankListBeanList) {
        if (bankListBeanList != null) {
            list.clear();
            list.addAll(bankListBeanList);
            bankCardAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void getBankCardListFailed() {

    }

    @Override
    public void detelBankCardSuccess() {
        initData();
    }

    @Override
    public void detelBankCardFailed() {

    }
}
