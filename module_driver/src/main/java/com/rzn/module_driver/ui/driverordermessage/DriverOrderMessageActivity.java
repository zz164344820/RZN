package com.rzn.module_driver.ui.driverordermessage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.OrederInfo;
import com.zyhealth.expertlib.utils.MLog;

import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverOrderMessageActivity extends MVPBaseActivity<DriverOrderMessageContract.View, DriverOrderMessagePresenter> implements DriverOrderMessageContract.View {

    private TextView tvStartPost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_orders_message);
        initViews();
        initListener();
        mPresenter.onCreate();
//        showLoading(false,"");

    }



    private void initListener() {
        tvStartPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.httpPost();
            }
        });
    }

    private void initViews() {
        setTitle("完善接单信息");
        tvStartPost = (TextView) findViewById(R.id.tv_start_post);

    }

    /**
     * 开始接单成功
     */
    @Override
    public void postSuccessed() {

    }

    /**
     * 开始接单失败
     */
    @Override
    public void postFailed() {

    }

    @Override
    public void setOrderInfo(List<OrederInfo> list) {
        MLog.e(list.size()+"--------");
    }
}
