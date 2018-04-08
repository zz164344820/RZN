package com.rzn.module_main.ui.main;

import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
       void initViewPager(ViewPager  viewPager);
       void initRadioGroup(RadioGroup rg);

    }
}
