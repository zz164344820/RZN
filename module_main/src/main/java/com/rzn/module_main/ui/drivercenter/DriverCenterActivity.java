package com.rzn.module_main.ui.drivercenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;

import mlxy.utils.T;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverCenterActivity extends MVPBaseActivity<DriverCenterContract.View, DriverCenterPresenter> implements DriverCenterContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_conter);
        initViews();
        mPresenter.onCreate();
//        showLoading(false,"");
    }

    private void initViews() {
        ImageView ivPhoto = (ImageView) findViewById(R.id.iv_photo);//头像
        TextView tvName = (TextView) findViewById(R.id.tv_name);//姓名
        TextView tvContent = (TextView) findViewById(R.id.tv_content);//从业多少年
        TextView tvText = (TextView) findViewById(R.id.tv_text);//什么老司机
//        findViewById(R.id.tv_name);
        TextView tvNumberMu = (TextView) findViewById(R.id.tv_number_mu);
        TextView tvMoney = (TextView) findViewById(R.id.tv_money);


    }
}
