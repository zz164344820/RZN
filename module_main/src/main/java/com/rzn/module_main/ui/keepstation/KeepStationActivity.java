package com.rzn.module_main.ui.keepstation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.keepstationdetial.KeepStationDetialActivity;
import com.zyhealth.expertlib.utils.MLog;

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


    double LATITUDE_B = 32.335756;  //终点纬度
    double LONGTITUDE_B = 118.88462;  //终点经度
    private EditText ed_text_search;
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private String longitude = "";
    private String latitude = "";
    private KeepStationAdapter keepStationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_maintenance_station);
        initViews();
        initLinstener();
        initLocation();
        mPresenter.onCreate();

    }

    private void initLocation() {

        //声明AMapLocationClient类对象
        mLocationClient = new AMapLocationClient(KeepStationActivity.this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //单次定位
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getLongitude() != 0.0) {
//                    tvMainAddress.setText(aMapLocation.getDistrict());
//                    SPUtils.getInstance().put("addressName", aMapLocation.getDistrict());

//                    getWeater(aMapLocation.getLongitude() + "," + aMapLocation.getLatitude());
                    longitude = aMapLocation.getLongitude() + "";
                    latitude = aMapLocation.getLatitude() + "";
                    mPresenter.getKeepData("", longitude, latitude);

                }
            }
        });
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private void initLinstener() {
        ed_text_search.setOnKeyListener(onKey);
    }

    private void initViews() {
        ed_text_search = (EditText) findViewById(R.id.ed_text_search);
        setTitle("维修站");
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
//        mPresenter.getKeepData("", longitude, latitude);
    }

    @Override
    public void complete_enter() {
        // super.complete_enter();
        mPresenter.getKeepData(ed_text_search.getText().toString().trim(), longitude, latitude);
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
            Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=appname&poiname=fangheng&lat=" + LATITUDE_B + "&lon=" + LONGTITUDE_B + "&dev=1&style=2");

//            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&sname=我的位置&dlat=" + LATITUDE_B + "&dlon=" + LONGTITUDE_B + "&dname=" + address + "&dev=0&m=0&t=1");
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

    @Override
    public void getKeepDataSuccess(final List<KeepStationBean> list) {
        keepStationAdapter = new KeepStationAdapter(R.layout.item_maintenance_station, list);
        swipeTarget.setAdapter(keepStationAdapter);
        keepStationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LATITUDE_B = Double.valueOf(list.get(position).getLatitude());//32.335756;  //终点纬度
                LONGTITUDE_B = Double.valueOf(list.get(position).getLongitude());//118.88462;  //终点经度
                setUpGaodeAppByMine();
            }
        });
        keepStationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(KeepStationActivity.this, KeepStationDetialActivity.class);
                intent.putExtra("repairId", list.get(position).getRepairId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getKeepDataFailed() {

    }


}
