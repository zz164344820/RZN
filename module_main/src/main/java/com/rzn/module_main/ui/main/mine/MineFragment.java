package com.rzn.module_main.ui.main.mine;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.LogUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.drivercenter.DriverCenterActivity;
import com.rzn.module_main.ui.mesagecenter.MessageCenterActivity;
import com.rzn.module_main.ui.myadvice.MyAdviceActivity;
import com.rzn.module_main.ui.personalinfo.PersonalInfoActivity;
import com.rzn.module_main.ui.setting.SettingActivity;
import com.zyhealth.expertlib.utils.GlideUtils;
import com.zyhealth.expertlib.utils.MLog;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter> implements MineContract.View {
    View rootView;
    Unbinder unbinder;
    private LinearLayout llMyWork;
    private LinearLayout llMyCollection;
    private LinearLayout llAdvice;
    private LinearLayout llPhoneConcel;
    private LinearLayout llSetting;
    private ImageView tv_bianji, iv_photo, iv_background;
    private TextView tv_Status, tv_name, tv_VersionName;
    private LoginResponseBean loginResponseBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.act_myself, container, false);
        mPresenter.onCreate();
        initViews();
        initListener();
        unbinder = ButterKnife.bind(this, rootView);
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
                LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                if (TextUtils.isEmpty(loginResponseBean.getHandlerId())) {
//                    Toast.makeText(mContext, "您还不是机手！", Toast.LENGTH_LONG).show();/driver/driverident
                    ARouter.getInstance().build("/driver/driverident").navigation();
                    return;
                }
                startActivity(new Intent(mContext, DriverCenterActivity.class));//DriverHomeActivity

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
        tv_Status = (TextView) rootView.findViewById(R.id.tv_Status);
        tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        iv_photo = (ImageView) rootView.findViewById(R.id.iv_photo);
        iv_background = (ImageView) rootView.findViewById(R.id.iv_background);
        tv_VersionName = (TextView) rootView.findViewById(R.id.tv_VersionName);


    }

    private void initData() {

        if (loginResponseBean == null) {
            GlideUtils.loadImageRound(getContext(),"http://img1.3lian.com/gif/more/11/201211/b5442c2bcfbfac31066a747c2c5a0d03.jpg", iv_photo, 60);
            GlideUtils.GaussianBlur(getContext(), "http://img1.3lian.com/gif/more/11/201211/b5442c2bcfbfac31066a747c2c5a0d03.jpg", iv_background, 8, 1);
            return;
        }
        GlideUtils.loadImageRound(getContext(), EncodeUtils.urlDecode(loginResponseBean.getPic()) , iv_photo, 60);
        GlideUtils.GaussianBlur(getContext(),  EncodeUtils.urlDecode(loginResponseBean.getPic()), iv_background, 20, 1);

        MLog.e(EncodeUtils.urlDecode(loginResponseBean.getPic()));
        tv_name.setText(loginResponseBean.getPhone());
        if (TextUtils.isEmpty(loginResponseBean.getHandlerId())) {
            tv_Status.setText("未认证");
        } else {
            tv_Status.setText("已认证");
            tv_Status.setTextColor(getResources().getColor(R.color.main_color));
        }

        tv_VersionName.setText("版本号：v " + AppUtils.getAppVersionName());
    }

    @Override
    public void onResume() {
        super.onResume();
//        LogUtils.d("aaaaaaaaaa","this is onResume");initData
        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        initData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R2.id.iv_message)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MessageCenterActivity.class));
    }
}
