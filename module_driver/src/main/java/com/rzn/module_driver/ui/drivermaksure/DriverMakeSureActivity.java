package com.rzn.module_driver.ui.drivermaksure;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverMakeSureActivity extends MVPBaseActivity<DriverMakeSureContract.View, DriverMakeSurePresenter> implements DriverMakeSureContract.View {

    private TextView tvCustomService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_make_sure);
        initViews();
        initListener();
        mPresenter.onCreate();
    }

    private void initListener() {
        tvCustomService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initViews() {
        setTitle("审核中");
        tvCustomService = (TextView) findViewById(R.id.tv_custom_service);

    }
}
