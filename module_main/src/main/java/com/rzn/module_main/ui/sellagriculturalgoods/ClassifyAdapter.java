package com.rzn.module_main.ui.sellagriculturalgoods;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zz on 2019/5/7.
 */

public class ClassifyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public ClassifyAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
