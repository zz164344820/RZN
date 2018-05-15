package com.rzn.module_farmer.ui.selectaddress;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.SelectAddressBean;

import java.util.List;

/**
 * Created by 17662 on 2018/5/15.
 */

public class SelectAddressAdapter extends BaseQuickAdapter<SelectAddressBean, BaseViewHolder> {
    public SelectAddressAdapter(int layoutResId, @Nullable List<SelectAddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectAddressBean item) {
        helper.setText(R.id.tv_title, item.getTypes());
        helper.setText(R.id.tv_size, item.getAddress());
        helper.setText(R.id.tv_work_adress, item.getAreas());
        helper.setText(R.id.tv_time, item.getCreateTime() + "至" + item.getUpdateTime());
    }
}
