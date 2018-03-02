package com.rzn.module_main.ui.main;

import android.support.v4.view.ViewPager;

import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter{
    List<MVPBaseFragment> fragments =new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        initFragment();
    }

    private void initFragment() {
    }


    @Override
    public void initViewPager(ViewPager viewPager) {
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
       // viewPager.setAdapter();
    }


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
         //当页面切换到某一页的时候，在加载数据
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
