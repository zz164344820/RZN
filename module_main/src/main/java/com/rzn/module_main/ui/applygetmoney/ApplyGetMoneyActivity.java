package com.rzn.module_main.ui.applygetmoney;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzj.pass.dialog.PayPassDialog;
import com.lzj.pass.dialog.PayPassView;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.rzn.module_main.ui.applygetmoney.bean.BankMessageBean;
import com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd.VerifyMessageActivity;
import com.rzn.module_main.ui.mywallet.bean.MyWalletBean;
import com.rzn.module_main.ui.selectbankcard.SelectBankCardActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ApplyGetMoneyActivity extends MVPBaseActivity<ApplyGetMoneyContract.View, ApplyGetMoneyPresenter> implements ApplyGetMoneyContract.View {


    private TextView tvTijiao;
    private MyWalletBean myWalletBean;
    private TextView tvUseMoney;
    private LinearLayout llBankAdd;
    private LoginResponseBean loginResponseBean;
    private TextView tvBankName;
    private TextView tvAllGet;
    private EditText etMoney;
    private BankMessageBean bankMessageBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_getmoney);
        mPresenter.onCreate();
        initViews();
        initData();
        setTitle("申请提现");
        initListener();

    }

    private void initData() {
        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map map = new HashMap();
        map.put("userId", loginResponseBean.getUserId());
        mPresenter.getBankListData(map);

    }

    private void initListener() {
        tvTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bankMessageBean != null) {
                    payDialog();
                } else {
                    Toast.makeText(ApplyGetMoneyActivity.this, "请选择银行卡", Toast.LENGTH_SHORT).show();
                }

            }
        });
        llBankAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplyGetMoneyActivity.this, SelectBankCardActivity.class);
//                  startActivity(new Intent(ApplyGetMoneyActivity.this, SelectBankCardActivity.class));
                startActivityForResult(intent, 1);
            }
        });
        tvAllGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etMoney.setText(myWalletBean.getBalance());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {

            bankMessageBean = (BankMessageBean) data.getSerializableExtra("bean");
            tvBankName.setText(bankMessageBean.getBankName());

        }


    }

    private void initViews() {

        myWalletBean = (MyWalletBean) getIntent().getSerializableExtra("walletBean");


        tvTijiao = (TextView) findViewById(R.id.tv_tijiao);
        tvUseMoney = (TextView) findViewById(R.id.tv_use_money);
        llBankAdd = (LinearLayout) findViewById(R.id.ll_bank_add);
        tvBankName = (TextView) findViewById(R.id.tv_bank_name);
        tvAllGet = (TextView) findViewById(R.id.tv_all_get);
        etMoney = (EditText) findViewById(R.id.et_money);
        tvUseMoney.setText("可提现金额：" + myWalletBean.getBalance());
    }


    private void payDialog() {

        final PayPassDialog dialog = new PayPassDialog(this);

        dialog.getPayViewPass()

                .setPayClickListener(new PayPassView.OnPayClickListener() {

                    @Override

                    public void onPassFinish(String passContent) {

                        //6位输入完成,回调
//                        userId	Y	    机手用户Id	String
//                        userInfoId	Y	用户详情银行卡id	String
//                        withdrawAmt	Y	申请提现金额	String
//                        payPassword	Y	支付密码	String

                        // 请求接口
                        Map<String, String> map = new HashMap<>();
                        map.put("userId", loginResponseBean.getUserId());
                        map.put("userInfoId", bankMessageBean.getUserInfoId());
                        map.put("withdrawAmt", etMoney.getText().toString().trim());
                        map.put("payPassword", passContent);
                        mPresenter.getMoneyData(map);


                    }

                    @Override

                    public void onPayClose() {

                        dialog.dismiss();

                        //关闭回调

                    }


                    @Override

                    public void onPayForget() {


                        startActivity(new Intent(ApplyGetMoneyActivity.this, VerifyMessageActivity.class));


                        dialog.dismiss();

                        //点击忘记密码回调

                    }

                });

    }


    @Override
    public void getBankListDataSuccess(List<BankMessageBean> bankListBeanList) {
        if (bankListBeanList != null) {

            if (bankListBeanList.size() == 0) {
                //显示添加银行卡
                tvBankName.setText("添加银行卡");
            } else {

                //显示选择银行卡
                tvBankName.setText("选择银行卡");
            }

        }
    }

    @Override
    public void getBankListDataFailed() {

    }

    @Override
    public void getMoneyDataSuccess() {
        finish();
    }

    @Override
    public void getMoneyDataFailed() {

    }
}
