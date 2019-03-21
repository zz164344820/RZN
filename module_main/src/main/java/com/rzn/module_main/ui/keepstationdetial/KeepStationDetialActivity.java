package com.rzn.module_main.ui.keepstationdetial;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SizeUtils;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.keepstationdetial.bean.StationDetialBean;
import com.tmall.ultraviewpager.UltraViewPager;
import com.zyhealth.expertlib.utils.GlideUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KeepStationDetialActivity extends MVPBaseActivity<KeepStationDetialContract.View, KeepStationDetialPresenter> implements KeepStationDetialContract.View {
    /**
     * 建委维修厂
     */
    private TextView tvStationName;
    /**
     * 维修范围：农用机械、各类轿车、吊车、各类大客车，农用机械、各类轿车、吊车、各...
     */
    private TextView tvJingyingFanwei;
    /**
     * 商家地址：清河镇武门村口西行50m路东
     */
    private TextView tvShagnjiaDizhi;
    /**
     * 营业时间：9:00-10:00
     */
    private TextView tvYingyeshijian;
    /**
     * 联系方式：清河镇武门村口西行50m路东
     */
    private TextView tvLianxiFangshi;
    /**
     * 联系商家
     */
    private TextView tvLianxi;
    private String repairId;
    private ImageView ivPhoto;
    private UltraViewPager ultraViewPager;
    private ImageView ivQuzheli;
    String LATITUDE_B = "";//32.335756;  //终点纬度
    String LONGTITUDE_B = "";//118.88462;  //终点经度
    String phone = "";
    private TextView tvPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_staticon_detial);
        initViews();
        mPresenter.onCreate();
        initData();
        initListener();
//        showLoading(false,"");
    }

    private void initData() {
//        repair/repairInfo
        Map<String, String> map = new HashMap<>();
        map.put("repairId", repairId);
        mPresenter.getDetialData(map);

    }

    private void initListener() {
        tvLianxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(phone)) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + phone);
                    intent.setData(data);
                    startActivity(intent);
                } else {
                    Toast.makeText(KeepStationDetialActivity.this, "暂无联系电话", Toast.LENGTH_LONG).show();
                }
            }
        });

        ivQuzheli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpGaodeAppByMine();
            }
        });
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

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    private void initViews() {


        repairId = getIntent().getStringExtra("repairId");
        setTitle("商家照片");
        ivQuzheli = findViewById(R.id.iv_quzheli);
        tvStationName = (TextView) findViewById(R.id.tv_station_name);
        tvJingyingFanwei = (TextView) findViewById(R.id.tv_jingying_fanwei);
        tvShagnjiaDizhi = (TextView) findViewById(R.id.tv_shagnjia_dizhi);
        tvYingyeshijian = (TextView) findViewById(R.id.tv_yingyeshijian);
        tvLianxiFangshi = (TextView) findViewById(R.id.tv_lianxi_fangshi);
        tvLianxi = (TextView) findViewById(R.id.tv_lianxi);
        tvPhone = findViewById(R.id.tv_phone);
        ivPhoto = findViewById(R.id.iv_photo);

        ultraViewPager = findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);


    }


    @Override
    public void getDetialDataSuccess(StationDetialBean stationDetialBean) {

        if (stationDetialBean != null) {
            showView(stationDetialBean);
        }
    }

    private void showView(StationDetialBean stationDetialBean) {


//        private String businessAddress;//": "安徽省淮南市田家庵区百花园路1032", //商家地址
//        private String createTime;//":"2018-07-03 22:41:22", //创建时间
//        private String factoryName;//":"君宝修理厂2", //维修站名称
//        private String factoryPic1;//":"", //维修站图片
//        private String homeTel;//":"", //家庭电话
//        private String latitude;//":"98.32835", //维度
//        private String linkman;//":"胡君宝2", //联系人名称
//        private String longitude;//":"103.124235", //经度
//        private String phone;//":"18063005664", //联系人手机
//        private String repairId;//":"1234567891234567890987654121", //维修站id
//        private String repairScope;//":"农用机械、各类轿车、吊车、各类大客车，农用机械、各类轿车、吊车、各类大电子设备", //维修范围
//        private String tel;//":"0554-4444444", //座机
//        private String workTimeEnd;//":"2017-06-18", //营业结束时间
//        private String workTimeStart;//":"2017-06-17" //营业开始时间
        phone = stationDetialBean.getTel();
        LATITUDE_B = stationDetialBean.getLatitude();
        LONGTITUDE_B = stationDetialBean.getLongitude();

        tvStationName.setText(stationDetialBean.getFactoryName());
        tvJingyingFanwei.setText("经营范围：" + stationDetialBean.getRepairScope());
        tvShagnjiaDizhi.setText("商家地址：" + stationDetialBean.getBusinessAddress());
        tvYingyeshijian.setText("营业时间：" + stationDetialBean.getWorkTimeStart() + "-" + stationDetialBean.getWorkTimeEnd());
        tvLianxiFangshi.setText(stationDetialBean.getTel() + "");
        tvPhone.setText(stationDetialBean.getPhone() + "");
//        GlideUtils.loadImageView(this, stationDetialBean.getFactoryPic1(), ivPhoto);
//        List<String> list = new ArrayList<>();
//        if (!TextUtils.isEmpty(stationDetialBean.getFactoryPic1())) {
//            list.add(stationDetialBean.getFactoryPic1());
//        }
//        if (!TextUtils.isEmpty(stationDetialBean.getFactoryPic2())) {
//            list.add(stationDetialBean.getFactoryPic2());
//        }
//        if (!TextUtils.isEmpty(stationDetialBean.getFactoryPic3())) {
//            list.add(stationDetialBean.getFactoryPic3());
//        }
//        if (!TextUtils.isEmpty(stationDetialBean.getFactoryPic4())) {
//            list.add(stationDetialBean.getFactoryPic4());
//        }
//        if (!TextUtils.isEmpty(stationDetialBean.getFactoryPic5())) {
//            list.add(stationDetialBean.getFactoryPic5());
//        }
//        if (!TextUtils.isEmpty(stationDetialBean.getFactoryPic6())) {
//            list.add(stationDetialBean.getFactoryPic6());
//        }

        if (stationDetialBean.getFactoryPicList() == null) {
            return;
        }

        KeepViewPagerAdapter keepViewPagerAdapter = new KeepViewPagerAdapter(stationDetialBean.getFactoryPicList(), this);
        ultraViewPager.setAdapter(keepViewPagerAdapter);
        //内置indicator初始化
        ultraViewPager.initIndicator();
        //设置indicator样式
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.RED)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()));
        //设置indicator对齐方式
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        ultraViewPager.getIndicator().setMargin(0, 0, 0, SizeUtils.dp2px(10));
        //构造indicator,绑定到UltraViewPager
        ultraViewPager.getIndicator().build();
        //设定页面循环播放
        ultraViewPager.setInfiniteLoop(true);
        //设定页面自动切换  间隔2秒
        ultraViewPager.setAutoScroll(4000);

    }

    @Override
    public void getDetialDataFailed() {

    }
}
