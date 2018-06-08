package com.rzn.module_driver.ui.drivermaksure;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.PhoneUtils;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.driver_identification.Driver_identificationActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = "/driver/makesure")
public class DriverMakeSureActivity extends MVPBaseActivity<DriverMakeSureContract.View, DriverMakeSurePresenter> implements DriverMakeSureContract.View {

    private TextView tvCustomService,tv_DriverCenter;

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
                PhoneUtils.dial("17512500100");
            }
        });

        tv_DriverCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2018/5/27 跳转机手中心
                startActivity(new Intent(DriverMakeSureActivity.this, Driver_identificationActivity.class));
            }
        });
    }

    private void initViews() {
        setTitle("审核中");
        tvCustomService = (TextView) findViewById(R.id.tv_custom_service);
        tv_DriverCenter = (TextView) findViewById(R.id.tv_DriverCenter);

    }
}
