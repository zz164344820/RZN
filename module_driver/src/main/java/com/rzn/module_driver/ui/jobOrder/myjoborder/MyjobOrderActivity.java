package com.rzn.module_driver.ui.jobOrder.myjoborder;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.jobOrder.allorder.AllOrderFragment;
import com.rzn.module_driver.ui.jobOrder.await_job.Await_jobFragment;
import com.rzn.module_driver.ui.jobOrder.havefinished.HaveFinishedFragment;
import com.rzn.module_driver.ui.jobOrder.myjoborder.wait_pay.Await_PayFragment;
import com.zhy.autolayout.AutoFrameLayout;

import mlxy.utils.T;


/**
 * 我的作业订单类
 * <p>
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = "/driver/myjobdetial")
public class MyjobOrderActivity extends MVPBaseActivity<MyjobOrderContract.View, MyjobOrderPresenter> implements MyjobOrderContract.View {


    private AutoFrameLayout rlContent;
    MVPBaseFragment haveFinishedFragment;
    MVPBaseFragment awaitJobFragment;
    MVPBaseFragment allOrderFragment;
    MVPBaseFragment currentFragment;
    private TextView tvAll;
    private TextView tvWork;
    private TextView tvFinish;
    private RadioButton rbRamer;
    private RadioButton rbDriver;
    String flag = "farmer";
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private View viewOne;
    private View viewTwo;
    private View viewThree;
    private View viewFour;
    private TextView tvPay;
    private RelativeLayout rlDai;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.act_myjob_order);

        //初始化布局
        initViews();
        //初始化数据
        initData();
        //初始化fragment
//        initFragment();
        initListener();


    }

    private void initData() {

    }


    private void initViews() {
        setTitle("我的作业订单");

        rlDai = (RelativeLayout) findViewById(R.id.rl_dai);
        rlDai.setVisibility(View.VISIBLE);
        rlContent = (AutoFrameLayout) findViewById(R.id.rl_content);
        tvAll = (TextView) findViewById(R.id.tv_all);
        tvWork = (TextView) findViewById(R.id.tv_work);
        tvFinish = (TextView) findViewById(R.id.tv_finish);
        tvPay = (TextView) findViewById(R.id.tv_pay);

        rbRamer = (RadioButton) findViewById(R.id.rb_rarmer);
        rbDriver = (RadioButton) findViewById(R.id.rb_driver);

        tvAll.setTextColor(Color.parseColor("#fb9300"));
        tvFinish.setTextColor(Color.parseColor("#333333"));
        tvWork.setTextColor(Color.parseColor("#333333"));

        viewOne = findViewById(R.id.view_one);
        viewTwo = findViewById(R.id.view_two);
        viewThree = findViewById(R.id.view_three);
        viewFour = findViewById(R.id.view_four);

        viewOne.setVisibility(View.VISIBLE);
        viewTwo.setVisibility(View.GONE);
        viewThree.setVisibility(View.GONE);
        viewFour.setVisibility(View.GONE);


        tvFinish.setText("待接单");
        String type = getIntent().getStringExtra("type");
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        if ("1".equals(type)) {
            flag = "driver";
            tvFinish.setText("已完成");
            viewOne.setVisibility(View.VISIBLE);
            viewTwo.setVisibility(View.GONE);
            viewThree.setVisibility(View.GONE);
            viewFour.setVisibility(View.GONE);

            tvAll.setTextColor(Color.parseColor("#fb9300"));
            tvFinish.setTextColor(Color.parseColor("#333333"));
            tvWork.setTextColor(Color.parseColor("#333333"));
            tvPay.setTextColor(Color.parseColor("#333333"));

            transaction.replace(R.id.rl_content, new AllOrderFragment());
            transaction.commit();
        } else {
            transaction.add(R.id.rl_content, new AllOrderFragment());
            transaction.commit();
        }
    }

    private void initListener() {

        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewOne.setVisibility(View.VISIBLE);
                viewTwo.setVisibility(View.GONE);
                viewThree.setVisibility(View.GONE);
                viewFour.setVisibility(View.GONE);

                tvAll.setTextColor(Color.parseColor("#fb9300"));
                tvFinish.setTextColor(Color.parseColor("#333333"));
                tvWork.setTextColor(Color.parseColor("#333333"));
                tvPay.setTextColor(Color.parseColor("#333333"));
//                switchContentFragment(allOrderFragment);
                transaction = manager.beginTransaction();
                transaction.replace(R.id.rl_content, new AllOrderFragment());
                transaction.commit();

            }
        });
        tvWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewOne.setVisibility(View.GONE);
                viewTwo.setVisibility(View.VISIBLE);
                viewThree.setVisibility(View.GONE);
                viewFour.setVisibility(View.GONE);
                tvAll.setTextColor(Color.parseColor("#333333"));
                tvFinish.setTextColor(Color.parseColor("#333333"));
                tvWork.setTextColor(Color.parseColor("#fb9300"));
                tvPay.setTextColor(Color.parseColor("#333333"));
                transaction = manager.beginTransaction();

                transaction.replace(R.id.rl_content, new Await_jobFragment());
                transaction.commit();
//                switchContentFragment(awaitJobFragment);
            }
        });
        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewOne.setVisibility(View.GONE);
                viewTwo.setVisibility(View.GONE);
                viewThree.setVisibility(View.VISIBLE);
                viewFour.setVisibility(View.GONE);
                tvAll.setTextColor(Color.parseColor("#333333"));
                tvFinish.setTextColor(Color.parseColor("#fb9300"));
                tvWork.setTextColor(Color.parseColor("#333333"));
                tvPay.setTextColor(Color.parseColor("#333333"));
                transaction = manager.beginTransaction();

                transaction.replace(R.id.rl_content, new HaveFinishedFragment());
                transaction.commit();
//                switchContentFragment(haveFinishedFragment);
            }
        });


        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewOne.setVisibility(View.GONE);
                viewTwo.setVisibility(View.GONE);
                viewThree.setVisibility(View.GONE);
                viewFour.setVisibility(View.VISIBLE);
                tvAll.setTextColor(Color.parseColor("#333333"));
                tvFinish.setTextColor(Color.parseColor("#333333"));
                tvWork.setTextColor(Color.parseColor("#333333"));
                tvPay.setTextColor(Color.parseColor("#fb9300"));
                transaction = manager.beginTransaction();

                transaction.replace(R.id.rl_content, new Await_PayFragment());
                transaction.commit();
            }
        });


        rbDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = "driver";
                tvFinish.setText("已完成");
                viewOne.setVisibility(View.VISIBLE);
                viewTwo.setVisibility(View.GONE);
                viewThree.setVisibility(View.GONE);
                rlDai.setVisibility(View.GONE);
                tvAll.setTextColor(Color.parseColor("#fb9300"));
                tvFinish.setTextColor(Color.parseColor("#333333"));
                tvWork.setTextColor(Color.parseColor("#333333"));
//                switchContentFragment(allOrderFragment);
                transaction = manager.beginTransaction();

                transaction.replace(R.id.rl_content, new AllOrderFragment());
                transaction.commit();
            }
        });

        rbRamer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = "farmer";
                tvFinish.setText("待接单");
                viewOne.setVisibility(View.VISIBLE);
                viewTwo.setVisibility(View.GONE);
                viewThree.setVisibility(View.GONE);
                rlDai.setVisibility(View.VISIBLE);
                tvAll.setTextColor(Color.parseColor("#fb9300"));
                tvFinish.setTextColor(Color.parseColor("#333333"));
                tvWork.setTextColor(Color.parseColor("#333333"));
                transaction = manager.beginTransaction();

                transaction.replace(R.id.rl_content, new AllOrderFragment());
                transaction.commit();
//                switchContentFragment(allOrderFragment);

            }
        });


    }

    public String getLabel() {
        return flag;
    }


//    private void initFragment() {
//
////        MVPBaseFragment fragment=    com.rzn.commonbaselib.applictaion.ViewManager.getInstance().getFragment(0);
//
//        haveFinishedFragment = new HaveFinishedFragment();
//        awaitJobFragment = new Await_PayFragment();
//        allOrderFragment = new AllOrderFragment();
//
//        switchContentFragment(allOrderFragment);
//    }

//    private void switchContentFragment(MVPBaseFragment fragment) {
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        if (currentFragment != fragment) {
//            if (currentFragment != null) {
//                transaction.hide(fragment);
//            }
//            if (fragment.isAdded()) {
//                transaction.show(fragment).commit();
//            } else {
//                transaction.add(R.id.rl_content, fragment).commit();
//            }
//            currentFragment = fragment;
//        }
//    }
}
