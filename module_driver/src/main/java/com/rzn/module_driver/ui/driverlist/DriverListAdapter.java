package com.rzn.module_driver.ui.driverlist;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;
import com.zyhealth.expertlib.utils.GlideUtils;

import java.util.List;

/**
 * Created by zz on 2018/3/4.
 */

public class DriverListAdapter extends BaseQuickAdapter<DriverGrabOrderInfo, BaseViewHolder> {

    public DriverListAdapter(@Nullable List<DriverGrabOrderInfo> data) {
        super(R.layout.driver_item_driverlists, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DriverGrabOrderInfo item) {
        helper.addOnClickListener(R.id.tv_get_post);

        helper.setText(R.id.tv_name_product, item.getTypes());

        helper.setText(R.id.tv_name, item.getName());
        if ("1".equals(item.getFlag())) {
            helper.setText(R.id.tv_name_scope, item.getAreas() + "亩/" + "集中连片");
        } else if ("2".equals(item.getFlag())) {
            helper.setText(R.id.tv_name_scope, item.getAreas() + "亩/" + "零星分散");
        }

        helper.setText(R.id.tv_address, "作业地点：" + item.getProvince_name() + item.getCity_name() + item.getArea_name());
        helper.setText(R.id.tv_time, "作业时间：" + item.getStart_date() + "至" + item.getEnd_date());
        helper.setText(R.id.tv_money, item.getTotalprice() + "元");
        helper.setText(R.id.tv_money_scope, "(" + item.getUnit_price() + "元/亩)");

//        helper.setText(R.id.tv_name_product, item.getTypes());
//        helper.setText(R.id.tv_name_product, item.getTypes());

    }
}
