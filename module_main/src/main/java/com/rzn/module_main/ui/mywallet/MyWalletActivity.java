package com.rzn.module_main.ui.mywallet;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.applygetmoney.ApplyGetMoneyActivity;
import com.rzn.module_main.ui.bankcard.BankCardActivity;
import com.rzn.module_main.ui.getmoneydetial.GetMoneyDetialActivity;
import com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd.VerifyMessageActivity;
import com.rzn.module_main.ui.modiffypassword.modiffypwd.ModiffyPasswordActivity;
import com.rzn.module_main.ui.moneydetial.MoneyDetialActivity;
import com.rzn.module_main.ui.moneydetial.MoneyDetialAdapter;
import com.rzn.module_main.ui.mywallet.bean.MyWalletBean;
import com.rzn.module_main.ui.personalinfo.UserInfo;
import com.rzn.module_main.ui.setting.SettingActivity;
import com.rzn.module_main.ui.setting.bean.SettingPasswordBean;

import java.util.HashMap;
import java.util.Map;


/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyWalletActivity extends MVPBaseActivity<MyWalletContract.View, MyWalletPresenter> implements MyWalletContract.View {


    private LinearLayout llApplyMoney;
    private LinearLayout llBankCard;
    private LinearLayout llGetMoney;
    private LinearLayout llUseMoney;
    private TextView tvAllMoney;
    private TextView tvKeyong;
    private TextView tvTixian;
    private TextView tvLeft;
    private MyWalletBean walletBean;
    private LoginResponseBean loginResponseBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        mPresenter.onCreate();
        initViews();
//        setTitle("");

        initListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map<String, String> map = new HashMap<>();
        map.put("userId", loginResponseBean.getUserId());
        mPresenter.getWalletDetial(map);
    }

    private void initListener() {
        llApplyMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //请求网络判断是否有初始密码

                Map<String, String> map = new HashMap<>();
                map.put("fundId", loginResponseBean.getFundId());
                mPresenter.getIsPasswordData(map);


                //跳转申请提现
//                Intent intent = new Intent(MyWalletActivity.this, ApplyGetMoneyActivity.class);
//                intent.putExtra("walletBean", walletBean);
//                startActivity(intent);
            }
        });
        llBankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转银行卡
                startActivity(new Intent(MyWalletActivity.this, BankCardActivity.class));
            }
        });

        llGetMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //提现中
                startActivity(new Intent(MyWalletActivity.this, GetMoneyDetialActivity.class));
            }
        });
        llUseMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //可用余额
                startActivity(new Intent(MyWalletActivity.this, MoneyDetialActivity.class));

            }
        });


        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void initViews() {

        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        llApplyMoney = (LinearLayout) findViewById(R.id.ll_apply_money);
        llBankCard = (LinearLayout) findViewById(R.id.ll_bank_card);

        llGetMoney = (LinearLayout) findViewById(R.id.ll_get_money);
        llUseMoney = (LinearLayout) findViewById(R.id.ll_use_money);


        tvAllMoney = (TextView) findViewById(R.id.tv_all_money);
        tvKeyong = (TextView) findViewById(R.id.tv_keyong);
        tvTixian = (TextView) findViewById(R.id.tv_tixian);
        tvLeft = (TextView) findViewById(R.id.tv_left);

    }


    @Override
    public void getWalletDetialSuccess(MyWalletBean myWalletBean) {

//                "balance": 74.04, //可用余额
//                "userId": "40289f376536de0401653734d39d0000", //机手用户id
//                "viewTotalPrice": 104.04, //总额
//                "withdrawing": 30 //提现中id

        walletBean = myWalletBean;
        if (myWalletBean != null) {
            tvAllMoney.setText("¥"+myWalletBean.getViewTotalPrice());
            tvKeyong.setText("¥"+myWalletBean.getBalance());
            tvTixian.setText("¥"+myWalletBean.getWithdrawing());
        }


    }

    @Override
    public void getWalletDetialFailed() {

    }

    @Override
    public void getIsPasswordDataSuccess(SettingPasswordBean settingPasswordBean) {
        if (settingPasswordBean != null) {
            if (settingPasswordBean.isSet()) {
                // true 已设置密码 ,falst 未设置密码
//                startActivity(new Intent(MyWalletActivity.this, ModiffyPasswordActivity.class));
                Intent intent = new Intent(MyWalletActivity.this, ApplyGetMoneyActivity.class);
                intent.putExtra("walletBean", walletBean);
                startActivity(intent);
            } else {

                startActivity(new Intent(MyWalletActivity.this, VerifyMessageActivity.class));
            }
        }
    }

    @Override
    public void getIsPasswordDataFailed() {

    }
}
