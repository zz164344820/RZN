package com.rzn.module_main.ui.weather;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.newpwd.NewPasswordActivity;

import java.util.HashMap;
import java.util.Map;



/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WeatherActivity extends MVPBaseActivity<WeatherContract.View, WeatherPresenter> implements WeatherContract.View {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mPresenter.onCreate();
        initViews();
//        setTitle("");

    }

    private void initViews() {

        WebView wb = (WebView) findViewById(R.id.wb);


//        HeWeatherConfig.init("FxDa00r8Jf","");
//        synopticNetworkCustomView = (SynopticNetworkCustomView) findViewById(R.id.synopticNetworkCustomView);
//        /**
//         * 设置控件的对齐方式 默认居中
//         * 详见viewGravity
//         */
//        synopticNetworkCustomView.setViewGravity(HeContent.GRAVITY_CENTER);
//        /**
//         * 设置控件的显示风格 默认横向
//         * 详见viewType
//         */
//        synopticNetworkCustomView.setViewType(HeContent.TYPE_HORIZONTAL);//TYPE_TYPE_HORIZONTAL
//        /**
//         * 设置控件内边距 默认为0
//         * left 左边距
//         * top 上边距
//         * right 右边距
//         * bottom 下边距
//         */
//        synopticNetworkCustomView.setViewPadding(5,5,5,5);
//        /**
//         * 设置控件文字颜色 默认为黑色
//         */
//        synopticNetworkCustomView.setViewTextColor(Color.BLACK);
//
//        //显示控件
//        synopticNetworkCustomView.show();


    }

}
