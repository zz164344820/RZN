package com.rzn.module_main.ui.keepstation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KeepStationActivity extends MVPBaseActivity<KeepStationContract.View, KeepStationPresenter> implements KeepStationContract.View {

    private RecyclerView swipeTarget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_maintenance_station);
        initViews();
        mPresenter.onCreate();
//        showLoading(false,"");
    }

    private void initViews() {
        setTitle("维修站");
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        KeepStationAdapter keepStationAdapter = new KeepStationAdapter(R.layout.item_maintenance_station, s);
        swipeTarget.setAdapter(keepStationAdapter);
    }
}
