package com.rzn.module_main.ui.driverhome;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverHomeActivity extends MVPBaseActivity<DriverHomeContract.View, DriverHomePresenter> implements DriverHomeContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_person_center);
        initViews();
        mPresenter.onCreate();
//        showLoading(false,"");
    }

    private void initViews() {
        setTitle("机手中心");
    }
}
