package com.rzn.module_main.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RadioButton;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.zhy.autolayout.AutoFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    @BindView(R2.id.fl_content)
    AutoFrameLayout flContent;
    @BindView(R2.id.rb_homepage)
    RadioButton rbHomepage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_main);
        ButterKnife.bind(this);


    }
}
