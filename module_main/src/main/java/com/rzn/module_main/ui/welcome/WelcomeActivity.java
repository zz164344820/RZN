package com.rzn.module_main.ui.welcome;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.main.MainActivity;
import com.rzn.module_main.ui.main.home.HeWeather6;
import com.rzn.module_main.ui.main.home.WeaterList;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyhealth.expertlib.utils.MLog;

import okhttp3.Call;


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

    private void getWeater(String location) {
        String url = "https://free-api.heweather.com/s6/weather?";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("location", location)
                .addParams("key", "2eb9b628139a435684719fab15d1ebff")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MLog.e(response);
                        Gson gson = new Gson();
                        WeaterList weaterList=  gson.fromJson(response,new TypeToken<WeaterList>(){}.getType());
                        HeWeather6 heWeather6 =  weaterList.getHeWeather6().get(0);
                        //List<HeWeather6.Daily_forecast>  daily_forecast = heWeather6.getDaily_forecast();
                        FileSaveUtils.fileSaveObject(heWeather6,"weater");

                    }
                });
    }
}
