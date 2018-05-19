package com.rzn.module_driver.ui.driverordermessage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.OrederInfo;
import com.zyhealth.expertlib.utils.MLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                Map<String,String> map = new HashMap<>();
                mPresenter.supplementOrderInfo(map);
            }
        });
    }

    private void initViews() {
        setTitle("完善接单信息");
        tvStartPost = (TextView) findViewById(R.id.tv_start_post);

    }




    @Override
    public void setOrderInfo(List<OrederInfo> list) {
        MLog.e(list.size()+"--------");
    }
}
