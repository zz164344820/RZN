package com.rzn.module_driver.ui.drivermaksure;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DriverMakeSureActivity extends MVPBaseActivity<DriverMakeSureContract.View, DriverMakeSurePresenter> implements DriverMakeSureContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_make_sure);
        initViews();
        mPresenter.onCreate();
    }

    private void initViews() {

    }
}
