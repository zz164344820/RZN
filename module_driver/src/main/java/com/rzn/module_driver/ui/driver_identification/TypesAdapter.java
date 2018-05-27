package com.rzn.module_driver.ui.driver_identification;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.WorkTypeObjBean;


import java.util.List;

/**
 * Created by 17662 on 2018/5/14.
 */

public class TypesAdapter extends BaseQuickAdapter<WorkTypeObjBean, BaseViewHolder> {
    private String name = "";
    public void getPost(String name) {
        this.name = name;
    }
//    public void getPosition(int position) {
//        this.postion = postion;
//    }
    public TypesAdapter(int layoutResId, @Nullable List<WorkTypeObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkTypeObjBean item) {
        helper.setText(R.id.tv_type, item.getTypeName());
        if (!TextUtils.isEmpty(name)) {

            if (name.equals(item.getTypeName())) {
                helper.setTextColor(R.id.tv_type, Color.parseColor("#70c63f"));
            } else {
                helper.setTextColor(R.id.tv_type, Color.parseColor("#333333"));
            }

        }
//        if (postion == helper.getPosition()) {
//            helper.setTextColor(R.id.tv_type, Color.parseColor("#70c63f"));
//        } else {
//            helper.setTextColor(R.id.tv_type, Color.parseColor("#333333"));
//        }
    }


}
