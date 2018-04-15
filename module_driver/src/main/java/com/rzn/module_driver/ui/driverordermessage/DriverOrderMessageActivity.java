package com.rzn.module_driver.ui.driverordermessage;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverOrderMessageActivity extends MVPBaseActivity<DriverOrderMessageContract.View, DriverOrderMessagePresenter> implements DriverOrderMessageContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_orders_message);
        initViews();
        mPresenter.onCreate();
        showLoading(false,"");
    }

    private void initViews() {
        setTitle("完善接单信息");
    }
}
