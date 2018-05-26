package com.rzn.module_main.ui.main.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.rzn.module_main.R;
import com.zyhealth.expertlib.utils.GlideUtils;

import java.util.List;

/**
 * Created by zz on 2018/5/26.
 */

public class BannerPagerAdapter  extends PagerAdapter{

    private List<String> list;
    private Context mContext;

    public BannerPagerAdapter(Context mContext,List<String> list) {
        this.list = list;
        this.mContext =mContext;
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

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, null);
        ImageView imageView=(ImageView) view.findViewById(R.id.banner);

        GlideUtils.loadImageView(mContext,list.get(position),imageView);
        container.addView(view);
        return view;
}

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }

}