package com.rzn.module_main.ui.bankcard;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.applygetmoney.bean.BankMessageBean;
import com.rzn.module_main.ui.mesagecenter.MessageInfo;

import java.util.List;

/**
 * RAY.LEE
 * Created by 17662on 2019/5/16.
 */

public class BankCardAdapter extends BaseQuickAdapter<BankMessageBean, BaseViewHolder> {
    public BankCardAdapter(int layoutResId, @Nullable List<BankMessageBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankMessageBean item) {

//        private String bankCard;//": "6227***********4890",//卡号
//        private String bankName;//": "北京银行",//银行名称
//        private String branchBankName;//": "北京市地址北街二号支行",//支行
//        private String cardholder;//": "胡君宝", //磁持人
//        private String createTime;//": "2019-05-03 13:39:59", //创建时间
//        private String userId;//": "40289f376536de0401653734d39d0000", //用户id
//        private String userInfoId;//": "402882ee6a7c1eda016a7c34d61e0000" //用户详情银行卡id


        helper.setText(R.id.tv_bank_name, item.getBankName() + "");
        helper.setText(R.id.tv_card_type, "");
        helper.setText(R.id.tv_code, item.getBankCard() + "");
        helper.setText(R.id.tv_people, "开户人:" + item.getCardholder());
        helper.setText(R.id.tv_open_bank, "开户支行:" + item.getBranchBankName());


        helper.addOnClickListener(R.id.iv_modiffy);
        helper.addOnClickListener(R.id.iv_delete);


    }
}
