package com.rzn.module_main.ui.sellagriculturalgoods.goodsinfo;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.RelativeLayout;

import com.just.agentweb.AgentWeb;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.v5kf.client.lib.V5ClientAgent;
import com.rzn.module_main.R;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GoodsInfoActitiy extends MVPBaseActivity<GoodsInfoContract.View, GoodsInfoPresenter> implements GoodsInfoContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_goodsinfo);

        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();

        setTitle("农货详情");

        AgentWeb.with(this)
                .setAgentWebParent((RelativeLayout) findViewById(R.id.rootView), new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.parseColor("#70C63F"), 3)
                .createAgentWeb()
                .ready()
                .go(getIntent().getStringExtra("url"));


        findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                V5ClientAgent.getInstance().startV5ChatActivity(getApplicationContext());

            }
        });

    }


}
