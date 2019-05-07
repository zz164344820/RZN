package com.rzn.module_main.ui.sellagriculturalgoods;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class CommodityListFragment extends Fragment {
    private String mTitle;

    public static CommodityListFragment getInstance(String title) {
        CommodityListFragment sf = new CommodityListFragment();
        sf.mTitle = title;
        return sf;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView=new TextView(getActivity());
        textView.setText(mTitle);

        return textView;
    }
}