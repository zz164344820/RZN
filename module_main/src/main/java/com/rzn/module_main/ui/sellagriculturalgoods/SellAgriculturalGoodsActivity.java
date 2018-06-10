package com.rzn.module_main.ui.sellagriculturalgoods;


import android.os.Bundle;
import android.support.annotation.Nullable;
import com.rzn.module_main.R;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SellAgriculturalGoodsActivity extends MVPBaseActivity<SellAgriculturalGoodsContract.View, SellAgriculturalGoodsPresenter> implements SellAgriculturalGoodsContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sellagriculturegoods);
        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("卖农货");
    }
}
