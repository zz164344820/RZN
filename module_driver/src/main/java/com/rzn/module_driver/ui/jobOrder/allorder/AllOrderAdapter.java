package com.rzn.module_driver.ui.jobOrder.allorder;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_driver.ui.bean.MyWorkDetialBean;

import java.util.List;

/**
 * Created by Ray.lee on 2018/4/11.
 */

public class AllOrderAdapter extends BaseQuickAdapter<MyWorkDetialBean, BaseViewHolder> {

    public AllOrderAdapter(int layoutResId, @Nullable List<MyWorkDetialBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyWorkDetialBean item) {

    }
}
