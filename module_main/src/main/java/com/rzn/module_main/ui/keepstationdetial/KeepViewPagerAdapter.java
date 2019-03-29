package com.rzn.module_main.ui.keepstationdetial;

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
 * RAY.LEE
 * Created by 17662on 2019/3/18.
 */

public class KeepViewPagerAdapter extends PagerAdapter {

    private List<String> list;
    private Context context;
    View view;

    public KeepViewPagerAdapter(List<String> list, Context context) {

        this.list = list;
        this.context = context;
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
        view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.banner);
        GlideUtils.loadImageView(context, list.get(position), imageView,R.drawable.ic_pit);
        container.addView(view);
        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
}
