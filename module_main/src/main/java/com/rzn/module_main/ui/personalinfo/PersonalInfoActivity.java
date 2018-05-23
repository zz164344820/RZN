package com.rzn.module_main.ui.personalinfo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import com.rzn.module_main.R;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalInfoActivity extends MVPBaseActivity<PersonalInfoContract.View, PersonalInfoPresenter> implements PersonalInfoContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_person_center);
        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("个人信息");
    }
}
