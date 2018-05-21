package com.rzn.module_main.ui.main.shopping;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ShoppingFragment extends MVPBaseFragment<ShoppingContract.View, ShoppingPresenter> implements ShoppingContract.View {

    private View rootView;
    private RecyclerView swipeTarget;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shop, container, false);
        initViews();
//        initListener();
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
}
