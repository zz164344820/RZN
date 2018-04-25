package com.rzn.module_farmer.ui.sendwork;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SendWorkActivity extends MVPBaseActivity<SendWorkContract.View, SendWorkPresenter> implements SendWorkContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_release_assignments);
        setTitle("发布作业需求");
        mPresenter.onCreate();
        showLoading(false,"");
    }
}
