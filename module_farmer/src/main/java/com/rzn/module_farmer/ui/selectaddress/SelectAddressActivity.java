package com.rzn.module_farmer.ui.selectaddress;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.SelectAddressBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectAddressActivity extends MVPBaseActivity<SelectAddressContract.View, SelectAddressPresenter> implements SelectAddressContract.View {

    private TextView tvSure;
    private RecyclerView swipeTarget;
    List<SelectAddressBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_select_work);
        mPresenter.onCreate();
        initViews();
        initListener();
//        showLoading(false,"");
    }


    private void initViews() {
        //确认预约按钮
        tvSure = (TextView) findViewById(R.id.tv_sure);
        //recyclerView
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        SelectAddressAdapter selectAddressAdapter = new SelectAddressAdapter(R.layout.item_select_work, list);
        swipeTarget.setAdapter(selectAddressAdapter);

    }

    private void initListener() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求预约接口
            }
        });
    }
}
