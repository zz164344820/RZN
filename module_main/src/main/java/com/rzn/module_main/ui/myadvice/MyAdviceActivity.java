package com.rzn.module_main.ui.myadvice;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyAdviceActivity extends MVPBaseActivity<MyAdviceContract.View, MyAdvicePresenter> implements MyAdviceContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_feedback);
        initViews();
        mPresenter.onCreate();
//        showLoading(false, "");
    }

    private void initViews() {
        setTitle("意见反馈");
    }
}
