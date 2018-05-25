package com.rzn.module_main.ui.setting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.tencent.bugly.beta.Beta;
import com.zyhealth.expertlib.LibApplication;

import cn.jpush.android.api.JPushInterface;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingActivity extends MVPBaseActivity<SettingContract.View, SettingPresenter> implements SettingContract.View {

    LoginResponseBean  loginResponseBean;
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
        final TextView tv_messageStute = (TextView)findViewById(R.id.tv_messageStute);
        CheckBox checkBox= (CheckBox)findViewById(R.id.cb_message);
        boolean isOpen= SPUtils.getInstance().getBoolean("message",true);
        checkBox.setChecked(isOpen);
        loginResponseBean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        findViewById(R.id.tv_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beta.checkUpgrade(true,false);
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
                 if(b){
                     tv_messageStute.setText("接收消息推送");
                     SPUtils.getInstance().put("message",true);
                     if(loginResponseBean!=null){
                         JPushInterface.setAlias(SettingActivity.this, 111, loginResponseBean.getUserId());
                     }

                 }else{
                     tv_messageStute.setText("关闭消息推送");
                     SPUtils.getInstance().put("message",false);
                     JPushInterface.setAlias(SettingActivity.this, 111, "");

                 }
            }
        });

    }


    public void logOut() {
        for (int i = 0, size = LibApplication.mActivityStack.size(); i < size; i++) {
             if(!LibApplication.mActivityStack.get(i).getClass().getSimpleName().equals("LoginActivity")){
                 LibApplication.mActivityStack.get(i).finish();
             }
        }
        JPushInterface.setAlias(SettingActivity.this, 111, "");

    }
}
