package com.rzn.module_driver.ui.driver_identification;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;


/**
 * MVPPlugin
 * 机手认证信息界面
 * 邮箱 784787081@qq.com
 */

public class Driver_identificationActivity extends MVPBaseActivity<Driver_identificationContract.View, Driver_identificationPresenter> implements Driver_identificationContract.View {


    private EditText etName;
    private EditText etIdent;
    private EditText etData;
    private EditText etPhone;
    private EditText etWorkTab;
    private EditText etCarTab;
    private EditText etCarNumber;
    private EditText etFromHome;
    private TextView tvCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_attestation);
        initViews();
        initListener();
    }


    /**
     * 初始化监听
     */
    private void initListener() {
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etName.getText()) &&
                        !TextUtils.isEmpty(etIdent.getText()) &&
                        !TextUtils.isEmpty(etData.getText()) &&
                        !TextUtils.isEmpty(etPhone.getText()) &&
                        !TextUtils.isEmpty(etWorkTab.getText()) &&
                        !TextUtils.isEmpty(etCarTab.getText()) &&
                        !TextUtils.isEmpty(etCarNumber.getText()) &&
                        !TextUtils.isEmpty(etFromHome.getText())
                        ) {


                    //请求接口提交订单,跳转下一页

                }
            }
        });
    }

    /**
     * 初始化布局
     */
    private void initViews() {
        //姓名
        etName = (EditText) findViewById(R.id.et_name);
        //身份证号码
        etIdent = (EditText) findViewById(R.id.et_ident);
        //出生日期
        etData = (EditText) findViewById(R.id.et_date);
        //联系电话
        etPhone = (EditText) findViewById(R.id.et_phone);
        //作业类型
        etWorkTab = (EditText) findViewById(R.id.et_work_tab);
        //车辆类型
        etCarTab = (EditText) findViewById(R.id.et_car_tab);
        //车牌号
        etCarNumber = (EditText) findViewById(R.id.et_car_number);
        //所属合作社
        etFromHome = (EditText) findViewById(R.id.et_from_home);
        //提交申请按钮
        tvCommit = (TextView) findViewById(R.id.tv_commit);

    }
}
