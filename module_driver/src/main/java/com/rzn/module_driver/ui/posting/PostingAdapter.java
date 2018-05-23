package com.rzn.module_driver.ui.posting;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;

import java.util.List;

/**
 * Created by 17662 on 2018/5/23.
 */

public class PostingAdapter extends BaseQuickAdapter<DriverGrabOrderInfo, BaseViewHolder> {
    public PostingAdapter(int layoutResId, @Nullable List<DriverGrabOrderInfo> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DriverGrabOrderInfo item) {

    }
}
