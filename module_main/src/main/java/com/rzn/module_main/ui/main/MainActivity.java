package com.rzn.module_main.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.Toast;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.views.AutoRadioGroup;
import com.rzn.commonbaselib.views.NosrollViewPager;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.login.LoginActivity;
import com.zyhealth.expertlib.LibApplication;
import com.zyhealth.expertlib.utils.GlideUtils;
import com.zyhealth.expertlib.utils.MLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import chihane.jdaddressselector.AddressUtils;
import chihane.jdaddressselector.BottomDialog;


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
    private long lastTime=0; //记录上次点击的时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_main);
        ButterKnife.bind(this);
        mPresenter.onCreate();
        AddressUtils.CreateDBData(this, "address.json");
    }


    @Override
    public void initView() {
        super.initView();
        rbHomepage.setChecked(true);
        viewpager.setCurrentItem(0);
        mPresenter.initViewPager(viewpager);
        mPresenter.initRadioGroup(rgBottom);
    }

    public void setCheckedPager(int checkIndex ,int viewId){
        viewpager.setCurrentItem(checkIndex);
        rgBottom.check(viewId);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-lastTime)>2000){
                Toast.makeText(MainActivity.this, "在按一次退出程序", Toast.LENGTH_SHORT).show();
                lastTime=System.currentTimeMillis();
            }else {
                for (int i = 0, size = LibApplication.mActivityStack.size(); i < size; i++) {
                    LibApplication.mActivityStack.get(i).finish();
                }
            }
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
