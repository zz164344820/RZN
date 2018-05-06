package com.rzn.module_main.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;
import com.rzn.module_main.ui.jobscreening.JobScreeningActivity;
import com.rzn.module_main.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;


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
        mPresenter.onCreate();
        JPushInterface.setAlias(this, 111, "123456");

    }


    @OnClick(R2.id.bt_affirm)
    public void onViewClicked() {
        startActivity(new Intent(this, MainActivity.class));

        if (!TextUtils.isEmpty(edPhoneNum.getText().toString()) && !TextUtils.isEmpty(edAuthCode.getText().toString())) {
            if (btAffirm.isClickable()) {
                mPresenter.login(edPhoneNum.getText().toString(), edAuthCode.getText().toString());
            } else {
                Toast.makeText(this, "请同意协议后在录", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 登录成功回调
     */
    @Override
    public void loginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}
