package com.rzn.module_farmer.ui.sendworksuccess;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendWorkSuccessActivity extends MVPBaseActivity<SendWorkSuccessContract.View, SendWorkSuccessPresenter> implements SendWorkSuccessContract.View {

    private RecyclerView rvType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_send_work_success);
        initViews();
        initData();
        initListener();
        mPresenter.onCreate();
//        showLoading(false, "");
    }


    private void initViews() {
        setTitle("发布作业需求");
        rvType = (RecyclerView) findViewById(R.id.rv_type);
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

    /**
     * 初始化监听
     */
    private void initListener() {
    }

}
