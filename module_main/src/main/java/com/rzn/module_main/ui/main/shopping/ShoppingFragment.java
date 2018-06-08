package com.rzn.module_main.ui.main.shopping;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.login.LoginActivity;
import com.rzn.module_main.ui.mesagecenter.MessageCenterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ShoppingFragment extends MVPBaseFragment<ShoppingContract.View, ShoppingPresenter> implements ShoppingContract.View {

    Unbinder unbinder;
    private View rootView;
    private RecyclerView swipeTarget;
    private LoginResponseBean loginResponseBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shop, container, false);
        initViews();
//      initListener();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initViews() {

        swipeTarget = (RecyclerView) rootView.findViewById(R.id.swipe_target);
        swipeTarget.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        ShoppingAdapter shoppingAdapter = new ShoppingAdapter(R.layout.item_shop, s);
        swipeTarget.setAdapter(shoppingAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
    }

    @OnClick(R2.id.iv_message)
    public void onViewClicked() {
        if (loginResponseBean == null || TextUtils.isEmpty(loginResponseBean.getUserId())) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else {
            startActivity(new Intent(getActivity(), MessageCenterActivity.class));
        }

//        startActivity(new Intent(getActivity(), MessageCenterActivity.class));
    }
}
