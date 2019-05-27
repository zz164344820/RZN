package com.rzn.module_driver.ui.joborderdetial;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.JobOrderDetialBean;
import com.rzn.commonbaselib.listener.ConfirmationCallBack;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.RZNUtils;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.alipayV2.AlipayUtilV2;
import com.rzn.module_driver.ui.driverlist.SelectMatchingPopWindow;
import com.rzn.module_driver.ui.joborderdetial.bean.ZhifubaoBean;
import com.rzn.module_driver.ui.joborderdetial.view.NumberPopupwindow;
import com.rzn.module_driver.ui.joborderdetial.view.PayPopupwindow;
import com.rzn.module_driver.ui.wxapi.WechatPrePayInfoEntity;
import com.rzn.module_driver.ui.wxapi.WxPayUtilV2;

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
    private LinearLayout llDaiZhiFu;
    private TextView tvYouhuiJinE;
    private TextView tvDingDanJinE;
    private TextView tvXuFuKuan;
    private LinearLayout llPayMoth;
    private TextView tvPayType;
    private LinearLayout llPayTime;
    private TextView tvPayTime;
    private TextView tvPayTime1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_order_detial);
        mPresenter.onCreate();
        initViews();
//        initData();
        initListener();
//        showLoading(false,"");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
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
                        } else {
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


                                //弹出弹窗，让机手填写实际完成亩数
                                final NumberPopupwindow numberPopupwindow = new NumberPopupwindow(JobOrderDetialActivity.this);
                                if (numberPopupwindow.isShowing()) {
                                    return;
                                }

                                numberPopupwindow.setOnClickPopListener(new NumberPopupwindow.OnPopupClickListener() {
                                    @Override
                                    public void onClick(String paytype) {

                                        if (Integer.valueOf(paytype) <(int)Double.parseDouble(jobOrderDetialBean.getAreas().toString())) {//int i = (int)Double.parseDouble(str);// Integer.valueOf(jobOrderDetialBean.getAreas().toString())
                                            Map<String, String> map = new HashMap<>();
                                            map.put("farmerTaskId", farmerTaskId);
                                            map.put("realAreas", paytype);//jobOrderDetialBean.getAreas().toString()
                                            map.put("realTotalprice", jobOrderDetialBean.getTotalprice().toString());
                                            mPresenter.finishWork(map);
                                        } else {
                                            Toast.makeText(JobOrderDetialActivity.this, "输入亩数超过了农户需要作业亩数，请核实后正确填写！",Toast.LENGTH_SHORT).show();
                                        }


                                        numberPopupwindow.dismiss();
                                    }
                                });

                                numberPopupwindow.showAtLocation(llAll, Gravity.BOTTOM, 0, 0);

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

                        //取消原因弹窗

                        final CancelPopupwindow cancelPopupwindow = new CancelPopupwindow(JobOrderDetialActivity.this);
                        if (cancelPopupwindow.isShowing()) {
                            return;
                        }
                        cancelPopupwindow.setOnClickPopListener(new CancelPopupwindow.OnPopupClickListener() {
                            @Override
                            public void onClick(String paytype) {
                                Map<String, String> map = new HashMap<>();
                                map.put("farmerTaskId", farmerTaskId);
                                map.put("cancelReason", paytype);
                                mPresenter.cancelPost(map);


                                cancelPopupwindow.dismiss();
                            }
                        });

                        cancelPopupwindow.showAtLocation(llAll, Gravity.BOTTOM, 0, 0);
                    } else if ("去付款".equals(tvMore.getText().toString())) {
                        //支付
                        showPayMoney();
                    }
                } else if ("driver".equals(flag)) {
                    if ("更多".equals(tvMore.getText().toString())) {
                        showSelectPic(window);
                    }
                }
            }
        });


        //点击支付弹窗
//        tvHadWork.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                showPayMoney();
//
//
//            }
//        });

    }

    /**
     * 弹出支付弹窗
     */
    private void showPayMoney() {

        final PayPopupwindow payPopupwindow = new PayPopupwindow(this);
        if (payPopupwindow.isShowing()) {
            return;
        }
        payPopupwindow.setOnClickPopListener(new PayPopupwindow.OnPopupClickListener() {
            @Override
            public void onClick(String paytype) {
                if ("zhifubao".equals(paytype)) {

                    Map<String, String> map = new HashMap<>();
                    map.put("orderNo", jobOrderDetialBean.getOrderNo());
                    map.put("payPrice", jobOrderDetialBean.getPayPrice());
                    mPresenter.getZhiFuPay(map);


                } else if ("weixin".equals(paytype)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("orderNo", jobOrderDetialBean.getOrderNo());
                    map.put("payPrice", jobOrderDetialBean.getPayPrice());
                    mPresenter.getWeixinPay(map);
                }

                payPopupwindow.dismiss();

            }
        });
        payPopupwindow.showAtLocation(llAll, Gravity.BOTTOM, 0, 0);
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

        llDaiZhiFu = (LinearLayout) findViewById(R.id.ll_daizhifu);
        tvYouhuiJinE = (TextView) findViewById(R.id.tv_youhuijine);
        tvDingDanJinE = (TextView) findViewById(R.id.tv_dingdanjine);
        tvXuFuKuan = (TextView) findViewById(R.id.tv_xufukuan);


        llDaiZhiFu.setVisibility(View.GONE);

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


        llPayMoth = (LinearLayout) findViewById(R.id.ll_pay_moth);
        tvPayType = (TextView) findViewById(R.id.tv_pay_type);
        llPayTime = (LinearLayout) findViewById(R.id.ll_pay_time);
        tvPayTime1 = (TextView) findViewById(R.id.tv_pay_time);


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
        llPayMoth.setVisibility(View.GONE);
        llPayTime.setVisibility(View.GONE);

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


            llDaiZhiFu.setVisibility(View.VISIBLE);
            tvYouhuiJinE.setText(jobOrderDetialBean.getDiscountAmount() + "元");
            tvDingDanJinE.setText(jobOrderDetialBean.getRealTotalprice() + "元");
            tvXuFuKuan.setText(jobOrderDetialBean.getPayPrice() + "元");

            llPayMoth.setVisibility(View.VISIBLE);
            llPayTime.setVisibility(View.VISIBLE);
//                private String payTime;//”:”2018-05-12 18:00:53”,//支付时间
//                private String paymentType;//”:1,//支付方式：1：支付宝 2：微信
            if ("1".equals(jobOrderDetialBean.getPaymentType())) {
                tvPayType.setText("支付宝");
            } else if ("2".equals(jobOrderDetialBean.getPaymentType())) {
                tvPayType.setText("微信");
            }
            tvPayTime1.setText(jobOrderDetialBean.getPayTime());


        } else if ("5".equals(jobOrderDetialBean.getStatus())) {//手机取消

        } else if ("6".equals(jobOrderDetialBean.getStatus())) {//待支付
            tvCancel.setText("待支付");

        }

        tvAddress.setText("• " + jobOrderDetialBean.getProvinceName() + jobOrderDetialBean.getCityName() + jobOrderDetialBean.getAreaName() + "");
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
        llPayMoth.setVisibility(View.GONE);
        llPayTime.setVisibility(View.GONE);

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

                tvHadWork.setVisibility(View.VISIBLE);
                tvHadWork.setText("联系机手");
                tvHadWork.setBackgroundResource(R.drawable.driver_shape_background_green);

                llDaiZhiFu.setVisibility(View.VISIBLE);
                tvYouhuiJinE.setText(jobOrderDetialBean.getDiscountAmount() + "元");
                tvDingDanJinE.setText(jobOrderDetialBean.getRealTotalprice() + "元");
                tvXuFuKuan.setText(jobOrderDetialBean.getPayPrice() + "元");

                llPayMoth.setVisibility(View.VISIBLE);
                llPayTime.setVisibility(View.VISIBLE);
//                private String payTime;//”:”2018-05-12 18:00:53”,//支付时间
//                private String paymentType;//”:1,//支付方式：1：支付宝 2：微信
                if ("1".equals(jobOrderDetialBean.getPaymentType())) {
                    tvPayType.setText("支付宝");
                } else if ("2".equals(jobOrderDetialBean.getPaymentType())) {
                    tvPayType.setText("微信");
                }
                tvPayTime1.setText(jobOrderDetialBean.getPayTime());

            } else if ("5".equals(jobOrderDetialBean.getStatus())) {//手机取消

            } else if ("6".equals(jobOrderDetialBean.getStatus())) {//待支付
                tvCancel.setText("待支付");


                tvMore.setVisibility(View.VISIBLE);
                tvMore.setText("去付款");
                tvMore.setBackgroundResource(R.drawable.driver_shape_background);
                tvHadWork.setVisibility(View.VISIBLE);
                tvHadWork.setText("联系机手");
//                tvHadWork.setBackground();
                tvHadWork.setBackgroundResource(R.drawable.driver_shape_background_green);

//                private String payPrice;//”:”108.5”,//实际付款金额
//                private String payTime;//”:”2018-05-12 18:00:53”,//支付时间
//                private String paymentType;//”:1,//支付方式：1：支付宝 2：微信
//                private String discountAmount;//”:1,//优惠金额
                llDaiZhiFu.setVisibility(View.VISIBLE);

                tvYouhuiJinE.setText(jobOrderDetialBean.getDiscountAmount() + "元");
//                tvDingDanJinE.setText(jobOrderDetialBean.get);
                tvDingDanJinE.setText(jobOrderDetialBean.getRealTotalprice() + "元");
                tvXuFuKuan.setText(jobOrderDetialBean.getPayPrice() + "元");

            }
            tvAddress.setText("• " + jobOrderDetialBean.getProvinceName() + jobOrderDetialBean.getCityName() + jobOrderDetialBean.getAreaName() + "");
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

    @Override
    public void getZhiFuPaySuccess(ZhifubaoBean zhifubaoBean) {
        if (zhifubaoBean != null) {
            //调起支付宝进行支付
            // 获取支付订单
//            Toast.makeText(this, zhifubaoBean.getPayString() + "", Toast.LENGTH_SHORT).show();
            AlipayUtilV2.getInstance(zhifubaoBean.getPayString() + "", new AlipayUtilV2.OnPayResult2() {
                @Override
                public void onPaySuccess() {
//                Toast.makeText(PayActivity.this, "支付宝支付成功！", Toast.LENGTH_LONG).show();
//                        if (onPayNoticeListener != null) {
//                            onPayNoticeListener.queryPayResult(orderId);
//                        }
                    //请求验证接口
//                            httpMakeSure(zhiFuBaoPayBean.getOrderid(), "0");


                }

                @Override
                public void onPayFail(String errorMsg) {

//                            Toast.makeText(PayActivity.this, errorMsg + "", Toast.LENGTH_SHORT).show();

//                        ToastUtils.showShortToastSafe(errorMsg);
//                        needPayAgin();

                }
            }).startPayV2(JobOrderDetialActivity.this);
        }
    }

    @Override
    public void getZhiFuPayFailed() {

    }

    /**
     * 微信支付
     *
     * @param wechatPrePayInfoEntity
     */
    private boolean isWxSuccess = false;

    @Override
    public void getWeixinPaySucccess(WechatPrePayInfoEntity wechatPrePayInfoEntity) {
        isWxSuccess = WxPayUtilV2.startPay(this, wechatPrePayInfoEntity);
    }

    @Override
    public void getWeixinPayFailed() {

    }
}
