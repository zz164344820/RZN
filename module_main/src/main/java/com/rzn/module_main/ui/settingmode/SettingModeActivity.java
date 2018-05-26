package com.rzn.module_main.ui.settingmode;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SettingModeActivity extends MVPBaseActivity<SettingModeContract.View, SettingModePresenter> implements SettingModeContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_setting_mode);
        TextView tvMessage = (TextView) findViewById(R.id.tv_message);
        tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/driver/driverident").withString("setting","setting").navigation();
            }
        });
        mPresenter.onCreate();
//        showLoading(false,"");
    }
}
