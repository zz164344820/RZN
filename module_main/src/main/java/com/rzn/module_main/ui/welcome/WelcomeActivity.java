package com.rzn.module_main.ui.welcome;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.main.MainActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WelcomeActivity extends MVPBaseActivity<WelcomeContract.View, WelcomePresenter> implements WelcomeContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.act_welcome);
        initData();
        mPresenter.onCreate();
//        showLoading(false,"");
    }

    private void initData() {
        new CountDownTimer(2 * 1000, 1000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //倒计时结束回调
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}
