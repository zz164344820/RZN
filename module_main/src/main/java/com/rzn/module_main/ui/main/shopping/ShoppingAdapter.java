package com.rzn.module_main.ui.main.shopping;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 17662 on 2018/5/21.
 */

public class ShoppingAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ShoppingAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
