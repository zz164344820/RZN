package com.rzn.module_main.ui.main.mine.payment_pwd.findpayment_pwd;


import android.os.Bundle;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.views.CountdownTextView;
import com.rzn.module_main.R;

import org.w3c.dom.Text;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class VerifyMessageActivity extends MVPBaseActivity<VerifyMessageContract.View, VerifyMessagePresenter> implements VerifyMessageContract.View {

    TextView  tv_phone;
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
        showPhone(loginResponseBean);

         setTitle("找回支付密码");
         tv_phone =(TextView) findViewById(R.id.tv_phone);
         ed_authCode =(EditText) findViewById(R.id.ed_authCode);
         tv_getAuthCode = (CountdownTextView)findViewById(R.id.tv_getAuthCode);

         findViewById(R.id.bt_affirm).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //下一步
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
        if(loginResponseBean!=null && loginResponseBean.getPhone().length()==11){
            phoneText.append(loginResponseBean.getPhone().substring(0,3));
            phoneText.append("****");
            phoneText.append(loginResponseBean.getPhone().substring(7,11));
            tv_phone.setText(phoneText.toString());
        }


    }
}
