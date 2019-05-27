package com.rzn.module_main.ui.addbankcard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.addbankcard.bean.AddBankCardBean;
import com.rzn.module_main.ui.applygetmoney.bean.BankMessageBean;

import java.util.HashMap;
import java.util.Map;


/**
 * lilei
 * 添加银行卡
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddBankCardActivity extends MVPBaseActivity<AddBankCardContract.View, AddBankCardPresenter> implements AddBankCardContract.View {


    private TextView tvSave;
    private LoginResponseBean loginResponseBean;
    private EditText etBankCard;
    private EditText etPeople;
    private EditText etBank;
    private EditText etZhiBank;
    private BankMessageBean bankMessageBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        mPresenter.onCreate();
        initViews();
        setTitle("添加银行卡");


        initListener();
    }

    private void initListener() {
        tvSave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
//                userId	Y	    机手用户Id	String
//                cardholder	Y	持卡人	String
//                bankName	Y	银行名称	String
//                branchBankName	Y	支行	String
//                bankCard	Y	卡号	String
//                userInfoId	N	用户详情银行卡id	String
                loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");

                Map<String, String> map = new HashMap<>();
                map.put("userId", loginResponseBean.getUserId());
                map.put("cardholder", etPeople.getText().toString().toString().trim());
                map.put("bankName", etBank.getText().toString().trim());
                map.put("branchBankName", etZhiBank.getText().toString().trim());
                map.put("bankCard", etBankCard.getText().toString().trim());
                if (bankMessageBean != null && !TextUtils.isEmpty(bankMessageBean.getUserInfoId())) {
                    map.put("userInfoId", bankMessageBean.getUserInfoId());
                }
                mPresenter.addBankCardData(map);
            }
        });
    }

    private void initViews() {


        bankMessageBean = (BankMessageBean) getIntent().getSerializableExtra("bean");

        etBankCard = (EditText) findViewById(R.id.et_bank_card);
        etPeople = (EditText) findViewById(R.id.et_people);
        etBank = (EditText) findViewById(R.id.et_bank);
        etZhiBank = (EditText) findViewById(R.id.et_zhi_bank);
        tvSave = (TextView) findViewById(R.id.tv_save);


        if (bankMessageBean != null) {
            etBankCard.setText(bankMessageBean.getBankCard());
            etPeople.setText(bankMessageBean.getCardholder());
            etBank.setText(bankMessageBean.getBankName());
            etZhiBank.setText(bankMessageBean.getBranchBankName());
        }

    }


    @Override
    public void addBankCardDataSuccess(AddBankCardBean addBankCardBean) {

        if (addBankCardBean != null) {
            Toast.makeText(this, "银行卡添加成功！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void addBankCardDataFailed() {

    }
}
