package com.rzn.module_main.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.views.AutoRadioGroup;
import com.rzn.commonbaselib.views.NosrollViewPager;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.zyhealth.expertlib.utils.MLog;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {


    @BindView(R2.id.rb_homepage)
    RadioButton rbHomepage;
    @BindView(R2.id.rg_bottom)
    AutoRadioGroup rgBottom;
    @BindView(R2.id.viewpager)
    NosrollViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_main);
        ButterKnife.bind(this);
        mPresenter.onCreate();
    }


    @Override
    public void initView() {
        super.initView();
        rbHomepage.setChecked(true);
        viewpager.setCurrentItem(0);
        mPresenter.initViewPager(viewpager);
        mPresenter.initRadioGroup(rgBottom);

    }






}
