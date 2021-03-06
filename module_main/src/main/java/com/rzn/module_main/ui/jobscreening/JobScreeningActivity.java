package com.rzn.module_main.ui.jobscreening;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.rzn.commonbaselib.applictaion.ViewManager;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.views.AutoRadioGroup;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.zyhealth.expertlib.utils.MLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/module/JobScreeningActivity")
public class JobScreeningActivity extends MVPBaseActivity<JobScreeningContract.View, JobScreeningPresenter> implements JobScreeningContract.View {
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.iv_right)
    ImageView ivRight;
    @BindView(R2.id.rb_rarmer)
    RadioButton rbRarmer;
    @BindView(R2.id.rgGroup)
    AutoRadioGroup rgGroup;
    MVPBaseFragment driverFargment ,farmerFargment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_jobscreening);
        ButterKnife.bind(this);
        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();
        String addressName= SPUtils.getInstance().getString("addressName");
        setTitle(addressName);
        setRightImage(R.drawable.order_liebiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/driver/myjobdetial").navigation();
            }
        });
        initFragment();
        initRadioGroup(rgGroup);
    }

    private void initFragment() {
        farmerFargment = (MVPBaseFragment) ARouter.getInstance().build("/farmer/farmerFargment").navigation();
        driverFargment = (MVPBaseFragment) ARouter.getInstance().build("/driver/driverFargment").navigation();
        getSupportFragmentManager().beginTransaction().add(R.id.rl_content,farmerFargment).commitAllowingStateLoss();
    }


    public void initRadioGroup(RadioGroup radioGroup) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i==R.id.rb_rarmer){
                    //显示农户作业
                    if(!farmerFargment.isAdded()){
                        getSupportFragmentManager().beginTransaction().add(R.id.rl_content,farmerFargment).hide(driverFargment).commitAllowingStateLoss();
                    }else{
                        getSupportFragmentManager().beginTransaction().show(farmerFargment).hide(driverFargment).commitAllowingStateLoss();
                    }
                }else if(i==R.id.rb_driver){
                    //显示机手作业
                    if(!driverFargment.isAdded()){
                        getSupportFragmentManager().beginTransaction().add(R.id.rl_content,driverFargment).hide(farmerFargment).commitAllowingStateLoss();
                    }else{
                        getSupportFragmentManager().beginTransaction().show(driverFargment).hide(farmerFargment).commitAllowingStateLoss();

                    }
                }
            }
        });
    }


}
