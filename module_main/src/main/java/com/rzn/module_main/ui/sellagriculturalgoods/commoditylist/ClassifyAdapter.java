package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;

import java.util.List;

/**
 * Created by zz on 2019/5/7.
 */

public class ClassifyAdapter extends BaseQuickAdapter<CommodityListBean,BaseViewHolder> {

    public ClassifyAdapter(int layoutResId, @Nullable List<CommodityListBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CommodityListBean item) {
        helper.setText(R.id.supplier,item.getDealer());
        helper.setText(R.id.name,item.getGoodsName());

    }
}
