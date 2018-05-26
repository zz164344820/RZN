package com.rzn.module_driver.ui.joborderdetial;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_driver.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobOrderDetialActivity extends MVPBaseActivity<JobOrderDetialContract.View, JobOrderDetialPresenter> implements JobOrderDetialContract.View {

    private TextView tvCancel;
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvAddressTwo;
    private TextView tvTime;
    private TextView tvMessage;
    private TextView tvPrice;
    private TextView tvMoney;
    private TextView tvCreateTime;
    private TextView tvPost;
    private TextView tvCancelTime;
    private TextView tvDeletePost;
    private TextView tvMore;
    private TextView tvHadWork;
    private String flag;
    private LinearLayout llCancelTime;
    private LinearLayout llMu;
    private TextView tvMu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_order_detial);
        mPresenter.onCreate();
        initViews();
        initData();
        initListener();
//        showLoading(false,"");
    }

    private void initListener() {
        tvDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除订单
                if ("farmer".equals(flag)) {

                } else if ("driver".equals(flag)) {

                }
            }
        });
        tvHadWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //已完成作业
                if ("farmer".equals(flag)) {
                    if ("编辑".equals(tvHadWork.getText().toString())) {

                    } else if ("联系机手".equals(tvHadWork.getText().toString())) {

                    }
                } else if ("driver".equals(flag)) {
                    if ("完成作业".equals(tvHadWork.getText().toString())) {

                    }
                }
            }
        });
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更多
                if ("farmer".equals(flag)) {
                    if ("取消订单".equals(tvMore.getText().toString())) {

                    }
                } else if ("driver".equals(flag)) {
                    if ("更多".equals(tvMore.getText().toString())) {

                    }
                }
            }
        });
    }


    private void initViews() {
        setTitle("作业订单详情");
        tvCancel = (TextView) findViewById(R.id.tv_cancel);//取消按钮
        tvTitle = (TextView) findViewById(R.id.tv_title);//多少亩标题
        tvAddress = (TextView) findViewById(R.id.tv_address);//地址
        tvAddressTwo = (TextView) findViewById(R.id.tv_address_two);//地址
        tvTime = (TextView) findViewById(R.id.tv_time);//时间
        tvMessage = (TextView) findViewById(R.id.tv_message);//说明
        tvPrice = (TextView) findViewById(R.id.tv_price);//价格
        tvMoney = (TextView) findViewById(R.id.tv_money);//钱数
        tvCreateTime = (TextView) findViewById(R.id.tv_create_time);//创建时间
        tvPost = (TextView) findViewById(R.id.tv_post);//单号
        tvCancelTime = (TextView) findViewById(R.id.tv_cancel_time);//取消时间


        tvDeletePost = (TextView) findViewById(R.id.tv_delete_post);
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvHadWork = (TextView) findViewById(R.id.tv_had_work);
        llCancelTime = (LinearLayout) findViewById(R.id.ll_cancel_time);

        llMu = (LinearLayout) findViewById(R.id.ll_mu);
        tvMu = (TextView) findViewById(R.id.tv_mu);


    }

    /**
     * 初始化数据
     */
    private void initData() {
        flag = getIntent().getStringExtra("flag");
        String farmerTaskId = getIntent().getStringExtra("farmerTaskId");
//        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if ("farmer".equals(flag)) {
            mPresenter.getFarmerData(farmerTaskId);
        } else if ("driver".equals(flag)) {
            mPresenter.getData(farmerTaskId);
        }

    }

    private void showView(JobOrderDetialBean jobOrderDetialBean) {


        //判断是机手还是农民
        if ("farmer".equals(flag)) {
            showFarmer(jobOrderDetialBean);
        } else if ("driver".equals(flag)) {
            showDriver(jobOrderDetialBean);
        }
    }

    private void showDriver(JobOrderDetialBean jobOrderDetialBean) {
        tvDeletePost.setVisibility(View.GONE);
        tvMore.setVisibility(View.GONE);
        tvHadWork.setVisibility(View.GONE);
        llCancelTime.setVisibility(View.GONE);
        llMu.setVisibility(View.GONE);
        if ("2".equals(jobOrderDetialBean.getStatus())) {
            tvCancel.setText("待作业");
            tvMore.setVisibility(View.VISIBLE);
            tvHadWork.setVisibility(View.VISIBLE);
            tvMore.setText("更多");
            tvMore.setBackgroundResource(R.drawable.driver_shape_background_green);
            tvHadWork.setText("完成作业");
            tvHadWork.setBackgroundResource(R.drawable.driver_shape_background);
        } else if ("3".equals(jobOrderDetialBean.getStatus())) {
            tvCancel.setText("已取消");
            llCancelTime.setVisibility(View.VISIBLE);
            tvDeletePost.setBackgroundResource(R.drawable.driver_shape_background_red);
        } else if ("4".equals(jobOrderDetialBean.getStatus())) {
            tvCancel.setText("已完成");
        }


        tvAddress.setText(jobOrderDetialBean.getAddress());
        tvAddressTwo.setText(jobOrderDetialBean.getTaskPlace());
        tvTime.setText(jobOrderDetialBean.getStartDate() + "至" + jobOrderDetialBean.getEndDate());
        tvMessage.setText(jobOrderDetialBean.getRemark());
        tvPrice.setText(jobOrderDetialBean.getUnitPrice() + "元/亩");
        tvMoney.setText(jobOrderDetialBean.getTotalprice() + "元");
        tvCreateTime.setText(jobOrderDetialBean.getCreateTime());
        tvPost.setText(jobOrderDetialBean.getOrderNo());

    }

    private void showFarmer(JobOrderDetialBean jobOrderDetialBean) {
        tvDeletePost.setVisibility(View.GONE);
        tvMore.setVisibility(View.GONE);
        tvHadWork.setVisibility(View.GONE);
        llCancelTime.setVisibility(View.GONE);
        llMu.setVisibility(View.GONE);
        if (jobOrderDetialBean != null) {

            if ("1".equals(jobOrderDetialBean.getStatus())) {
                tvCancel.setText("待接单");
                tvMore.setVisibility(View.VISIBLE);
                tvMore.setText("取消订单");
                tvMore.setBackgroundResource(R.drawable.driver_shape_background_hui);
                tvHadWork.setVisibility(View.VISIBLE);
                tvHadWork.setText("编辑");

//                tvHadWork.setBackground();
                tvHadWork.setBackgroundResource(R.drawable.driver_shape_background);
            } else if ("2".equals(jobOrderDetialBean.getStatus())) {
                tvCancel.setText("待作业");
                tvMore.setVisibility(View.VISIBLE);
                tvMore.setText("取消订单");
                tvMore.setBackgroundResource(R.drawable.driver_shape_background_hui);
                tvHadWork.setVisibility(View.VISIBLE);
                tvHadWork.setText("联系机手");
//                tvHadWork.setBackground();
                tvHadWork.setBackgroundResource(R.drawable.driver_shape_background_green);
            } else if ("3".equals(jobOrderDetialBean.getStatus())) {
                tvCancel.setText("取消");
                tvDeletePost.setVisibility(View.VISIBLE);
                tvDeletePost.setBackgroundResource(R.drawable.driver_shape_background_red);
                llCancelTime.setVisibility(View.VISIBLE);
            } else if ("4".equals(jobOrderDetialBean.getStatus())) {
                tvCancel.setText("已完成");
                llMu.setVisibility(View.VISIBLE);
                tvMu.setText(jobOrderDetialBean.getAreas() + "亩");
            }
            tvAddress.setText(jobOrderDetialBean.getAddress());
            tvAddressTwo.setText(jobOrderDetialBean.getTaskPlace());
            tvTime.setText(jobOrderDetialBean.getStartDate() + "至" + jobOrderDetialBean.getEndDate());
            tvMessage.setText(jobOrderDetialBean.getRemark());
            tvPrice.setText(jobOrderDetialBean.getUnitPrice() + "元/亩");
            tvMoney.setText(jobOrderDetialBean.getTotalprice() + "元");
            tvCreateTime.setText(jobOrderDetialBean.getCreateTime());
            tvPost.setText(jobOrderDetialBean.getOrderNo());


        }

    }

    /**
     * 初始化数据成功
     */
    @Override
    public void getDataSuccess(JobOrderDetialBean jobOrderDetialBean) {
        //给布局文件赋值
        showView(jobOrderDetialBean);

    }


    @Override
    public void getDataFaile() {

    }
}
