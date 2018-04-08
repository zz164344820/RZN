package com.rzn.module_main.ui.jobscreening;

import android.support.annotation.IdRes;
import android.widget.RadioGroup;

import com.rzn.commonbaselib.mvp.BasePresenterImpl;



public class JobScreeningPresenter extends BasePresenterImpl<JobScreeningContract.View> implements JobScreeningContract.Presenter{

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void initRadioGroup(RadioGroup radioGroup) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case 0:
                        //显示农户作业
                        break;
                    case 1:
                        //显示机手作业
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
