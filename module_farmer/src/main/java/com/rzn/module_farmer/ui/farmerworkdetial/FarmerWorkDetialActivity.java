package com.rzn.module_farmer.ui.farmerworkdetial;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;


/**
 * 农户端 作业详情
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerWorkDetialActivity extends MVPBaseActivity<FarmerWorkDetialContract.View, FarmerWorkDetialPresenter> implements FarmerWorkDetialContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_farmer_order_detial);
        initViews();
        initData();
        initListener();
        mPresenter.onCreate();
//        showLoading(false,"");
    }

    /**
     * 初始化数据
     */
    private void initData() {

        mPresenter.httpLoadData("");

    }

    /**
     * 初始化监听
     */
    private void initListener() {
    }

    /**
     * 初始化view
     */
    private void initViews() {
    }

    /**
     * 加载数据成功
     */
    @Override
    public void onLoadSuccess() {

    }

    /**
     * 加载数据失败
     */
    @Override
    public void onLoafFailed() {

    }
}
