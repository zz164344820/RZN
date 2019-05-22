package com.rzn.module_main.ui.presentationdetial;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.moneydetial.bean.AccountWaterListBean;
import com.rzn.module_main.ui.presentationdetial.bean.PresentationBean;
import com.zyhealth.expertlib.utils.GlideUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PresentationDetialActivity extends MVPBaseActivity<PresentationDetialContract.View, PresentationDetialPresenter> implements PresentationDetialContract.View {


    private AccountWaterListBean accountWaterListBean;
    private TextView tvTime;
    private TextView tvMoney;
    private TextView tvGetType;
    private TextView tvOpenPeople;
    private TextView tvOpenBank;
    private TextView tvOpenZhiBank;
    private TextView tvBankCOde;
    private TextView tvZhuanzhang;
    private ImageView ivZhuanzhang;
    private LinearLayout llZhuan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentaction_detial);
        mPresenter.onCreate();
        initViews();
        setTitle("提现明细");
        initData();

    }

    private void initData() {
//        accountWaterId	Y	    账户流水id	String
        Map<String, String> map = new HashMap<>();
        map.put("accountWaterId", accountWaterListBean.getAccountWaterId());
        mPresenter.getDetialData(map);
    }

    private void initViews() {

        accountWaterListBean = (AccountWaterListBean) getIntent().getSerializableExtra("bean");

        tvTime = (TextView) findViewById(R.id.tv_time);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        tvGetType = (TextView) findViewById(R.id.tv_get_type);
        tvOpenPeople = (TextView) findViewById(R.id.tv_open_people);
        tvOpenBank = (TextView) findViewById(R.id.tv_open_bank);
        tvOpenZhiBank = (TextView) findViewById(R.id.tv_open_zhi_bank);
        tvBankCOde = (TextView) findViewById(R.id.tv_bank_code);

        llZhuan = (LinearLayout) findViewById(R.id.ll_zhuan);
        tvZhuanzhang = (TextView) findViewById(R.id.tv_zhuan_zhang);
        ivZhuanzhang = (ImageView) findViewById(R.id.iv_zhuanzhang);


        llZhuan.setVisibility(View.GONE);
    }


    @Override
    public void getDetialDataSuccess(PresentationBean presentationBean) {
        if (presentationBean != null) {
//            private String accountWaterId;//": "402882ee6a7c1eda016a7c3b1bc70001", //账户流水id
//            private String amt;//": 12,//金额
//            private String bankCard;//": "6227***********3321", //银行卡号
//            private String bankName;//": "建设银行", //开户行
//            private String branchBankName;//": "北京市地址北街一号支行", //支行
//            private String cardholder;//": "胡君宝", //持卡人
//            private String createTime;//": "2019-05-03 13:46:50", //申请提现创建时间
//            private String reason;//": "",
//            private String picProff;//": "http://173rd88727.iok.la/farmHand/handler/downloadFile?fileName=carPic_1553501587601_V4T0R.png", //已转账凭证图片全路径 (当且仅当verdict=1时,字段存在且有效)
//            private String processTime;//": "2019-05-05 15:43:19", //提现处理时间
//            private String status;//": 3,  //账户流水状态：1：支付成功入账到余额  2：提现中  3：提现成功    4：提现失败
//            private String verdict;//": "1" //提现结论 1:已转账  2:驳回申请


            if ("1".equals(presentationBean.getVerdict())) {
                llZhuan.setVisibility(View.VISIBLE);
                tvZhuanzhang.setText("转账凭证");
                GlideUtils.loadImageView(PresentationDetialActivity.this, presentationBean.getPicProff() + "", ivZhuanzhang);

            } else if ("2".equals(presentationBean.getVerdict())) {
                llZhuan.setVisibility(View.VISIBLE);
                tvZhuanzhang.setText("失败原因：" + presentationBean.getReason());
                ivZhuanzhang.setVisibility(View.GONE);
            } else {
                llZhuan.setVisibility(View.GONE);
            }


            tvTime.setText(presentationBean.getCreateTime());
            tvMoney.setText(presentationBean.getAmt());

            if ("1".equals(presentationBean.getStatus())) {
                tvGetType.setText("支付成功入账到余额");
            } else if ("2".equals(presentationBean.getStatus())) {
                tvGetType.setText("提现中");
                tvGetType.setTextColor(Color.parseColor("#FB9300"));
            } else if ("3".equals(presentationBean.getStatus())) {
                tvGetType.setText("提现成功");
                tvGetType.setTextColor(Color.parseColor("#70c63f"));
            } else if ("4".equals(presentationBean.getStatus())) {
                tvGetType.setText("提现失败");
                tvGetType.setTextColor(Color.parseColor("#ff5050"));
            }


            tvOpenPeople.setText(presentationBean.getCardholder());
            tvOpenBank.setText(presentationBean.getBankName());
            tvOpenZhiBank.setText(presentationBean.getBranchBankName());
            tvBankCOde.setText(presentationBean.getBankCard());


        }
    }

    @Override
    public void getDetialDataFailed() {

    }
}
