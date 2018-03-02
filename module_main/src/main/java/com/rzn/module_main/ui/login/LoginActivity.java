package com.rzn.module_main.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {


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
        // ARouter.getInstance().build("/farmer/test").navigation();
    }


    @OnClick(R2.id.bt_affirm)
    public void onViewClicked() {
        startActivity(new Intent(this, MainActivity.class));

    }
}
