package com.rzn.module_main.ui.main.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.driverhome.DriverHomeActivity;
import com.rzn.module_main.ui.myadvice.MyAdviceActivity;
import com.rzn.module_main.ui.personalinfo.PersonalInfoActivity;
import com.rzn.module_main.ui.setting.SettingActivity;

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
    private ImageView tv_bianji;

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
                startActivity(new Intent(mContext, MyAdviceActivity.class));
            }
        });


        llMyCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "暂未开通", Toast.LENGTH_LONG).show();
            }
        });
        llMyWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/driver/myjobdetial").navigation();
            }
        });
        llPhoneConcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, DriverHomeActivity.class));

            }
        });
        llSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, SettingActivity.class));

            }
        });

        tv_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, PersonalInfoActivity.class));
            }
        });
    }

    private void initViews() {

        llMyWork = (LinearLayout) rootView.findViewById(R.id.ll_my_work);
        llMyCollection = (LinearLayout) rootView.findViewById(R.id.ll_my_collection);
        llAdvice = (LinearLayout) rootView.findViewById(R.id.ll_advice);
        llPhoneConcel = (LinearLayout) rootView.findViewById(R.id.ll_phone_concel);
        llSetting = (LinearLayout) rootView.findViewById(R.id.ll_setting);
        tv_bianji = (ImageView) rootView.findViewById(R.id.tv_bianji);

    }
}
