package com.rzn.module_main.ui.sellagriculturalgoods;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rzn.module_main.ui.sellagriculturalgoods.commoditylist.CommodityListFragment;

import java.util.List;

/**
 * Created by zz on 2019/5/6.
 */

public class CommodityPageAdapter extends FragmentPagerAdapter {

    List<CommodityListFragment> fragmentList ;
    Context context;

    public CommodityPageAdapter(FragmentManager fm, List<CommodityListFragment> fragmentList) {
        super(fm);
        this.fragmentList =fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


}

