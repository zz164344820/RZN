package com.rzn.module_main.ui.sellagriculturalgoods;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;
import com.rzn.module_main.R;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.ui.sellagriculturalgoods.commoditylist.CommodityListFragment;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

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
   TagFlowLayout id_flowlayout;
    TextView tv_classify;
    CustomPopWindow mCustomPopWindow;
    View line_gray;

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
        tv_classify=(TextView)findViewById(R.id.tv_classify);
        line_gray=(View)findViewById(R.id.line_gray);
    }


    @Override
    public void setViewPager(List<String> list, ArrayList<CommodityListFragment> fragmentList) {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        viewPager.setAdapter(new CommodityPageAdapter(getSupportFragmentManager(),fragmentList));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommodityAdapter(this,viewPager,list));
        tabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabLayout, viewPager);
        setPopWindow(list, mInflater);
    }



    private void setPopWindow(final List<String> list, final LayoutInflater mInflater) {
        id_flowlayout= (TagFlowLayout)mInflater.inflate(R.layout.goodsclassift_popwindow,null);
        tv_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(SellAgriculturalGoodsActivity.this)
                        .setView(id_flowlayout)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.9f) // 控制亮度
                        .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                        .create()
                        .showAsDropDown(line_gray,0,0);
                id_flowlayout.getAdapter().setSelectedList(viewPager.getCurrentItem());
            }
        });

        id_flowlayout.setAdapter(new TagAdapter<String>(list) {
             @Override
             public View getView(FlowLayout parent, int position, String s)
             {
                 TextView tv = (TextView) mInflater.inflate(R.layout.commodityclassify,
                         id_flowlayout, false);
                 tv.setText(s);
                 return tv;
             }
         });

        id_flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                tabLayout.onPageSelected(position);
                return false;
            }
        });
    }
}
