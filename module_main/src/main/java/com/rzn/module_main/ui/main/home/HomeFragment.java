package com.rzn.module_main.ui.main.home;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.jobscreening.JobScreeningActivity;
import com.rzn.module_main.ui.keepstation.KeepStationActivity;
import com.rzn.module_main.ui.main.MainActivity;
import com.rzn.module_main.ui.mesagecenter.MessageCenterActivity;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View {
    View rootView;
    @BindView(R2.id.tv_main_address)
    TextView tvMainAddress;
    Unbinder unbinder;
    @BindView(R2.id.ultra_viewpager)
    UltraViewPager ultraViewPager;
    private TextView tvMainWeixiuzhan;
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initViews();
        initListener();
        initLocation();
        return rootView;
    }

    private void initLocation() {
        //声明AMapLocationClient类对象
        mLocationClient = new AMapLocationClient(getActivity());
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //单次定位
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                tvMainAddress.setText(aMapLocation.getDistrict());
                SPUtils.getInstance().put("addressName", aMapLocation.getDistrict());
            }
        });
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private void initListener() {
        tvMainWeixiuzhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, KeepStationActivity.class));
            }
        });
    }

    private void initViews() {
        List<String> list = new ArrayList<>();
        list.add("http://p2.so.qhimgs1.com/bdr/_240_/t01d2878c11069776be.jpg");
        list.add("http://p1.so.qhimgs1.com/bdr/_240_/t012f38ae2366358121.jpg");
        list.add("http://p1.so.qhimgs1.com/bdr/_240_/t01278b9b22af792ca1.jpg");
        list.add("http://p1.so.qhimgs1.com/bdr/_240_/t01b610395eca9633a1.jpg");
        list.add("http://p3.so.qhimgs1.com/bdr/_240_/t01db972ee913fefa23.jpg");
        tvMainWeixiuzhan = (TextView) rootView.findViewById(R.id.tv_main_weixiuzhan);


        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //UltraPagerAdapter 绑定子view到UltraViewPager
        PagerAdapter adapter = new BannerPagerAdapter(getActivity(),list);
        ultraViewPager.setAdapter(adapter);
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
        ultraViewPager.getIndicator().setMargin(0,0,0, SizeUtils.dp2px(10));
        //构造indicator,绑定到UltraViewPager
        ultraViewPager.getIndicator().build();
        //设定页面循环播放
        ultraViewPager.setInfiniteLoop(true);
        //设定页面自动切换  间隔2秒
        ultraViewPager.setAutoScroll(2000);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R2.id.tv_main_address)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), JobScreeningActivity.class));
    }

    @OnClick(R2.id.tv_main_nongjitong)
    public void tv_main_nongjitong() {
        startActivity(new Intent(getActivity(), JobScreeningActivity.class));
    }

    @OnClick(R2.id.iv_message)
    public void iv_message() {
        startActivity(new Intent(getActivity(), MessageCenterActivity.class));
    }


}
