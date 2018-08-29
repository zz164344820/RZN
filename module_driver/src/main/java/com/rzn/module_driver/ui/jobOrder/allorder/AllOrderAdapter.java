package com.rzn.module_driver.ui.jobOrder.allorder;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_driver.R;
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
//        private String status;//":"1", //订单状态 1: 待接单  2:  待作业  3:已取消  4:已完成
//        private String flag;//":"2", //地块分布 1集中连片 2零星分散
//        private String flagNum;//":"", //当是零星分散时的分散快数
        helper.setText(R.id.tv_title, item.getTypes());
        if ("1".equals(item.getStatus())) {
            helper.setText(R.id.tv_lable, "待接单");
        } else if ("2".equals(item.getStatus())) {
            helper.setText(R.id.tv_lable, "待作业");
        } else if ("3".equals(item.getStatus())) {
            helper.setText(R.id.tv_lable, "已取消");
        } else if ("4".equals(item.getStatus())) {
            helper.setText(R.id.tv_lable, "已完成");
        }

        if ("1".equals(item.getFlag())) {
            helper.setText(R.id.tv_name_area, item.getAreas() + "亩/" + "集中连片/" + item.getFlagNum() + "块");
        } else if ("2".equals(item.getFlag())) {
            helper.setText(R.id.tv_name_area, item.getAreas() + "亩/" + "零星分散/" + item.getFlagNum() + "块");
        }

        helper.setText(R.id.tv_adress, item.getProvinceName()  + item.getCityName()+ item.getAreaName() + "");
        helper.setText(R.id.tv_time, item.getStartDate() + "至" + item.getEndDate());
        helper.setText(R.id.tv_num, item.getAreas() + "亩");
        helper.setText(R.id.tv_true_money, item.getTotalprice() + "元");
//        if ("2".equals(item.getStatus())) {
//            helper.getView(R.id.tv_num).setVisibility(View.GONE);
//        } else if ("4".equals(item.getStatus())) {
//
//        }

    }
}
