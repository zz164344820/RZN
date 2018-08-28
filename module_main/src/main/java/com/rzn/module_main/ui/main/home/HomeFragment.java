package com.rzn.module_main.ui.main.home;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.listener.NoDoubleClickListener;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.jobscreening.JobScreeningActivity;
import com.rzn.module_main.ui.keepstation.KeepStationActivity;
import com.rzn.module_main.ui.login.LoginActivity;
import com.rzn.module_main.ui.main.MainActivity;
import com.rzn.module_main.ui.main.farmmachinery.InfoBean;
import com.rzn.module_main.ui.mesagecenter.MessageCenterActivity;
import com.rzn.module_main.ui.mesagecenter.MessageInfo;
import com.rzn.module_main.ui.sellagriculturalgoods.SellAgriculturalGoodsActivity;
import com.rzn.module_main.ui.util.LoginUtil;
import com.rzn.module_main.ui.webview.WebViewActivity;
import com.tmall.ultraviewpager.UltraViewPager;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.net.GenericsCallback;
import com.zyhealth.expertlib.net.JsonGenericsSerializator;
import com.zyhealth.expertlib.utils.GlideUtils;
import com.zyhealth.expertlib.utils.MLog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;
import mlxy.utils.T;
import okhttp3.Call;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View, OnAddressSelectedListener {
    View rootView;
    @BindView(R2.id.tv_main_address)
    TextView tvMainAddress;
    Unbinder unbinder;
    @BindView(R2.id.ultra_viewpager)
    UltraViewPager ultraViewPager;
    @BindView(R2.id.iv_zixun)
    ImageView ivZixun;
    @BindView(R2.id.tv_wenzhang)
    ImageView tvWenzhang;
    private TextView tvMainWeixiuzhan;
    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    private LoginResponseBean loginResponseBean;
    private AutoLinearLayout alItemOne;
    private AutoLinearLayout alItemTwo;
    private TextView tvMainMessage;
    List<MessageInfo> lists = new ArrayList<>();
    List<String> list = new ArrayList<>();
    BannerPagerAdapter adapter;
    BottomDialog bottomDialog;

    int i = 0;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    try {
                        if(i>lists.size()){
                            i=0;
                        }
                        tvMainMessage.setText(lists.get(i).getMsgTitle() + "");
                        i++;
                        handler.sendEmptyMessageDelayed(0, 2000);
                    }catch (Exception e){
                       MLog.e("异常");
                    }

                    break;
            }
        }
    };
    private TextView tvLook;
    private TextView tvSearch;
    private TextView tvZixunTitle;
    private TextView tvZixunContext;
    private TextView tvZixuntime;
    private ImageView ivZixun1;
    private TextView tvFarmerTitle;
    private TextView tvFarmerContent;
    private TextView tvFarmerTime;
    private ImageView ivWenzhagn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter.onCreate();
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
                getWeater(aMapLocation.getLongitude() + "," + aMapLocation.getLatitude());
            }
        });
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private void getWeater(String location) {
        String url = "https://free-api.heweather.com/s6/weather?";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("location", location)
                .addParams("key", "2eb9b628139a435684719fab15d1ebff")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ultraViewPager.setAutoScroll(4000);
                        ToastUtils.showShort("获取天气失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MLog.e(response);
                        Gson gson = new Gson();
                        WeaterList weaterList = gson.fromJson(response, new TypeToken<WeaterList>() {
                        }.getType());
                        HeWeather6 heWeather6 = weaterList.getHeWeather6().get(0);
                        FileSaveUtils.fileSaveObject(heWeather6, "weater");
                        adapter = new BannerPagerAdapter(getActivity(), list, new NoDoubleClickListener() {
                            @Override
                            protected void onNoDoubleClick(View v) {
                                ultraViewPager.setAutoScroll(1000000000);

                                bottomDialog.show();
                            }
                        });

                        ultraViewPager.setAdapter(adapter);
                        ultraViewPager.setAutoScroll(4000);

                    }
                });
    }

    private void initListener() {
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "暂未开通该功能，请耐心等待。", Toast.LENGTH_SHORT).show();
            }
        });
        tvMainWeixiuzhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginResponseBean == null || TextUtils.isEmpty(loginResponseBean.getUserId())) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    startActivity(new Intent(mContext, KeepStationActivity.class));

                }
            }
        });


//        alItemOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), WebViewActivity.class);
//                intent.putExtra("url", "https://mp.weixin.qq.com/s/EG0tXoFHa_2nZpEwQjOJ2w");
//                intent.putExtra("title", "农业资讯");
//                startActivity(intent);
//            }
//        });
//        alItemTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                http://www.farmer.com.cn/xwpd/btxw/201805/t20180531_1380906.htm
//                Intent intent = new Intent(getContext(), WebViewActivity.class);
//                intent.putExtra("url", "http://www.farmer.com.cn/xwpd/btxw/201805/t20180531_1380906.htm");
//                intent.putExtra("title", "热门文章");
//                startActivity(intent);
//            }
//        });
        tvLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2018/6/22
                if (loginResponseBean == null || TextUtils.isEmpty(loginResponseBean.getUserId())) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    //消息中心節目
                    startActivity(new Intent(getActivity(), MessageCenterActivity.class));
                }


            }
        });

    }

    private void initViews() {
        mPresenter.getHotData();
        mPresenter.getFarmerData();
        alItemOne = (AutoLinearLayout) rootView.findViewById(R.id.al_item_one);
        alItemTwo = (AutoLinearLayout) rootView.findViewById(R.id.al_item_two);
        tvMainMessage = (TextView) rootView.findViewById(R.id.tv_main_mesage);
        tvLook = (TextView) rootView.findViewById(R.id.tv_look);
        tvSearch = (TextView) rootView.findViewById(R.id.tv_search);
        //todo
        tvZixunTitle = (TextView) rootView.findViewById(R.id.tv_zixunTitle);//title
        tvZixunContext = (TextView) rootView.findViewById(R.id.tv_zixunConent);//content
        tvZixuntime = (TextView) rootView.findViewById(R.id.tv_zixunTime);//time
        ivZixun1 = (ImageView) rootView.findViewById(R.id.iv_zixun);//image

        tvFarmerTitle = (TextView) rootView.findViewById(R.id.tv_farmertitle);
        tvFarmerContent = (TextView) rootView.findViewById(R.id.tv_farmercontent);
        tvFarmerTime = (TextView) rootView.findViewById(R.id.tv_farmertime);
        ivWenzhagn = (ImageView) rootView.findViewById(R.id.tv_wenzhang);


        bottomDialog = new BottomDialog(getActivity());
        bottomDialog.setOnAddressSelectedListener(this);
        bottomDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                ultraViewPager.setAutoScroll(4000);
            }
        });

        list.add("http://pic.dbw.cn/0/09/25/08/9250842_112468.jpg");
        list.add("http://www.nanjing.gov.cn/hdjl/weixin/201710/W020171010647707737249.jpg");
        list.add("http://p4.so.qhmsg.com/bdr/_240_/t01d9ab0e953723b689.jpg");
        list.add("http://pic.90sjimg.com/back_pic/qk/back_origin_pic/00/02/46/2fe8323073c593179f56cbf0f3fc705f.jpg");
        list.add("http://p0.so.qhmsg.com/bdr/_240_/t0197ce49236cf12935.jpg");

        GlideUtils.loadImageView(getActivity(), "https://mmbiz.qpic.cn/mmbiz_jpg/W3OkHlCc5AGC9H85eeGATLpGibS5qJl6vgFhp7OA4ezGxFu6XT41PsBM9ZOSkQ5IYJDUgFJKoxib2JKPorbbiaukA/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1", ivZixun1);
        GlideUtils.loadImageView(getActivity(), "http://p1.so.qhmsg.com/bdr/_240_/t015bced96590d4cbc6.jpg", tvWenzhang);
        tvMainWeixiuzhan = (TextView) rootView.findViewById(R.id.tv_main_weixiuzhan);


        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //UltraPagerAdapter 绑定子view到UltraViewPager
        adapter = new BannerPagerAdapter(getActivity(), list, new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ultraViewPager.setAutoScroll(1000000000);

                bottomDialog.show();
            }
        });

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
        ultraViewPager.getIndicator().setMargin(0, 0, 0, SizeUtils.dp2px(10));
        //构造indicator,绑定到UltraViewPager
        ultraViewPager.getIndicator().build();
        //设定页面循环播放
        ultraViewPager.setInfiniteLoop(true);
        //设定页面自动切换  间隔2秒
        ultraViewPager.setAutoScroll(4000);

    }

    @Override
    public void onResume() {
        super.onResume();
        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        initData();
    }

    private void initData() {
        if (loginResponseBean != null && !TextUtils.isEmpty(loginResponseBean.getUserId())) {
            Map<String, String> map = new HashMap<>();
            map.put("userId", loginResponseBean.getUserId());
            mPresenter.getMessage(map);
        } else {
            tvMainMessage.setText("您还未登录，请登录！");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        handler.removeCallbacksAndMessages(null);
    }

    @OnClick(R2.id.tv_main_address)
    public void onViewClicked() {
        if (!LoginUtil.getUserId()) {
            mContext.startActivity(new Intent(mContext, LoginActivity.class));
            return;
        }
        startActivity(new Intent(getActivity(), JobScreeningActivity.class));
    }

    @OnClick(R2.id.tv_main_nongjitong)
    public void tv_main_nongjitong() {
        if (loginResponseBean == null || TextUtils.isEmpty(loginResponseBean.getUserId())) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else {
            startActivity(new Intent(getActivity(), JobScreeningActivity.class));
        }
    }

    @OnClick(R2.id.tv_main_mainnonghuo)
    public void tv_main_mainnonghuo() {
        startActivity(new Intent(getActivity(), SellAgriculturalGoodsActivity.class));
    }

    @OnClick(R2.id.tv_zixunMore)
    public void tv_zixunMore() {
        ((MainActivity) get_Context()).setCheckedPager(2, R.id.rb_nongjitong, 2);
    }

    @OnClick(R2.id.tv_wenzhangMore)
    public void tv_wenzhang() {
        ((MainActivity) get_Context()).setCheckedPager(2, R.id.rb_nongjitong, 1);
    }

    @OnClick(R2.id.tv_main_wenzhuanjia)
    public void tv_main_wenzhuanjia() {
        ToastUtils.showShortSafe("功能暂未开通,敬请期待!");
        //  ((MainActivity) get_Context()).setCheckedPager(2, R.id.rb_nongjitong,1);
    }


    @OnClick(R2.id.iv_message)
    public void iv_message() {
        if (loginResponseBean == null || TextUtils.isEmpty(loginResponseBean.getUserId())) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else {
            startActivity(new Intent(getActivity(), MessageCenterActivity.class));
        }

    }


    /**
     * 獲取消息信息成功
     *
     * @param list
     */
    @Override
    public void getMessageSuccess(List<MessageInfo> list) {
        lists.clear();
        for (int j = 0; j < list.size(); j++) {
            if ("0".equals(list.get(j).getIsread())) {
                lists.add(list.get(j));
            }
        }
        if (lists.size() != 0) {
            handler.sendEmptyMessage(0);
        } else {
            tvMainMessage.setText("暂无消息！");
        }


    }

    /**
     * 热门文章
     *
     * @param list
     */
    @Override
    public void getHotWordSuccess(final List<InfoBean> list) {
        if (list != null && list.size()>=1) {
            tvFarmerTitle.setText(list.get(0).getTitle());
            tvFarmerContent.setText(list.get(0).getContent());
            tvFarmerTime.setText(list.get(0).getCreateTime());
            GlideUtils.loadImageView(mContext, list.get(0).getPic(), ivWenzhagn);// TODO: 2018/8/12 缺少图片url
            alItemTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                http://www.farmer.com.cn/xwpd/btxw/201805/t20180531_1380906.htm
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", list.get(0).getArticleUrl());
                    intent.putExtra("title", "热门文章");
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * 农业资讯
     *
     * @param list
     */
    @Override
    public void getFarmerWordSuccess(final List<InfoBean> list) {
        if (list != null  && list.size()>=1) {
            tvZixunTitle.setText(list.get(0).getTitle());
            tvZixunContext.setText(list.get(0).getContent());
            tvZixuntime.setText(list.get(0).getCreateTime());
            GlideUtils.loadImageView(mContext, list.get(0).getPic(), ivZixun1);// TODO: 2018/8/12 缺少图片url
            alItemOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra("url", list.get(0).getArticleUrl());
                    intent.putExtra("title", "农业资讯");
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        bottomDialog.dismiss();
        //请求和风天气
        String url = "https://search.heweather.com/find?";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("location", city.getName())
                .addParams("key", "2eb9b628139a435684719fab15d1ebff")
                .addParams("number", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ultraViewPager.setAutoScroll(4000);
                        ToastUtils.showShort("获取天气失败");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MLog.e(response);
                        Gson gson = new Gson();
                        GetCityBean heWeather6 = gson.fromJson(response, new TypeToken<GetCityBean>() {
                        }.getType());
                        String lat = heWeather6.getHeWeather6().get(0).getBasic().get(0).getLat();
                        String lon = heWeather6.getHeWeather6().get(0).getBasic().get(0).getLon();
                        getWeater(lon + "," + lat);

                    }
                });
    }

}
