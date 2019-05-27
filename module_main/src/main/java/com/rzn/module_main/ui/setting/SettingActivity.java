package com.rzn.module_main.ui.setting;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.login.LoginActivity;
import com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd.VerifyMessageActivity;
import com.rzn.module_main.ui.modiffypassword.modiffypwd.ModiffyPasswordActivity;
import com.rzn.module_main.ui.setting.bean.SettingPasswordBean;
import com.tencent.bugly.beta.Beta;
import com.zyhealth.expertlib.LibApplication;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import mlxy.utils.L;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingActivity extends MVPBaseActivity<SettingContract.View, SettingPresenter> implements SettingContract.View {

    LoginResponseBean loginResponseBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting);
        initViews();
        mPresenter.onCreate();
    }

    private void initViews() {
        setTitle("设置");

    }

    @Override
    public void initView() {
        super.initView();

//        LinearLayout llPassword = (LinearLayout) findViewById(R.id.ll_password);
//        if ()

        findViewById(R.id.ll_ChangePaymentPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //请求网络判断是否有初始密码

                Map<String, String> map = new HashMap<>();
                map.put("fundId", loginResponseBean.getFundId());
                mPresenter.getIsPasswordData(map);

//                startActivity(new Intent(SettingActivity.this, SelectBankCardActivity.class));
//                ToastUtils.showShort("修改支付密码");
            }
        });
        findViewById(R.id.ll_FindPaymentPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vew) {
                startActivity(new Intent(SettingActivity.this, VerifyMessageActivity.class));
            }
        });


        final TextView tv_messageStute = (TextView) findViewById(R.id.tv_messageStute);
        CheckBox checkBox = (CheckBox) findViewById(R.id.cb_message);
        boolean isOpen = SPUtils.getInstance().getBoolean("message", true);
        checkBox.setChecked(isOpen);
        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        findViewById(R.id.tv_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beta.checkUpgrade(true, false);
            }
        });

        findViewById(R.id.tv_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tv_messageStute.setText("接收消息推送");
                    SPUtils.getInstance().put("message", true);
                    if (loginResponseBean != null) {
                        JPushInterface.setAlias(SettingActivity.this, 111, loginResponseBean.getUserId());
                    }

                } else {
                    tv_messageStute.setText("关闭消息推送");
                    SPUtils.getInstance().put("message", false);
                    JPushInterface.setAlias(SettingActivity.this, 111, "");

                }
            }
        });
        LinearLayout llPassword =(LinearLayout) findViewById(R.id.ll_password);
        if (TextUtils.isEmpty(loginResponseBean.getFundId())) {
            llPassword.setVisibility(View.GONE);
        } else {
            llPassword.setVisibility(View.VISIBLE);
        }
    }


    public void logOut() {
        //將本地数据对象清空
        clearObject();

        startActivity(new Intent(this, LoginActivity.class));
        LibApplication.instance.killAllActivity2("LoginActivity");
        JPushInterface.setAlias(SettingActivity.this, 111, "");
    }

    private void clearObject() {
        LoginResponseBean responseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        responseBean = null;
        FileSaveUtils.fileSaveObject(responseBean, "loginBean");
    }

    @Override
    public void getIsPasswordDataSuccess(SettingPasswordBean settingPasswordBean) {
        if (settingPasswordBean != null) {
            if (settingPasswordBean.isSet()) {
                // true 已设置密码 ,falst 未设置密码
                startActivity(new Intent(SettingActivity.this, ModiffyPasswordActivity.class));
            } else {
                startActivity(new Intent(SettingActivity.this, VerifyMessageActivity.class));
            }
        }
    }

    @Override
    public void getIsPasswordDataFailed() {

    }
}
