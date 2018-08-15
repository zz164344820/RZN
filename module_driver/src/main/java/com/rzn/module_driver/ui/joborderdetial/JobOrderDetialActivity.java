package com.rzn.module_driver.ui.joborderdetial;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.JobOrderDetialBean;
import com.rzn.commonbaselib.listener.ConfirmationCallBack;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.RZNUtils;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.driverlist.SelectMatchingPopWindow;

import java.util.HashMap;
import java.util.Map;

import mlxy.utils.T;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = "/driver/joborderdetial")
public class JobOrderDetialActivity extends MVPBaseActivity<JobOrderDetialContract.View, JobOrderDetialPresenter> implements JobOrderDetialContract.View {

    private TextView tvCancel;
    private TextView tvTitles;
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
    private String farmerTaskId;
    JobOrderDetialBean jobOrderDetialBean;
    private TextView tvT;
    final SelectMatchingPopWindows[] window = new SelectMatchingPopWindows[1];
    private LinearLayout llAll;


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
//                if ("farmer".equals(flag)) {
//
//                } else if ("driver".equals(flag)) {
//
//                }
                Map<String, String> map = new HashMap<>();
                map.put("farmerTaskId", farmerTaskId);
                mPresenter.deletePost(map);
            }
        });
        tvHadWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //已完成作业
                if ("farmer".equals(flag)) {
                    if ("编辑".equals(tvHadWork.getText().toString())) {
                        //修改作业需求
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("jobOrderDetialBean", jobOrderDetialBean);
                        bundle.putString("jobdetial", "jobdetial");
                        ARouter.getInstance().build("/farmer/sendwork").withBundle("bean", bundle).navigation();
                    } else if ("联系机手".equals(tvHadWork.getText().toString())) {
                        if (!TextUtils.isEmpty(jobOrderDetialBean.getMobile())) {
                            PhoneUtils.dial(jobOrderDetialBean.getMobile());
                        }else{
                            ToastUtils.showShort("暂无联系电话");
                        }
                    }
                } else if ("driver".equals(flag)) {
                    if ("完成作业".equals(tvHadWork.getText().toString())) {
                        RZNUtils.popupDialog_Confirm(JobOrderDetialActivity.this, "确认已完成作业?", "取消", "确定", new ConfirmationCallBack() {
                            @Override
                            public void cancel() {

                            }

                            @Override
                            public void confirm() {
                                Map<String, String> map = new HashMap<>();
                                map.put("farmerTaskId", farmerTaskId);
                                map.put("realAreas", jobOrderDetialBean.getAreas().toString());
                                map.put("realTotalprice", jobOrderDetialBean.getTotalprice().toString());
                                mPresenter.finishWork(map);
                            }
                        });

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
                        Map<String, String> map = new HashMap<>();
                        map.put("farmerTaskId", farmerTaskId);
                        mPresenter.cancelPost(map);
                    }
                } else if ("driver".equals(flag)) {
                    if ("更多".equals(tvMore.getText().toString())) {
                        showSelectPic(window);
                    }
                }
            }
        });
    }

    private void showSelectPic(final SelectMatchingPopWindows[] window) {
        window[0] = new SelectMatchingPopWindows(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.tv_closeRange) {
                    PhoneUtils.dial(jobOrderDetialBean.getMobile());
                    window[0].dismiss();
                } else if (v.getId() == R.id.tv_normal) {
                    //取消订单
                    Map<String, String> map = new HashMap<>();
                    map.put("farmerTaskId", farmerTaskId);
                    mPresenter.cancelPost(map);
                    window[0].dismiss();
                }
            }
        });

        window[0].showAtLocation(llAll, Gravity.BOTTOM, 10, 10);
    }


    private void initViews() {
        setTitle("作业订单详情");
        tvCancel = (TextView) findViewById(R.id.tv_cancel);//取消按钮
        tvTitles = (TextView) findViewById(R.id.tv_titles);//多少亩标题
        tvAddress = (TextView) findViewById(R.id.tv_address);//地址
        tvAddressTwo = (TextView) findViewById(R.id.tv_address_two);//地址
        tvTime = (TextView) findViewById(R.id.tv_time);//时间
        tvMessage = (TextView) findViewById(R.id.tv_message);//说明
        tvPrice = (TextView) findViewById(R.id.tv_price);//价格
        tvMoney = (TextView) findViewById(R.id.tv_money);//钱数
        tvCreateTime = (TextView) findViewById(R.id.tv_create_time);//创建时间
        tvPost = (TextView) findViewById(R.id.tv_post);//单号
        tvCancelTime = (TextView) findViewById(R.id.tv_cancel_time);//取消时间
        tvT = (TextView) findViewById(R.id.tv_t);
        llAll = (LinearLayout) findViewById(R.id.ll_all);



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
        farmerTaskId = getIntent().getStringExtra("farmerTaskId");
//        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if ("farmer".equals(flag)) {
            mPresenter.getFarmerData(farmerTaskId);
        } else if ("driver".equals(flag)) {
            mPresenter.getData(farmerTaskId);
        }

    }

    private void showView(JobOrderDetialBean jobOrderDetialBean) {

        this.jobOrderDetialBean = jobOrderDetialBean;
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

        tvAddress.setText("• " + jobOrderDetialBean.getProvinceName() + jobOrderDetialBean.getAreaName() + jobOrderDetialBean.getCityName() + "");
//        tvAddress.setText(jobOrderDetialBean.getAddress());
        tvAddressTwo.setText("• " + jobOrderDetialBean.getTaskPlace());
        tvTime.setText(jobOrderDetialBean.getStartDate() + "至" + jobOrderDetialBean.getEndDate());
        tvMessage.setText(jobOrderDetialBean.getRemark());
        tvPrice.setText(jobOrderDetialBean.getUnitPrice() + "元/亩");
        tvMoney.setText(jobOrderDetialBean.getTotalprice() + "元");
        tvCreateTime.setText(jobOrderDetialBean.getCreateTime());
        tvPost.setText(jobOrderDetialBean.getOrderNo());
        if ("1".equals(jobOrderDetialBean.getFlag())) {
            tvTitles.setText(jobOrderDetialBean.getAreas() + "亩/" + "集中连片" + jobOrderDetialBean.getFlagNum() + "块");
        } else if ("2".equals(jobOrderDetialBean.getFlag())) {
            tvTitles.setText(jobOrderDetialBean.getAreas() + "亩/" + "零星分散" + jobOrderDetialBean.getFlagNum() + "块");
        }
        tvT.setText(jobOrderDetialBean.getTypes());
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
            tvAddress.setText("• " + jobOrderDetialBean.getProvinceName() + jobOrderDetialBean.getAreaName() + jobOrderDetialBean.getCityName() + "");
//            tvAddress.setText(jobOrderDetialBean.getAddress());
            tvAddressTwo.setText("• " + jobOrderDetialBean.getTaskPlace());
            tvTime.setText(jobOrderDetialBean.getStartDate() + "至" + jobOrderDetialBean.getEndDate());
            tvMessage.setText(jobOrderDetialBean.getRemark());
            tvPrice.setText(jobOrderDetialBean.getUnitPrice() + "元/亩");
            tvMoney.setText(jobOrderDetialBean.getTotalprice() + "元");
            tvCreateTime.setText(jobOrderDetialBean.getCreateTime());
            tvPost.setText(jobOrderDetialBean.getOrderNo());
            if ("1".equals(jobOrderDetialBean.getFlag())) {
                tvTitles.setText(jobOrderDetialBean.getAreas() + "亩/" + "集中连片" + jobOrderDetialBean.getFlagNum() + "块");
            } else if ("2".equals(jobOrderDetialBean.getFlag())) {
                tvTitles.setText(jobOrderDetialBean.getAreas() + "亩/" + "零星分散" + jobOrderDetialBean.getFlagNum() + "块");
            }
            tvT.setText(jobOrderDetialBean.getTypes());


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

    /**
     * 农户取消订单成功
     */
    @Override
    public void cancelPostSuccess() {
        finish();
    }

    /**
     * 完成訂單
     */
    @Override
    public void finishWorkSuccess() {
        finish();

    }

    /**
     *
     */
    @Override
    public void deletePostSuccess() {
        finish();
    }
}
