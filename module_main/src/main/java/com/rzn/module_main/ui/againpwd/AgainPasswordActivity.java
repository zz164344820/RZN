package com.rzn.module_main.ui.againpwd;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_main.R;
import com.rzn.module_main.ui.againpwd.bean.AgainPwdBean;

import java.util.HashMap;
import java.util.Map;

import mlxy.utils.L;


/**
 * lilei
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AgainPasswordActivity extends MVPBaseActivity<AgainPasswordContract.View, AgainPasswordPresenter> implements AgainPasswordContract.View {

    //验证码的集合
    private EditText[] codes;
    private EditText etCodeOne;
    private EditText etCodeTwo;
    private EditText etCodeThree;
    private EditText etCodeFour;
    private EditText etCodeFive;
    private EditText etCodeSix;
    private String pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_again_password);
        mPresenter.onCreate();
        initViews();
        setTitle("新支付密码");
        showCode();
    }

    private void initViews() {

        pwd = getIntent().getStringExtra("pwd");


        etCodeOne = (EditText) findViewById(R.id.et_login_code_one);
        etCodeTwo = (EditText) findViewById(R.id.et_login_code_two);
        etCodeThree = (EditText) findViewById(R.id.et_login_code_three);
        etCodeFour = (EditText) findViewById(R.id.et_login_code_four);
        etCodeFive = (EditText) findViewById(R.id.et_login_code_five);
        etCodeSix = (EditText) findViewById(R.id.et_login_code_six);
        codes = new EditText[]{etCodeOne, etCodeTwo, etCodeThree, etCodeFour, etCodeFive, etCodeSix};


    }

    /**
     * 展示验证码
     */
    private void showCode() {
        for (int i = 0; i < codes.length; i++) {
            final int j = i;
            codes[j].addTextChangedListener(new TextWatcher() {
                private CharSequence temp;

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    temp = s;
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (temp.length() == 1 && j >= 0 && j < codes.length - 1) {
                        codes[j + 1].setFocusable(true);
                        codes[j + 1].setFocusableInTouchMode(true);
                        codes[j + 1].requestFocus();
                    }

                    if (temp.length() == 0) {
                        if (j >= 1) {
                            codes[j - 1].setFocusable(true);
                            codes[j - 1].setFocusableInTouchMode(true);
                            codes[j - 1].requestFocus();
                        }
                    }
                    checkCode();
                }
            });

            codes[j].setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (TextUtils.isEmpty(codes[j].getText())) {
                            if (j >= 1) {
                                codes[j - 1].setFocusable(true);
                                codes[j - 1].setFocusableInTouchMode(true);
                                codes[j - 1].requestFocus();
                            }
                        }
                    }
                    return false;
                }
            });

            codes[j].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
//                    if (hasFocus) {
//                        v.setBackgroundResource(R.drawable.bkg_login_text_black);
//                    } else {
//                        if (((EditText) v).getText().length() > 0) {
//                            v.setBackgroundResource(R.drawable.bkg_login_text_black);
//                        } else {
//                            v.setBackgroundResource(R.drawable.bkg_login_text_white);
//                        }
//                    }
                }
            });

        }
    }

    /**
     * 校验验证码
     */
    private void checkCode() {
        //不为空 并且 为4位
        if (!TextUtils.isEmpty(etCodeOne.getText().toString()) && !TextUtils.isEmpty(etCodeTwo.getText().toString()) && !TextUtils.isEmpty(
                etCodeThree.getText().toString()) && !TextUtils.isEmpty(etCodeFour.getText().toString()) && !TextUtils.isEmpty(etCodeFive.getText().toString()) && !TextUtils.isEmpty(etCodeSix.getText().toString()) &&
                (etCodeOne.getText().toString() + etCodeTwo.getText().toString() + etCodeThree.getText().toString() + etCodeFour.getText().toString() + etCodeFive.getText().toString() + etCodeSix.getText().toString()).length() == 6) {
//            //关闭软键盘
//            InputMethodUtil.hiddenInputMethod(context, this.getWindow().peekDecorView());
//            showProgressDialogCancelable("登录中");
//            loginPresenter.login(etPhone.getText().toString().replace(" ", ""),
//                    etCodeOne.getText().toString() + etCodeTwo.getText().toString() + etCodeThree.getText().toString() + etCodeFour.getText().toString());

            // 跳转界面到修改密码

            Toast.makeText(this, "" + etCodeOne.getText().toString() + etCodeTwo.getText().toString() + etCodeThree.getText().toString() + etCodeFour.getText().toString()
                    + etCodeFive.getText().toString() + etCodeSix.getText().toString(), Toast.LENGTH_SHORT).show();


//            请求接口判断支付密码是否正确

            if (pwd.equals("" + etCodeOne.getText().toString() + etCodeTwo.getText().toString() + etCodeThree.getText().toString() + etCodeFour.getText().toString()
                    + etCodeFive.getText().toString() + etCodeSix.getText().toString())) {
                LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                //请求接口
                Map<String, String> map = new HashMap<>();
                map.put("fundId", loginResponseBean.getFundId());
                map.put("payPassword", pwd);
                mPresenter.setNewPassword(map);
            }


        }
    }

    @Override
    public void setNewPasswordSuccess(AgainPwdBean againPwdBean) {
        if (againPwdBean != null) {
            if (againPwdBean.isResetComplete()) {
                Toast.makeText(this, "重置成功！", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public void setNewPasswordFailed() {

    }
}
