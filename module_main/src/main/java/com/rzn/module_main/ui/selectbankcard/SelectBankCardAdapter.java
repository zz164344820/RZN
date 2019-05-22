package com.rzn.module_main.ui.selectbankcard;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.rzn.module_main.ui.applygetmoney.bean.BankMessageBean;

import java.util.List;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/19.
 */

public class SelectBankCardAdapter extends BaseQuickAdapter<BankMessageBean, BaseViewHolder> {
    public SelectBankCardAdapter(int layoutResId, @Nullable List<BankMessageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, BankMessageBean item) {
        helper.setText(R.id.tv_bank_name, item.getBankName());
        helper.setText(R.id.tv_card_code, "卡号：" + item.getBankCard());
//        helper.getView(R.id.rl_all).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              helper.getView(R.id.iv_select).setVisibility(View.VISIBLE);
//            }
//        });

    }
}
