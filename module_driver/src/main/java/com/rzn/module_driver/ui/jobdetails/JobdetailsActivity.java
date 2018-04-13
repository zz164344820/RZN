package com.rzn.module_driver.ui.jobdetails;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobdetailsActivity extends MVPBaseActivity<JobdetailsContract.View, JobdetailsPresenter> implements JobdetailsContract.View {


    private TextView tvSure;
    private TextView tvContent;
    private TextView tvWorkAddress;
    private TextView tvMoneyNumber;
    private TextView tvMoney;
    private TextView tvName;
    private ImageView ivPhoto;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_work_detial);
        initViews();
        initListener();
    }


    /**
     * 初始化监听
     */
    private void initListener() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求网络抢单按钮
            }
        });
    }

    /**
     * 初始化布局
     */
    private void initViews() {
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        tvMoneyNumber = (TextView) findViewById(R.id.tv_money_number);
        tvWorkAddress = (TextView) findViewById(R.id.tv_work_address);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvSure = (TextView) findViewById(R.id.tv_sure);
    }
}
