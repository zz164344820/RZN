package com.rzn.module_main.ui.sellagriculturalgoods;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rzn.module_main.ui.main.MainActivity;

import java.util.ArrayList;
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

