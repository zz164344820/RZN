package com.rzn.module_driver.ui.jobOrder.myjoborder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.jobOrder.allorder.AllOrderFragment;
import com.rzn.module_driver.ui.jobOrder.await_job.Await_jobFragment;
import com.rzn.module_driver.ui.jobOrder.havefinished.HaveFinishedFragment;
import com.zhy.autolayout.AutoFrameLayout;


/**
 * 我的作业订单类
 * <p>
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyjobOrderActivity extends MVPBaseActivity<MyjobOrderContract.View, MyjobOrderPresenter> implements MyjobOrderContract.View {


    private AutoFrameLayout rlContent;
    MVPBaseFragment haveFinishedFragment;
    MVPBaseFragment awaitJobFragment;
    MVPBaseFragment allOrderFragment;
    MVPBaseFragment currentFragment;
    private TextView tvAll;
    private TextView tvWork;
    private TextView tvFinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.act_myjob_order);

        //初始化布局
        initViews();
        //初始化fragment
        initFragment();
        initListener();


    }




    private void initViews() {
        rlContent = (AutoFrameLayout) findViewById(R.id.rl_content);
        tvAll = (TextView) findViewById(R.id.tv_all);
        tvWork = (TextView) findViewById(R.id.tv_work);
        tvFinish = (TextView) findViewById(R.id.tv_finish);

    }
    private void initListener() {

        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchContentFragment(allOrderFragment);
            }
        });
        tvWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchContentFragment(awaitJobFragment);
            }
        });
        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchContentFragment(haveFinishedFragment);
            }
        });

    }
    private void initFragment() {

//        MVPBaseFragment fragment=    com.rzn.commonbaselib.applictaion.ViewManager.getInstance().getFragment(0);

        haveFinishedFragment = new HaveFinishedFragment();
        awaitJobFragment = new Await_jobFragment();
        allOrderFragment = new AllOrderFragment();

        switchContentFragment(allOrderFragment);
    }

    private void switchContentFragment(MVPBaseFragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment != fragment) {
            transaction.hide(fragment);
        }
        if (fragment.isAdded()) {
            transaction.show(fragment).commit();
        } else {
            transaction.add(R.id.rl_content, fragment).commit();
        }
        currentFragment = fragment;

    }
}
