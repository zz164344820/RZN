package com.rzn.module_farmer.ui.farmerdriverdetial;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.DriverDetialMessageBean;
import com.zyhealth.expertlib.utils.GlideUtils;


/**
 * 农户端-机手详情
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerDriverDetialActivity extends MVPBaseActivity<FarmerDriverDetialContract.View, FarmerDriverDetialPresenter> implements FarmerDriverDetialContract.View {

    private ImageView ivPhoto, headbackground;
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
    private ImageView ivCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.act_farmer_driver_detial);
        mPresenter.onCreate();
        initViews();
        initData();
        initListener();
//        showLoading(false, "");
    }

    private void initData() {
        String driverId = getIntent().getStringExtra("handlerId");
        mPresenter.httpDriverMessage(driverId);
    }

    private void initListener() {
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getFarmerOreder();
            }
        });
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        headbackground = (ImageView) findViewById(R.id.headbackground);
        ivCancel = (ImageView) findViewById(R.id.iv_cancel);

        GlideUtils.loadImageRound(this, "http://p0.so.qhmsg.com/bdr/_240_/t01eccf86bc0d2cf28f.jpg", ivPhoto, 40);
        GlideUtils.GaussianBlur(this, "http://p0.so.qhmsg.com/bdr/_240_/t01eccf86bc0d2cf28f.jpg", headbackground, 8, 1);
    }

    //获取机手信息成功
    @Override
    public void driverMessageSuccess(DriverDetialMessageBean driverDetialMessageBean) {

        //給布局赋值
        showViewData(driverDetialMessageBean);


    }

    private void showViewData(DriverDetialMessageBean driverDetialMessageBean) {
        tvName.setText(driverDetialMessageBean.getName());
        tvContent.setText("从业" + driverDetialMessageBean.getYears() + "年" + "    " + driverDetialMessageBean.getBirthday().substring(2, 2) + "后");
        tvText.setText("从业" + driverDetialMessageBean.getYears() + "年神勇老司机" + ",神勇无敌!");
        tvContentOne.setText("llllll");
        tvContentTwo.setText("ssssss");
        tvPrice.setText("元/亩");
        tvTitle.setText("");
        if (driverDetialMessageBean.getJsonArrayTaskPlace().size() == 1) {
            tvAddressTwo.setVisibility(View.GONE);
            tvAddress.setText(driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getProvinceName() + driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getCityName()
                    + driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getAreaName() + "");
        } else if (driverDetialMessageBean.getJsonArrayTaskPlace().size() == 2) {
            tvAddress.setText(driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getProvinceName() + driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getCityName()
                    + driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getAreaName() + "");
            tvAddressTwo.setText(driverDetialMessageBean.getJsonArrayTaskPlace().get(1).getProvinceName() + driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getCityName() +
                    driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getAreaName() + "");
        }
        tvTime.setText(driverDetialMessageBean.getCreateTimeInfo());
        tvMessage.setText(driverDetialMessageBean.getRemark());
    }


    @Override
    public void appointmentSuccess() {
        //跳转到确认预约界面
        // startActivity( new Intent(this ,));
    }


}
