package com.rzn.module_driver.ui.joborderdetial;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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


    }

    /**
     * 初始化数据
     */
    private void initData() {
        String flag = getIntent().getStringExtra("flag");
        String farmerTaskId = getIntent().getStringExtra("farmerTaskId");
//        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if ("farmer".equals(flag)) {
            mPresenter.getFarmerData(farmerTaskId);
        } else if ("driver".equals(flag)) {
            mPresenter.getData(farmerTaskId);
        }

    }

    private void showView(JobOrderDetialBean jobOrderDetialBean) {

        if (jobOrderDetialBean != null) {

            if ("1".equals(jobOrderDetialBean.getStatus())) {
                tvCancel.setText("待接单");
            } else if ("2".equals(jobOrderDetialBean.getStatus())) {
                tvCancel.setText("待作业");
            } else if ("3".equals(jobOrderDetialBean.getStatus())) {
                tvCancel.setText("取消");
            } else if ("4".equals(jobOrderDetialBean.getStatus())) {
                tvCancel.setText("已完成");
            }
            tvAddress.setText(jobOrderDetialBean.getAddress());
            tvAddressTwo.setText(jobOrderDetialBean.getTaskPlace());
            tvTime.setText(jobOrderDetialBean.getUpdateTime());
            tvMessage.setText(jobOrderDetialBean.getRemark());
            tvPrice.setText(jobOrderDetialBean.getUnitPrice());
            tvMoney.setText(jobOrderDetialBean.getTotalprice());
            tvCreateTime.setText(jobOrderDetialBean.getCreateTime());
            tvPost.setText("");



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
