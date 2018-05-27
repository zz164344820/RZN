package com.rzn.module_main.ui.mesagecenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MessageCenterActivity extends MVPBaseActivity<MesageCenterContract.View, MesageCenterPresenter> implements MesageCenterContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_messagecenter);
        ButterKnife.bind(this);
        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("消息中心");
    }

    @OnClick({R2.id.ll_orderMessage, R2.id.ll_appointment, R2.id.ll_notification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.ll_orderMessage:
                break;
            case R2.id.ll_appointment:
                break;
            case R2.id.ll_notification:
                break;
        }
    }
}
