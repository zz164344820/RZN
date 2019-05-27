package com.rzn.module_main.ui.main.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rzn.commonbaselib.listener.NoDoubleClickListener;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.weather.WeatherActivity;
import com.rzn.module_main.ui.webview.WebViewActivity;
import com.zyhealth.expertlib.utils.GlideUtils;
import com.zyhealth.expertlib.utils.LunarUtils;

import java.util.List;

/**
 * Created by zz on 2018/5/26.
 */

public class BannerPagerAdapter extends PagerAdapter {
    LunarUtils lunarUtils = new LunarUtils();
    private List<String> list;
    private Context mContext;
    String date, date2;
    public HeWeather6 heWeather6;
    View.OnClickListener listener;
    View view;
    int position;

    public BannerPagerAdapter(Context mContext, List<String> list, View.OnClickListener listener) {
        this.list = list;
        this.mContext = mContext;
        this.listener = listener;
        int[] arrData = lunarUtils.getData();
        date = lunarUtils.getStringData("MM月dd日   ") + lunarUtils.StringData();
        date2 = lunarUtils.getTranslateLunarString(arrData[0], arrData[1], arrData[2]);
        date2 = "农历" + date2.substring(5, date2.length());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //布局处理代码
        this.position = position;
        if (position == 0) {
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.banner_weather, null);
            TextView current_temp = (TextView) view.findViewById(R.id.current_temp);
            TextView tv_temp_range = (TextView) view.findViewById(R.id.tv_temp_range);
            TextView tv_weater = (TextView) view.findViewById(R.id.tv_weater);
            TextView tv_wind = (TextView) view.findViewById(R.id.tv_wind);
            TextView tv_rainfall = (TextView) view.findViewById(R.id.tv_rainfall);
            TextView tv_dateSolar = (TextView) view.findViewById(R.id.tv_dateSolar);
            TextView tv_dateLunar = (TextView) view.findViewById(R.id.tv_dateLunar);
            TextView tv_season = (TextView) view.findViewById(R.id.tv_season);
            TextView tv_city = (TextView) view.findViewById(R.id.tv_city);
            tv_city.setOnClickListener(listener);
            if (heWeather6 == null) {
                try {
                    heWeather6 = (HeWeather6) FileSaveUtils.readObject("weater");
                } catch (Exception e) {
                    heWeather6 = null;
                }

            }
            LinearLayout llAll = (LinearLayout) view.findViewById(R.id.ll_all);
            llAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("url", "https://apip.weatherdt.com/h5.html?id=0BP7ncOTFr");
                    intent.putExtra("title","天气情况" );
                    mContext.startActivity(intent);
//                    url= getIntent().getStringExtra("url");
//                    title= getIntent().getStringExtra("title");
//                    mContext.startActivity(new Intent(mContext, WeatherActivity.class));
//                    Intent intent= new Intent();
//                    intent.setAction("android.intent.action.VIEW");
//                    Uri content_url = Uri.parse("https://apip.weatherdt.com/h5.html?id=0BP7ncOTFr");
//                    intent.setData(content_url);
//                    mContext.startActivity(intent);

                }
            });

            if (heWeather6 != null && heWeather6.getNow() != null) {
                current_temp.setText(heWeather6.getNow().getTmp() + "℃");
                tv_temp_range.setText(heWeather6.getDaily_forecast().get(0).getTmp_min() + "℃~" + heWeather6.getDaily_forecast().get(0).getTmp_max() + "℃");
                tv_weater.setText(heWeather6.getNow().getCond_txt());
                tv_wind.setText("风向" + heWeather6.getNow().getWind_dir());
                tv_rainfall.setText("降水量" + heWeather6.getNow().getPcpn() + "毫米");
                tv_season.setText("紫外线强度指数:" + heWeather6.getDaily_forecast().get(0).getUv_index());
                tv_city.setText(heWeather6.getBasic().getParent_city());
            }
            tv_dateSolar.setText(date);
            tv_dateLunar.setText(date2);

        } else {
            view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.banner);
            GlideUtils.loadImageView(mContext, list.get(position), imageView);
        }
        container.addView(view);

        return view;
    }

    //解决数据不刷新的问题
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setHeWeather6(HeWeather6 heWeather6) {
        if (position == 0 && view != null) {
            try {
                TextView current_temp = (TextView) view.findViewById(R.id.current_temp);
                TextView tv_temp_range = (TextView) view.findViewById(R.id.tv_temp_range);
                TextView tv_weater = (TextView) view.findViewById(R.id.tv_weater);
                TextView tv_wind = (TextView) view.findViewById(R.id.tv_wind);
                TextView tv_rainfall = (TextView) view.findViewById(R.id.tv_rainfall);
                TextView tv_season = (TextView) view.findViewById(R.id.tv_season);
                TextView tv_city = (TextView) view.findViewById(R.id.tv_city);
                tv_city.setOnClickListener(listener);
                if (heWeather6 == null) {
                    heWeather6 = (HeWeather6) FileSaveUtils.readObject("weater");
                }


                if (heWeather6 != null) {
                    current_temp.setText(heWeather6.getNow().getTmp() + "℃");
                    tv_temp_range.setText(heWeather6.getDaily_forecast().get(0).getTmp_min() + "℃~" + heWeather6.getDaily_forecast().get(0).getTmp_max() + "℃");
                    tv_weater.setText(heWeather6.getNow().getCond_txt());
                    tv_wind.setText("风向" + heWeather6.getNow().getWind_dir());
                    tv_rainfall.setText("降水量" + heWeather6.getNow().getPcpn() + "毫米");
                    tv_season.setText("紫外线强度指数:" + heWeather6.getDaily_forecast().get(0).getUv_index());
                    tv_city.setText(heWeather6.getBasic().getParent_city());
                }
            } catch (Exception e) {

            }
        }

    }


    public void setCityName(String cityName) {
        if (position == 0 && view != null) {
            TextView tv_city = (TextView) view.findViewById(R.id.tv_city);
            tv_city.setText(cityName);
        }
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }

}