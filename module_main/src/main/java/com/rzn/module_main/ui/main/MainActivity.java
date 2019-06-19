package com.rzn.module_main.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.views.AutoRadioGroup;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.main.farmmachinery.FarmMachineryFragment;
import com.v5kf.client.lib.V5ClientAgent;
import com.v5kf.client.lib.V5ClientConfig;
import com.zyhealth.expertlib.LibApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chihane.jdaddressselector.AddressUtils;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */
@Route(path = "/main/main")
public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    @BindView(R2.id.rb_homepage)
    RadioButton rbHomepage;
    @BindView(R2.id.rg_bottom)
    AutoRadioGroup rgBottom;
    @BindView(R2.id.viewpager)
    ViewPager viewpager;
    private long lastTime = 0; //记录上次点击的时间
    LoginResponseBean loginResponseBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act_main);
        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        String value = getIntent().getStringExtra("value");
        if (!TextUtils.isEmpty(value)) {
            getJPush(value);
        }
        setV5Info(loginResponseBean);
        ButterKnife.bind(this);
        mPresenter.onCreate();
        AddressUtils.CreateDBData(this, "address.json");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        rbHomepage.setChecked(true);
        String value = intent.getStringExtra("value");
        if (!TextUtils.isEmpty(value)) {
            getJPush(value);
        }
    }

    private void getJPush(String value) {

        if (loginResponseBean == null || TextUtils.isEmpty(loginResponseBean.getUserId())) {
            return;
        }
        //1: 预约  2：取消  3：认证   4：抢单
        if ("3".equals(value)) {
            //  认证提醒 跳转技手认证
            ARouter.getInstance().build("/driver/makesure").navigation();//审核中界面
        } else if ("4".equals(value)) {
//            接单提醒（接单，农户发布的作业有人接单了，跳转到具体页面）
            //作业订单详情/main/joborderdetial"
            // TODO: 2018/6/8  JobOrderDetialActivity这个类需要得参数  flag = getIntent().getStringExtra("flag");  farmerTaskId = getIntent().getStringExtra("farmerTaskId");
//            ARouter.getInstance().build("/driver/joborderdetial").navigation();//审核中界面   todo 需要参数，需要通知传递过来
            ARouter.getInstance().build("/driver/myjobdetial").navigation();
        } else if ("1".equals(value)) {
//            预约提醒（预约，有农户预约技手，跳转到具体页面）
            // TODO: 2018/6/8  JobOrderDetialActivity这个类需要得参数  flag = getIntent().getStringExtra("flag");  farmerTaskId = getIntent().getStringExtra("farmerTaskId");
//            ARouter.getInstance().build("/driver/joborderdetial").navigation();//审核中界面    todo 需要参数，需要通知传递过来
            ARouter.getInstance().build("/driver/myjobdetial").withString("type", "1").navigation();
        } else if ("2".equals(value)) {
//            预约提醒（预约，取消提醒，跳转到具体页面）
            // TODO: 2018/6/8  JobOrderDetialActivity这个类需要得参数  flag = getIntent().getStringExtra("flag");  farmerTaskId = getIntent().getStringExtra("farmerTaskId");
//            ARouter.getInstance().build("/driver/joborderdetial").navigation();//审核中界面    todo 需要参数，需要通知传递过来
            ARouter.getInstance().build("/driver/myjobdetial").navigation();

        }
    }


    @Override
    public void initView() {
        super.initView();
        rbHomepage.setChecked(true);
        viewpager.setCurrentItem(0);
        mPresenter.initViewPager(viewpager);
        mPresenter.initRadioGroup(rgBottom);
    }

    public void setCheckedPager(int checkIndex, int viewId, int Type) {
        viewpager.setCurrentItem(checkIndex);
        rgBottom.check(viewId);
        if (checkIndex == 2) {
            ((FarmMachineryFragment) mPresenter.fragments.get(2)).setCheckedTab(Type);
        }
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - lastTime) > 2000) {
                Toast.makeText(MainActivity.this, "在按一次退出程序", Toast.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();
            } else {
                for (int i = 0, size = LibApplication.mActivityStack.size(); i < size; i++) {
                    LibApplication.mActivityStack.get(i).finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick(R2.id.action_button)
    public void onViewClicked() {

        V5ClientAgent.getInstance().startV5ChatActivity(getApplicationContext());
    }

    private void setV5Info(LoginResponseBean loginResponseBean) {
        if(loginResponseBean==null){
            return;
        }
        V5ClientConfig.getInstance(this).shouldUpdateUserInfo();
        V5ClientConfig config = V5ClientConfig.getInstance(this);

        config.setNickname(loginResponseBean.getFarmerName());

        config.setAvatar(loginResponseBean.getPic());
    }
}
