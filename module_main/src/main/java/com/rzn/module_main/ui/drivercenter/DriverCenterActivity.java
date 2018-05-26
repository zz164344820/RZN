package com.rzn.module_main.ui.drivercenter;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.setting.SettingActivity;
import com.zyhealth.expertlib.utils.GlideUtils;

import java.util.HashMap;
import java.util.Map;

import mlxy.utils.T;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverCenterActivity extends MVPBaseActivity<DriverCenterContract.View, DriverCenterPresenter> implements DriverCenterContract.View {

    private RelativeLayout rlOne;
    private RelativeLayout rlTwo;
    private RelativeLayout rlThree;
    private RelativeLayout rlFour;
    private TextView tvName;
    private TextView tvContent;
    private TextView tvText;
    private TextView tvNumberMu;
    private TextView tvMoney;
    private ImageView ivPhoto ,iv_background;
    private TextView tvWei;
    private LinearLayout llDriver;
    private ImageView ivSex;
    private ImageView ivEye;
    private ImageView ivEyes;
    DriverBean driverBean;
    private ImageView ivSetting;
    private ImageView ivFinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.act_driver_conter);
        initViews();
        initData();
        initListener();
        mPresenter.onCreate();
//        showLoading(false,"");
    }

    private void initListener() {
        ivEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivEyes.setVisibility(View.VISIBLE);
                ivEye.setVisibility(View.GONE);
                tvMoney.setText("******");
            }
        });
        ivEyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivEye.setVisibility(View.VISIBLE);
                ivEyes.setVisibility(View.GONE);
                tvMoney.setText(driverBean.getAddIncome() + "元");
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverCenterActivity.this, SettingActivity.class));
            }
        });
        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void initViews() {
        //头像
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);
        //姓名
        tvName = (TextView) findViewById(R.id.tv_name);
        //从业多少年
        tvContent = (TextView) findViewById(R.id.tv_content);
        //什么老司机
        tvText = (TextView) findViewById(R.id.tv_text);
//        findViewById(R.id.tv_name);
        tvNumberMu = (TextView) findViewById(R.id.tv_number_mu);
        tvMoney = (TextView) findViewById(R.id.tv_money);

        rlOne = (RelativeLayout) findViewById(R.id.rl_one);
        rlTwo = (RelativeLayout) findViewById(R.id.rl_two);
        rlThree = (RelativeLayout) findViewById(R.id.rl_three);
        rlFour = (RelativeLayout) findViewById(R.id.rl_four);


        tvWei = (TextView) findViewById(R.id.tv_wei);
        llDriver = (LinearLayout) findViewById(R.id.ll_driver);
        ivSex = (ImageView) findViewById(R.id.iv_sex);

        ivEye = (ImageView) findViewById(R.id.iv_eye);
        ivEyes = (ImageView) findViewById(R.id.iv_eyes);
        ivSetting = (ImageView) findViewById(R.id.iv_setting);
        ivFinish = (ImageView) findViewById(R.id.iv_finish);
        iv_background = (ImageView) findViewById(R.id.iv_background);

        GlideUtils.loadImageRound(this,"http://www.fzlol.com/upimg/allimg/140408/1_1G0291243.jpg",ivPhoto,40);
        GlideUtils.GaussianBlur(this,"http://www.fzlol.com/upimg/allimg/140408/1_1G0291243.jpg",iv_background,8,1);
    }


    private void initData() {
        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if (TextUtils.isEmpty(loginResponseBean.getHandlerId())) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("handlerId", loginResponseBean.getHandlerId());
        mPresenter.getDriverData(map);

//        if (){}
    }


    @Override
    public void getDataSuccess(DriverBean driverBean) {
        this.driverBean = driverBean;
//        driverBean.setStatus("3");
        tvWei.setVisibility(View.GONE);
        llDriver.setVisibility(View.GONE);
        rlOne.setBackgroundResource(R.drawable.icon_hui);
        rlTwo.setBackgroundResource(R.drawable.icon_hui);
        rlThree.setBackgroundResource(R.drawable.icon_hui);
        rlFour.setBackgroundResource(R.drawable.icon_hui);
        if ("0".equals(driverBean.getStatus())) {//禁用

        } else if ("1".equals(driverBean.getStatus())) {//审核中
            tvWei.setVisibility(View.VISIBLE);
            tvWei.setText("手机认证审核中");
            tvWei.setBackgroundResource(R.drawable.driver_shape_background);
        } else if ("2".equals(driverBean.getStatus())) {//审核拒绝
            tvWei.setVisibility(View.VISIBLE);
            tvWei.setText("机手认证审核未通过");
            tvWei.setBackgroundResource(R.drawable.driver_shape_background_red);
        } else if ("3".equals(driverBean.getStatus())) {//审核通过
            llDriver.setVisibility(View.VISIBLE);
            rlOne.setBackgroundResource(R.drawable.icon_green);
            rlTwo.setBackgroundResource(R.drawable.icon_chen);
            rlThree.setBackgroundResource(R.drawable.icon_yellow);
            rlFour.setBackgroundResource(R.drawable.icon_blue);
        }
        tvName.setText(driverBean.getName());
        tvText.setText("从业多年老司机，技术超群！");
        if ("0".equals(driverBean.getSex())) {
            //女
            ivSex.setBackgroundResource(R.drawable.icon_sex_gril);
        } else if ("1".equals(driverBean.getSex())) {
            //男
            ivSex.setBackgroundResource(R.drawable.icon_sex_boy);
        }
        tvNumberMu.setText(driverBean.getAddAreas() + "亩");
        tvMoney.setText(driverBean.getAddIncome() + "元");

    }
}
