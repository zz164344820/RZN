package com.rzn.module_farmer.ui.sendworksuccess;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.RecommendDriver;

import java.util.List;

/**
 * Created by a111 on 2018/4/23.
 */

public class RecommendFarmerListAdapter extends BaseQuickAdapter<RecommendDriver, BaseViewHolder> {

    private String contents;

    public RecommendFarmerListAdapter(@Nullable List<RecommendDriver> data) {
        super(R.layout.farmer_item_farmerlist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendDriver item) {
        helper.addOnClickListener(R.id.tv_work);
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_price, item.getUnit_price() + "元/亩");
        helper.setText(R.id.tv_contents, item.getKind_type_nameArray());

    }

//    @Override
//    protected void convert(BaseViewHolder helper, String item) {
//
//    }
}
