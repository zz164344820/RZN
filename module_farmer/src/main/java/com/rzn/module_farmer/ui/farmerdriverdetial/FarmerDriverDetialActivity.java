package com.rzn.module_farmer.ui.farmerdriverdetial;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;


/**
 * 农户端-机手详情
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerDriverDetialActivity extends MVPBaseActivity<FarmerDriverDetialContract.View, FarmerDriverDetialPresenter> implements FarmerDriverDetialContract.View {

    private ImageView ivPhoto;
    private TextView tvName;
    private TextView tvContent;
    private TextView tvText;
    private TextView tvContentOne;
    private TextView tvContentTwo;
    private TextView tvContentThree;
    private TextView tvContentFour;
    private TextView tvPrice;
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvAddressTwo;
    private TextView tvTime;
    private TextView tvMessage;
    private TextView tvCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_farmer_driver_detial);
        mPresenter.onCreate();
        initViews();
        initListener();
//        showLoading(false, "");
    }

    private void initListener() {
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求预约机手接口   文档7接口对应
                mPresenter.httpAppointmentDriver("需求作业订单id", "机手id");
            }
        });
    }

    private void initViews() {

        ivPhoto = (ImageView) findViewById(R.id.iv_photo);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvText = (TextView) findViewById(R.id.tv_text);
        tvContentOne = (TextView) findViewById(R.id.tv_content_one);
        tvContentTwo = (TextView) findViewById(R.id.tv_content_two);
        tvContentThree = (TextView) findViewById(R.id.tv_content_three);
        tvContentFour = (TextView) findViewById(R.id.tv_content_four);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvAddressTwo = (TextView) findViewById(R.id.tv_address_two);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        tvCommit = (TextView) findViewById(R.id.tv_commit);

    }

    @Override
    public void appointmentSuccess() {
        //跳转到确认预约界面
    }

    @Override
    public void appointmentFailed() {

    }
}
