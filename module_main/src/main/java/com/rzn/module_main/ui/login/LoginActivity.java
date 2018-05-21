package com.rzn.module_main.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.utils.TextUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chihane.jdaddressselector.AddressUtils;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;
import cn.jpush.android.api.JPushInterface;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View ,OnAddressSelectedListener {


    @BindView(R2.id.ed_phoneNum)
    EditText edPhoneNum;
    @BindView(R2.id.ed_authCode)
    EditText edAuthCode;
    @BindView(R2.id.tv_getAuthCode)
    TextView tvGetAuthCode;
    @BindView(R2.id.bt_affirm)
    Button btAffirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_act_login);
        ButterKnife.bind(this);
        mPresenter.onCreate();
        JPushInterface.setAlias(this, 111, "123456");

    }

    @Override
    public void initView() {
        super.initView();
        tvGetAuthCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mPresenter.getAuthCode(edPhoneNum.getText().toString());
            }
        });
        btAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mPresenter.login("18810050361", "7859");
                if (!TextUtils.isEmpty(edPhoneNum.getText().toString()) && !TextUtils.isEmpty(edAuthCode.getText().toString())) {
                    if (btAffirm.isClickable()) {
                        mPresenter.login(edPhoneNum.getText().toString(), edAuthCode.getText().toString());
                    } else {
                        ToastUtils.showShort("请同意协议后在录");
                    }
                }
            }
        });

    }

    /**
     * 登录成功回调
     */
    @Override
    public void loginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        ToastUtils.showShort(province.getName()+"---"+county.getName()+"---"+county.getName());
    }
}
