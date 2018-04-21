package com.rzn.module_main.ui.jobscreening;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.rzn.commonbaselib.applictaion.ViewManager;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.commonbaselib.views.AutoRadioGroup;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class JobScreeningActivity extends MVPBaseActivity<JobScreeningContract.View, JobScreeningPresenter> implements JobScreeningContract.View {
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.iv_right)
    ImageView ivRight;
    @BindView(R2.id.rb_rarmer)
    RadioButton rbRarmer;
    @BindView(R2.id.rgGroup)
    AutoRadioGroup rgGroup;


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
        setTitle("张大千");
        initFragment();
        initRadioGroup(rgGroup);
    }

    private void initFragment() {
        MVPBaseFragment driverFargment = ViewManager.getInstance().getFragment(0);
        getSupportFragmentManager().beginTransaction().add(R.id.rl_content,driverFargment).commit();
    }


    public void initRadioGroup(RadioGroup radioGroup) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case 0:

                        //显示农户作业
                        break;
                    case 1:
                        //显示机手作业
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick({R2.id.tv_title, R2.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.tv_title:
                break;
            case R2.id.iv_right:
                break;
        }
    }
}
