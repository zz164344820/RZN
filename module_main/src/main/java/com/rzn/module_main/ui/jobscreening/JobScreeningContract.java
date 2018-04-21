package com.rzn.module_main.ui.jobscreening;

import android.widget.RadioGroup;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;


public class JobScreeningContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
    }
}
