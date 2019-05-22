package com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd;


import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.listener.CountdownCallBack;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.views.CountdownTextView;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.newpwd.NewPasswordActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 * <p>
 * <p>
 * todo
 * 需要替换接口地址
 */

public class VerifyMessageActivity extends MVPBaseActivity<VerifyMessageContract.View, VerifyMessagePresenter> implements VerifyMessageContract.View {

    TextView tv_phone;
    EditText ed_authCode;
    CountdownTextView tv_getAuthCode;
    Button bt_affirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_verifymessage);
        mPresenter.onCreate();

    }

    @Override
    public void initView() {
        super.initView();
        final LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");

        setTitle("找回支付密码");
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        ed_authCode = (EditText) findViewById(R.id.ed_authCode);
        tv_getAuthCode = (CountdownTextView) findViewById(R.id.tv_getAuthCode);
        showPhone(loginResponseBean);

        findViewById(R.id.bt_affirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //下一步
//                code	Y	手机验证码	String
//                phone	Y	手机号	String

                Map<String, String> map = new HashMap<>();
                map.put("code", ed_authCode.getText().toString().trim());
                map.put("phone", loginResponseBean.getPhone());
                mPresenter.nextData(map);
            }
        });

        tv_getAuthCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getAuthCode(loginResponseBean.getPhone());
                tv_getAuthCode.setClickable(false);
            }
        });
    }

    private void showPhone(LoginResponseBean loginResponseBean) {
        StringBuffer phoneText = new StringBuffer();
        if (loginResponseBean != null && loginResponseBean.getPhone().length() == 11) {
            phoneText.append(loginResponseBean.getPhone().substring(0, 3));
            phoneText.append("****");
            phoneText.append(loginResponseBean.getPhone().substring(7, 11));
            tv_phone.setText(phoneText.toString());
        }


    }


    @Override
    public void restoreTextView() {
        tv_getAuthCode.setText("再次获取");
        tv_getAuthCode.setBackgroundColor(getResources().getColor(R.color.main_color));
        tv_getAuthCode.setClickable(true);
    }

    @Override
    public void restoreClickTextView() {
        tv_getAuthCode.setBackgroundColor(getResources().getColor(R.color.main_color));
        tv_getAuthCode.setClickable(true);
    }

    @Override
    public void startRun() {
        tv_getAuthCode.startCountdown(60, new CountdownCallBack() {
            @Override
            public void countdownFinsh() {
                tv_getAuthCode.setText("再次获取");
                tv_getAuthCode.setBackgroundColor(getResources().getColor(R.color.main_color));
                tv_getAuthCode.setClickable(true);
            }
        });
    }

    /**
     * 登录成功回调
     */
    @Override
    public void loginSuccess() {

        //跳转设置密码
        startActivity(new Intent(this, NewPasswordActivity.class));
        finish();

    }

}
