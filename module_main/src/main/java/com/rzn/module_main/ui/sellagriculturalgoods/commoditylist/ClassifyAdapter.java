package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;
import com.zyhealth.expertlib.utils.GlideUtils;

import java.util.List;

/**
 * Created by zz on 2019/5/7.
 */

public class ClassifyAdapter extends BaseQuickAdapter<CommodityListBean,BaseViewHolder> {

    Context context;
    public ClassifyAdapter(Context context,int layoutResId, @Nullable List<CommodityListBean> data) {
        super(layoutResId, data);
        this.context =context;
    }


    @Override
    protected void convert(BaseViewHolder helper, CommodityListBean item) {
        helper.setText(R.id.supplier,"采购商："+item.getDealer());
        helper.setText(R.id.name,item.getGoodsName());
        ImageView imageView= helper.getView(R.id.image);
        GlideUtils.loadImageView(context,item.getUrl(),imageView);


    }
}
