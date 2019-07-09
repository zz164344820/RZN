package com.rzn.module_main.ui.agreement;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.ui.UiContract;
import com.rzn.commonbaselib.ui.UiPresenter;
import com.rzn.module_main.R;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AgreementActivity extends MVPBaseActivity<AgreementContract.View, AgreementPresenter> implements AgreementContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_agreement);
        mPresenter.onCreate();

    }

    @Override
    public void initView() {
        super.initView();
        setTitle("用户协议");
    }
}
