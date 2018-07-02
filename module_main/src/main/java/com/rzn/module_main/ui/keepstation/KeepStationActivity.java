package com.rzn.module_main.ui.keepstation;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KeepStationActivity extends MVPBaseActivity<KeepStationContract.View, KeepStationPresenter> implements KeepStationContract.View {

    private RecyclerView swipeTarget;

//    118.88462,32.335756

    private static final double LATITUDE_A = 28.1903;  //起点纬度
    private static final double LONGTITUDE_A = 113.031738;  //起点经度

    double LATITUDE_B = 32.335756;  //终点纬度
    double LONGTITUDE_B = 118.88462;  //终点经度
    private String address = "南京冠世机械设备有限公司农机维修站";
    private TextView tvTextSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_maintenance_station);
        initViews();
        initLinstener();
        mPresenter.onCreate();

//        showLoading(false,"");
    }

    private void initLinstener() {
        tvTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(this,"暂未开通该功能",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "暂未开通该功能", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initViews() {
        tvTextSearch = (TextView) findViewById(R.id.tv_text_search);
        setTitle("维修站");
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        List<String> s = new ArrayList<>();
        s.add("0");
        s.add("1");
        s.add("2");
        KeepStationAdapter keepStationAdapter = new KeepStationAdapter(R.layout.item_maintenance_station, s);
        swipeTarget.setAdapter(keepStationAdapter);
        keepStationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (0 == position) {
                    LATITUDE_B = 32.335756;  //终点纬度
                    LONGTITUDE_B = 118.88462;  //终点经度
                    address = "南京冠世机械设备有限公司农机维修站";
                } else if (1 == position) {
//                    118.773717,31.867643
                    LATITUDE_B = 31.867643;  //终点纬度
                    LONGTITUDE_B = 118.773717;  //终点经度
                    address = "农机配件";
                } else if (2 == position) {
                    LATITUDE_B = 32.335756;  //终点纬度
                    LONGTITUDE_B = 118.88462;  //终点经度
                    address = "南京冠世机械设备有限公司农机维修站-北门";
                }
                setUpGaodeAppByMine();
            }
        });
    }


    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 我的位置BY高德
     */
    void setUpGaodeAppByMine() {
        try {
            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&sname=我的位置&dlat=" + LATITUDE_B + "&dlon=" + LONGTITUDE_B + "&dname=" + address + "&dev=0&m=0&t=1");
            if (isInstallByread("com.autonavi.minimap")) {
                startActivity(intent);
//                Log.e(TAG, "高德地图客户端已经安装") ;
            } else {
                Toast.makeText(this, "请先安装高德地图App", Toast.LENGTH_LONG).show();
//                Log.e(TAG, "没有安装高德地图客户端") ;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
