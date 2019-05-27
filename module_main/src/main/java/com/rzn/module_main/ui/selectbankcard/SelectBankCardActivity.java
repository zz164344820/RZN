package com.rzn.module_main.ui.selectbankcard;


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
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.addbankcard.AddBankCardActivity;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.rzn.module_main.ui.applygetmoney.bean.BankMessageBean;
import com.rzn.module_main.ui.bankcard.BankCardActivity;

import com.rzn.module_main.ui.newpwd.NewPasswordActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectBankCardActivity extends MVPBaseActivity<SelectBankCardContract.View, SelectBankCardPresenter> implements SelectBankCardContract.View {

    //验证码的集合
    private EditText[] codes;
    private EditText etCodeOne;
    private EditText etCodeTwo;
    private EditText etCodeThree;
    private EditText etCodeFour;
    private EditText etCodeFive;
    private EditText etCodeSix;
    private List<BankMessageBean> list = new ArrayList<>();
    private SelectBankCardAdapter selectBankCardAdapter;
    private LoginResponseBean loginResponseBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank_card);
        mPresenter.onCreate();
        initViews();
        setTitle("选择银行卡");
        setRightText("添加");

        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {

        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map map = new HashMap();
        map.put("userId", loginResponseBean.getUserId());
        mPresenter.getBankCardList(map);
    }

    private void initListener() {

        findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SelectBankCardActivity.this, AddBankCardActivity.class));
            }
        });
        selectBankCardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


                Intent intent = new Intent();
                intent.putExtra("bean", list.get(position));
                setResult(2, intent);
                finish();
            }
        });
    }

    private void initViews() {

        RecyclerView swipeTarget = findViewById(R.id.swipe_target);//swipe_target
//        SwipeToLoadLayout swipeToLoadLayout = findViewById(R.id.swipeToLoadLayout);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));


        selectBankCardAdapter = new SelectBankCardAdapter(R.layout.item_select_bank_card, list);
        swipeTarget.setAdapter(selectBankCardAdapter);
    }


    @Override
    public void getBankCardListSuccess(List<BankMessageBean> bankListBeanList) {
        if (bankListBeanList != null) {
            list.clear();
            list.addAll(bankListBeanList);
            selectBankCardAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void getBankCardListFailed() {

    }
}
