package com.rzn.module_main.ui.getmoneydetial;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.moneydetial.bean.AccountWaterListBean;

import java.util.List;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/17.
 */

public class GetMoneyDetialAdapter extends BaseQuickAdapter<AccountWaterListBean, BaseViewHolder> {
    public GetMoneyDetialAdapter(int layoutResId, @Nullable List<AccountWaterListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountWaterListBean item) {
//        private String accountWaterId;//": 402882ee6a7c1eda016a7c3b1bc70001, //账户流水id
//        private String amt;//": 12, //金额
//        private String createTime;//": "2019-05-03 13:46:50", //申请创建时间
//        private String status;//": "3", //账户流水状态：1：支付成功入账到余额  2：提现中  3：提现成功    4：提现失败
//        private String taskTypes;//": "", //订单作业类型(订单标题)
//        private String type;//": "2", //流水类型：1：收益  2: 提现 (支出）
//        private String userName;//": "支付测试" //用户姓名

        helper.setText(R.id.tv_title, item.getAmt());
        helper.setText(R.id.tv_date, item.getCreateTime());
        if ("1".equals(item.getStatus())) {
            helper.setText(R.id.tv_money, "支付成功入账到余额");
        } else if ("2".equals(item.getStatus())) {
            helper.setText(R.id.tv_money, "提现中");
            helper.setTextColor(R.id.tv_money, Color.parseColor("#FB9300"));
        } else if ("3".equals(item.getStatus())) {
            helper.setText(R.id.tv_money, "提现成功");
            helper.setTextColor(R.id.tv_money, Color.parseColor("#70c63f"));
        } else if ("4".equals(item.getStatus())) {
            helper.setText(R.id.tv_money, "提现失败");
            helper.setTextColor(R.id.tv_money, Color.parseColor("#ff5050"));
        }


//        if ("1".equals(item.getType())){
//            helper.setText(R.id.tv_money, "+" + item.getAmt());
//            helper.setTextColor(R.id.tv_money, Color.parseColor("#70c63f"));
//        }else if ("2".equals(item.getType())){
//            helper.setText(R.id.tv_money, "-" + item.getAmt());
//            helper.setTextColor(R.id.tv_money, Color.parseColor("#ff5050"));
//        }


    }
}
