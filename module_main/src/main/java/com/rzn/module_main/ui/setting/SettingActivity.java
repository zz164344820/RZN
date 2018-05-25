package com.rzn.module_main.ui.setting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.tencent.bugly.beta.Beta;




/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingActivity extends MVPBaseActivity<SettingContract.View, SettingPresenter> implements SettingContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting);
        initViews();
        mPresenter.onCreate();
//        showLoading(false,"");
    }

    private void initViews() {
        setTitle("设置");
    }

    @Override
    public void initView() {
        super.initView();
        findViewById(R.id.tv_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beta.checkUpgrade(true,false);
            }
        });
    }
}
