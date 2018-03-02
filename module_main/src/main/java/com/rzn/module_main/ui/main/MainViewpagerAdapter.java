package com.rzn.module_main.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;

import java.util.List;

/**
 * Created by zz on 2018/3/2.
 */

public class MainViewpagerAdapter extends FragmentPagerAdapter {
    List<MVPBaseFragment> list;

    public MainViewpagerAdapter(FragmentManager fm, List<MVPBaseFragment> list) {
        super(fm);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        MVPBaseFragment  fragment = fragment=list.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
