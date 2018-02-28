package com.rzn.module_farmer.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zyhealth.expertlib.utils.MLog;

/**
 * Created by zz on 2018/2/24.
 */
@Route(path = "/farmer/test")
public class TestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.e("+++++++++++");
    }
}
