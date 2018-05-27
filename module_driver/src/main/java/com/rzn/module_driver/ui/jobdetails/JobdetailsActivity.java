package com.rzn.module_driver.ui.jobdetails;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.rzn.commonbaselib.bean.JobOrderDetialBean;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.driver_identification.Driver_identificationActivity;
import com.rzn.module_driver.ui.jobOrder.myjoborder.MyjobOrderActivity;

import java.util.HashMap;
import java.util.Map;


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
    private TextView tvTye;
    private ImageView ivPhone;
    private TextView tvTitleContent;
    private String farmerTaskId;
    private String phone;
    private TextView tvWorkTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_work_detial);
        mPresenter.onCreate();
        farmerTaskId = getIntent().getStringExtra("farmerTaskId");
        initViews();
        initData();
        initListener();
    }

    private void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("farmerTaskId", farmerTaskId);
        mPresenter.getMessage(map);
    }


    /**
     * 初始化监听
     */
    private void initListener() {
        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneUtils.dial(phone);
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求网络抢单按钮
                Map<String, String> map = new HashMap<>();
                LoginResponseBean bean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                map.put("handlerId", bean.getHandlerId());
                map.put("farmerTaskId", farmerTaskId);
                mPresenter.getPost(map);
                //跳转我的作业列表
//                startActivity(new Intent(JobdetailsActivity.this, MyjobOrderActivity.class));
            }
        });
    }

    /**
     * 初始化布局
     */
    private void initViews() {
        setTitle("作业详情");
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        tvMoneyNumber = (TextView) findViewById(R.id.tv_money_number);
        tvWorkAddress = (TextView) findViewById(R.id.tv_work_address);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvSure = (TextView) findViewById(R.id.tv_sure);
        tvTye = (TextView) findViewById(R.id.tv_tye);
        ivPhone = (ImageView) findViewById(R.id.iv_phone);
        tvTitleContent = (TextView) findViewById(R.id.tv_title_content);
        tvWorkTime = (TextView) findViewById(R.id.tv_work_time);
    }

    @Override
    public void getMessageSuccess(JobBean jobOrderDetialBeans) {
        phone = jobOrderDetialBeans.getMobile();
        tvName.setText(jobOrderDetialBeans.getName());
        tvTye.setText(jobOrderDetialBeans.getTypes());
        tvMoney.setText(jobOrderDetialBeans.getTotalprice());
        tvMoneyNumber.setText("(" + jobOrderDetialBeans.getUnitPrice() + "元/亩）");
        if ("1".equals(jobOrderDetialBeans.getFlag())) {
            tvTitleContent.setText(jobOrderDetialBeans.getAreas() + "亩/" + "集中连片" + jobOrderDetialBeans.getFlagNum() + "块");
        } else if ("2".equals(jobOrderDetialBeans.getFlag())) {
            tvTitleContent.setText(jobOrderDetialBeans.getAreas() + "亩/" + "零星分散" + jobOrderDetialBeans.getFlagNum() + "块");
        }
        tvWorkAddress.setText(jobOrderDetialBeans.getProvinceName() + jobOrderDetialBeans.getAreaName() + jobOrderDetialBeans.getCityName() + "");
        tvContent.setText(jobOrderDetialBeans.getRemark());
        tvWorkTime.setText(jobOrderDetialBeans.getStartDate() + "至" + jobOrderDetialBeans.getEndDate());
    }

    @Override
    public void getPostSuccess() {
          finish();
    }
}
