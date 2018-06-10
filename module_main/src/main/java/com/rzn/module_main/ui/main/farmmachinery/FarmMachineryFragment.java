package com.rzn.module_main.ui.main.farmmachinery;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.views.AutoRadioGroup;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.login.LoginActivity;
import com.rzn.module_main.ui.main.farmmachinery.wenzhang.WenZhangFragment;
import com.rzn.module_main.ui.main.farmmachinery.zixun.ZixunFragment;
import com.rzn.module_main.ui.mesagecenter.MessageCenterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmMachineryFragment extends MVPBaseFragment<FarmMachineryContract.View, FarmMachineryPresenter> implements FarmMachineryContract.View {

    Unbinder unbinder;
    @BindView(R2.id.rb_rarmer)
    RadioButton rbRarmer;
    @BindView(R2.id.rb_driver)
    RadioButton rbDriver;
    @BindView(R2.id.rgGroup)
    AutoRadioGroup rgGroup;
    @BindView(R2.id.ll_listConent)
    FrameLayout llListConent;
    private View rootView;
    private LoginResponseBean loginResponseBean;
     WenZhangFragment  wenZhangFragment  ;
     ZixunFragment zixunFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_farmer_driver, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {
       wenZhangFragment   =   new WenZhangFragment();
       zixunFragment = new ZixunFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.ll_listConent,wenZhangFragment).commitAllowingStateLoss();

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rb_rarmer){
                    if(wenZhangFragment==null){
                        wenZhangFragment = new WenZhangFragment();
                    }
                   getChildFragmentManager().beginTransaction().replace(R.id.ll_listConent,wenZhangFragment).commitAllowingStateLoss();
                }else {
                    if(zixunFragment==null){
                        zixunFragment = new ZixunFragment();
                    }
                    getChildFragmentManager().beginTransaction().replace(R.id.ll_listConent,zixunFragment).commitAllowingStateLoss();
                }

            }
        });

    }

    public void setCheckedTab(int type) {
        if (type == 1) {
            rgGroup.check(R.id.rb_rarmer);
        } else {
            rgGroup.check(R.id.rb_driver);
        }
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
    }
}
