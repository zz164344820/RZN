package com.rzn.module_farmer.ui.sendwork;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.WorkTypeBean;
import com.rzn.module_farmer.bean.WorkTypeObjBean;

import java.util.List;

/**
 * Created by 17662 on 2018/5/14.
 */

public class TypesAdapter extends BaseQuickAdapter<WorkTypeObjBean, BaseViewHolder> {
    private int postion;
//    public void getPosition(int position) {
//        this.postion = postion;
//    }
    public TypesAdapter(int layoutResId, @Nullable List<WorkTypeObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkTypeObjBean item) {
        helper.setText(R.id.tv_type, item.getTypeName());
//        if (postion == helper.getPosition()) {
//            helper.setTextColor(R.id.tv_type, Color.parseColor("#70c63f"));
//        } else {
//            helper.setTextColor(R.id.tv_type, Color.parseColor("#333333"));
//        }
    }


}
