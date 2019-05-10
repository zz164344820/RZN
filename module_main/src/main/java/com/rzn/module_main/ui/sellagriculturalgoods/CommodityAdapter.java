package com.rzn.module_main.ui.sellagriculturalgoods;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2019/5/6.
 */

public class CommodityAdapter extends CommonNavigatorAdapter {

    List<String> list;
    Context context;
    ViewPager viewPager;

    public CommodityAdapter(Context context, ViewPager viewPager,List<String> list) {
        super();
        this.context =context;
        this.viewPager =viewPager;
        this.list =list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
        simplePagerTitleView.setText(list.get(index));
        simplePagerTitleView.setTextSize(18);
        simplePagerTitleView.setNormalColor(Color.parseColor("#333333"));
        simplePagerTitleView.setSelectedColor(Color.parseColor("#70C63F"));
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(index);
            }
        });

        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        indicator.setColors(Color.parseColor("#70C63F"));
        return  indicator;
    }
}

