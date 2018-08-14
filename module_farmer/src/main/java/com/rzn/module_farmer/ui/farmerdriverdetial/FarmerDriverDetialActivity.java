package com.rzn.module_farmer.ui.farmerdriverdetial;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.DriverDetialMessageBean;
import com.rzn.module_farmer.bean.KindTypeBean;
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
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvAddressTwo;
    private TextView tvTime;
    private TextView tvMessage;
    private TextView tvCommit;
    private ImageView ivCancel;
    private TextView tvTypess;
    DriverDetialMessageBean driverDetialMessageBean;

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
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvAddressTwo = (TextView) findViewById(R.id.tv_address_two);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        tvCommit = (TextView) findViewById(R.id.tv_commit);
        headbackground = (ImageView) findViewById(R.id.headbackground);
        ivCancel = (ImageView) findViewById(R.id.iv_cancel);
        tvTypess = (TextView) findViewById(R.id.tv_typess);
        findViewById(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(driverDetialMessageBean!=null && driverDetialMessageBean.getMobile()!=null){
                    PhoneUtils.dial(driverDetialMessageBean.getMobile());
                }else{
                    ToastUtils.showShort("暂无联系方式");
                }
            }
        });
//

        GlideUtils.loadImageRound(this, "http://p1.so.qhmsg.com/t01badad785f8e95ce2.jpg", ivPhoto, 60);
        GlideUtils.GaussianBlur(this, "http://p1.so.qhmsg.com/t01badad785f8e95ce2.jpg", headbackground, 8, 1);
    }

    //获取机手信息成功
    @Override
    public void driverMessageSuccess(DriverDetialMessageBean driverDetialMessageBean) {
           this.driverDetialMessageBean =driverDetialMessageBean;
        //給布局赋值
        showViewData(driverDetialMessageBean);


    }

    private void showViewData(DriverDetialMessageBean driverDetialMessageBean) {
        tvName.setText(driverDetialMessageBean.getName());
        tvContent.setText("从业" + driverDetialMessageBean.getYears() + "年" + "    " + driverDetialMessageBean.getBirthdayAfter());
        tvText.setText("从业" + driverDetialMessageBean.getYears() + "年神勇老司机" + ",神勇无敌!");
        if(driverDetialMessageBean.getJsonArrayKindType().size()==1){
            KindTypeBean kindTypeBean= driverDetialMessageBean.getJsonArrayKindType().get(0);
            tvContentOne.setText(kindTypeBean.getTypes());
            tvTypess.setText(kindTypeBean.getTypes()+"："+kindTypeBean.getUnitPrice()+"元");//不知道是啥
        }else if((driverDetialMessageBean.getJsonArrayKindType().size()>=2)){
            KindTypeBean kindTypeBean0= driverDetialMessageBean.getJsonArrayKindType().get(0);
            KindTypeBean kindTypeBean1= driverDetialMessageBean.getJsonArrayKindType().get(1);
            tvContentOne.setText(kindTypeBean0.getTypes());
            tvContentTwo.setText(kindTypeBean1.getTypes());
            tvTypess.setText(kindTypeBean0.getTypes()+"："+kindTypeBean0.getUnitPrice()+"元  "+kindTypeBean1.getTypes()+"："+kindTypeBean1.getUnitPrice()+"元");//不知道是啥
        }


//        tvPrice.setText(driverDetialMessageBean.get"元/亩");
        tvTitle.setText("");
        if (driverDetialMessageBean.getJsonArrayTaskPlace().size() == 1) {
            tvAddressTwo.setVisibility(View.GONE);
            tvAddress.setText(driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getProvinceName() + driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getCityName()
                    + driverDetialMessageBean.getJsonArrayTaskPlace().get(0).getAreaName() + "");
        } else if (driverDetialMessageBean.getJsonArrayTaskPlace().size() >= 2) {
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
