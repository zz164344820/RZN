package com.rzn.module_main.ui.main.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;

import io.reactivex.internal.operators.observable.ObservableNever;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter> implements MineContract.View {
    View rootView;
    private LinearLayout llMyWork;
    private LinearLayout llMyCollection;
    private LinearLayout llAdvice;
    private LinearLayout llPhoneConcel;
    private LinearLayout llSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.act_myself, container, false);
        initViews();
        initListener();
        return rootView;
    }

    private void initListener() {
        llAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        llMyCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        llMyWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        llPhoneConcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        llSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void initViews() {

        llMyWork = (LinearLayout) rootView.findViewById(R.id.ll_my_work);
        llMyCollection = (LinearLayout) rootView.findViewById(R.id.ll_my_collection);
        llAdvice = (LinearLayout) rootView.findViewById(R.id.ll_advice);
        llPhoneConcel = (LinearLayout) rootView.findViewById(R.id.ll_phone_concel);
        llSetting = (LinearLayout) rootView.findViewById(R.id.ll_setting);

    }
}
