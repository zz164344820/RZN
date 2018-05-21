package com.rzn.module_main.ui.main;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.main.farmmachinery.FarmMachineryFragment;
import com.rzn.module_main.ui.main.home.HomeFragment;
import com.rzn.module_main.ui.main.mine.MineFragment;
import com.rzn.module_main.ui.main.shopping.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter{
    List<MVPBaseFragment> fragments =new ArrayList<>();
    ViewPager viewPager;
    @Override
    public void onCreate() {
        initFragment();
        super.onCreate();
       // AddressUtils.CreateDBData(mContext, "address.json");
    }

    private void initFragment() {
        fragments.add(new HomeFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new FarmMachineryFragment());
        fragments.add(new MineFragment());
    }


    @Override
    public void initViewPager(ViewPager viewPager) {
        this.viewPager =viewPager;
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
                if(i== R.id.rb_homepage){
                    viewPager.setCurrentItem(0);
                }else if(i== R.id.rb_shop){
                    viewPager.setCurrentItem(1);
                }else if(i== R.id.rb_nongjitong){
                    viewPager.setCurrentItem(2);
                }else if(i== R.id.rb_mine){
                    viewPager.setCurrentItem(3);
                }
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
