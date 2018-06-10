package com.rzn.module_main.ui.webview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WebViewActivity extends MVPBaseActivity<WebViewContract.View, WebViewPresenter> implements WebViewContract.View {
    AgentWeb mAgentWeb;
    @BindView(R2.id.rootView)
    LinearLayout rootView;
    String url="";
    String title="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        ButterKnife.bind(this);
        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();

        url= getIntent().getStringExtra("url");
        title= getIntent().getStringExtra("title");
        setTitle(title);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) rootView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }
}
