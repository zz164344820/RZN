package com.rzn.module_main.ui.sellagriculturalgoods;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.rzn.module_main.R;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SellAgriculturalGoodsActivity extends MVPBaseActivity<SellAgriculturalGoodsContract.View, SellAgriculturalGoodsPresenter> implements SellAgriculturalGoodsContract.View {
    MagicIndicator tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sellagriculturegoods);
        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("卖农货");
        tabLayout=(MagicIndicator)findViewById(R.id.viewpagertab);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
    }


    @Override
    public void setViewPager(List<String> list, ArrayList<CommodityListFragment> fragmentList) {
        viewPager.setAdapter(new CommodityPageAdapter(getSupportFragmentManager(),fragmentList));
        //String[] array1 = list.toArray(new String[list.size()]);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommodityAdapter(this,viewPager,list));
        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, viewPager);
    }
}
