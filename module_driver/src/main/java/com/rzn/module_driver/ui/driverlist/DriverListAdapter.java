package com.rzn.module_driver.ui.driverlist;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_driver.A;
import com.rzn.module_driver.R;

import java.util.List;

/**
 * Created by zz on 2018/3/4.
 */

public class DriverListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public DriverListAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(R.layout.driver_item_driverlist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


    }
}
