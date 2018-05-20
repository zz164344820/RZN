package com.rzn.module_farmer.ui.farmerlist;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;

import java.util.List;

/**
 * Created by a111 on 2018/4/23.
 */

public class FarmerListAdapter extends BaseQuickAdapter<FarmerDriverMessageBean, BaseViewHolder> {

    private String contents;

    public FarmerListAdapter(@Nullable List<FarmerDriverMessageBean> data) {
        super(R.layout.farmer_item_farmerlist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FarmerDriverMessageBean item) {
        helper.addOnClickListener(R.id.tv_work);
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_price, item.getMinUnitPrice() + "元/亩");
        contents="";
        for (int i = 0; i < item.getTypes().size(); i++) {
            contents = contents + item.getTypes().get(i).getKindName() +"-"+ item.getTypes().get(i).getKindTypeName()+"  ";
        }
        helper.setText(R.id.tv_contents, contents);

    }

//    @Override
//    protected void convert(BaseViewHolder helper, String item) {
//
//    }
}
