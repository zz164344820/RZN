package com.rzn.module_driver.ui.driverlist;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;

import java.util.List;

/**
 * Created by zz on 2018/3/4.
 */

public class DriverListAdapter extends BaseQuickAdapter<DriverGrabOrderInfo,BaseViewHolder> {

    public DriverListAdapter( @Nullable List<DriverGrabOrderInfo> data) {
        super(R.layout.driver_item_driverlists, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DriverGrabOrderInfo item) {
        helper.addOnClickListener(R.id.tv_get_post);

    }
}
