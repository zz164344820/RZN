package com.rzn.module_main.ui.main;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.ui.main.home.HomeFragment;

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
        initFragment();
        super.onCreate();
    }

    private void initFragment() {
        fragments.add(new HomeFragment());
    }


    @Override
    public void initViewPager(ViewPager viewPager) {
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setAdapter(new MainViewpagerAdapter(mContext.getSupportFragmentManager(),fragments));
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void initRadioGroup(RadioGroup rg) {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
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
