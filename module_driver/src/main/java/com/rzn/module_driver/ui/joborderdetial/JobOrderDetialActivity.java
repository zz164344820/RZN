package com.rzn.module_driver.ui.joborderdetial;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobOrderDetialActivity extends MVPBaseActivity<JobOrderDetialContract.View, JobOrderDetialPresenter> implements JobOrderDetialContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_order_detial);
        mPresenter.onCreate();
        initViews();
//        showLoading(false,"");
    }

    private void initViews() {
        setTitle("作业订单详情");
    }
}
