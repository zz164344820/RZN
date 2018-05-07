package com.rzn.module_farmer.ui.farmermakeworkmessage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerMakeWorkMessageActivity extends MVPBaseActivity<FarmerMakeWorkMessageContract.View, FarmerMakeWorkMessagePresenter> implements FarmerMakeWorkMessageContract.View {

    private TextView tvConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_make_work_detial);
        initViews();
        initListener();
        mPresenter.onCreate();
        showLoading(false, "");
    }

    //初始化监听
    private void initListener() {
        tvConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.httpConfig("", "");
            }
        });
    }

    /**
     * 初始化view布局
     */
    private void initViews() {
        //确认预约按钮
        tvConfig = (TextView) findViewById(R.id.tv_config);

    }

    /**
     * 预约机手成功
     */
    @Override
    public void configSuccess() {

    }

    /**
     * 预约机手失败
     */
    @Override
    public void configFailed() {

    }
}
