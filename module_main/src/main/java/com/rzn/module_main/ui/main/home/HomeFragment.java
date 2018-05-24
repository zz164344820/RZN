package com.rzn.module_main.ui.main.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.module_main.R2;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.jobscreening.JobScreeningActivity;
import com.rzn.module_main.ui.keepstation.KeepStationActivity;
import com.rzn.module_main.ui.main.MainActivity;
import com.zyhealth.expertlib.utils.MLog;

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
                SPUtils.getInstance().put("addressName",aMapLocation.getDistrict());
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
        tvMainWeixiuzhan = (TextView) rootView.findViewById(R.id.tv_main_weixiuzhan);
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
        ((MainActivity)getActivity()).setCheckedPager(2,R.id.rb_nongjitong);
    }
}
