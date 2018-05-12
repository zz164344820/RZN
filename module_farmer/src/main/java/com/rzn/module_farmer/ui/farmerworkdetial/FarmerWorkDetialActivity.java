package com.rzn.module_farmer.ui.farmerworkdetial;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.FarmerOrderDetialBean;


/**
 * 农户端 作业详情
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerWorkDetialActivity extends MVPBaseActivity<FarmerWorkDetialContract.View, FarmerWorkDetialPresenter> implements FarmerWorkDetialContract.View {

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
    private TextView tvDetelPost;
    private TextView tvMore;
    private TextView tvWorded;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_farmer_order_detial);
        initViews();
        initData();
        initListener();
        mPresenter.onCreate();
//        showLoading(false,"");
    }

    /**
     * 初始化数据
     */
    private void initData() {

        mPresenter.httpLoadData("");

    }

    /**
     * 初始化监听
     */
    private void initListener() {
    }

    /**
     * 初始化view
     */
    private void initViews() {
        setTitle("作業訂單詳情");
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
        tvDetelPost = (TextView) findViewById(R.id.tv_detel_post);
        tvMore = (TextView) findViewById(R.id.tv_more);
        tvWorded = (TextView) findViewById(R.id.tv_worded);
    }

    /**
     * 加载数据成功
     */
    @Override
    public void onLoadSuccess(FarmerOrderDetialBean farmerOrderDetialBean) {
        //給view賦值
        putView(farmerOrderDetialBean);
    }

    private void putView(FarmerOrderDetialBean farmerOrderDetialBean) {


        if (farmerOrderDetialBean != null) {
            tvDetelPost.setVisibility(View.GONE);
            tvWorded.setVisibility(View.GONE);
            tvMore.setVisibility(View.GONE);
            if (1 == farmerOrderDetialBean.getStatus()) {
                tvCancel.setText("待接单");
                tvWorded.setVisibility(View.VISIBLE);
                tvMore.setVisibility(View.VISIBLE);
            } else if (2 == farmerOrderDetialBean.getStatus()) {
                tvCancel.setText("待作业");
                tvWorded.setVisibility(View.VISIBLE);
                tvMore.setVisibility(View.VISIBLE);
                tvMore.setText("取消订单");
                tvWorded.setText("联系机手");

            } else if (3 == farmerOrderDetialBean.getStatus()) {
                tvCancel.setText("取消");
                tvDetelPost.setVisibility(View.VISIBLE);
            } else if (4 == farmerOrderDetialBean.getStatus()) {
                tvCancel.setText("已完成");
            }
            tvAddress.setText(farmerOrderDetialBean.getAddress());
            tvAddressTwo.setText(farmerOrderDetialBean.getTaskPlace());
            tvTime.setText(farmerOrderDetialBean.getUpdateTime());
            tvMessage.setText(farmerOrderDetialBean.getRemark());
            tvPrice.setText(farmerOrderDetialBean.getUnitPrice());
            tvMoney.setText(farmerOrderDetialBean.getTotalprice());
            tvCreateTime.setText(farmerOrderDetialBean.getCreateTime());
            tvPost.setText("");


        }

    }

    /**
     * 加载数据失败
     */
    @Override
    public void onLoafFailed() {

    }
}
