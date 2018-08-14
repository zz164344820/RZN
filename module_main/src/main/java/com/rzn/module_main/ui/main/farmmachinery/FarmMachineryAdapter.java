package com.rzn.module_main.ui.main.farmmachinery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;
import com.zyhealth.expertlib.utils.GlideUtils;

import java.util.List;

/**
 * Created by 17662 on 2018/5/21.
 */

public class FarmMachineryAdapter extends BaseQuickAdapter<InfoBean, BaseViewHolder> {

    Context context;

    public FarmMachineryAdapter(@Nullable List<InfoBean> data, Context context) {
        super(R.layout.item_farm_machinery, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setText(R.id.tv_time, item.getCreateTime());
        ImageView imageView = helper.getView(R.id.iv_pic);
        GlideUtils.loadImageView(context, item.getPic(), imageView);// TODO: 2018/8/12 缺少图片url

    }
}
