package com.rzn.module_farmer.ui.sendwork;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.SendWorkBean;
import com.rzn.module_farmer.bean.WorkTypeObjBean;
import com.rzn.module_farmer.ui.sendworksuccess.SendWorkSuccessActivity;

import java.util.Calendar;
import java.util.List;


/**
 * 发布作业
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendWorkActivity extends MVPBaseActivity<SendWorkContract.View, SendWorkPresenter> implements SendWorkContract.View {


    private EditText etPeople;
    private EditText etPhone;
    private EditText etHomeAddress;
    private TextView tvWorkTab;
    private TextView tvWorkAddress;
    private EditText etDetialAddress;
    private TextView tvStartTime;
    private TextView tvToTime;
    private TextView tvPrice;
    private EditText etDetial;
    private TextView tvConfim;
    private Calendar showDate = Calendar.getInstance();
    private LoginResponseBean loginResponseBean;
    private WorkTypeObjBean woekTypeObjBean;
    private EditText etWorkAreas;
    private CheckBox cbOne;
    private CheckBox cbTwo;
    private String farmerTaskId = "";
    private String flag="1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_release_assignments);
        mPresenter.onCreate();
        setTitle("发布作业需求");
        initViews();
        initListenter();
//        showLoading(false, "");
    }

    private void initListenter() {

        tvConfim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                //获取checkbox被选中的
                if (cbOne.isChecked()) {
                    //
                    flag = "1";
                } else if (cbTwo.isChecked()) {
                    //
                    flag = "2";
                } else {
                    //请选择后在提交
                }


               LoginResponseBean  loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                mPresenter.httpSendWork(loginResponseBean.getUserId(), farmerTaskId, "1",
                        "1", "1"
                        , "1", "1", "1", "1", "1", flag, "1", "1",
                        "1",
                       "1", "1", "1", "1", "1", "1", "1", "1"
                );
                /*if (!TextUtils.isEmpty(etPeople.getText().toString().trim()) &&
                        !TextUtils.isEmpty(etPhone.getText().toString().trim()) &&
                        !TextUtils.isEmpty(etHomeAddress.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvWorkTab.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvWorkAddress.getText().toString().trim()) &&
                        !TextUtils.isEmpty(etDetialAddress.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvStartTime.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvToTime.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvPrice.getText().toString().trim()) &&
                        !TextUtils.isEmpty(etDetial.getText().toString().trim())) {
                    mPresenter.httpSendWork("40289e9362bd29870162bd2b809c0002", farmerTaskId, etPeople.getText().toString().trim(),
                            etPhone.getText().toString().trim(), etHomeAddress.getText().toString().trim()
                            , "1", "1", "1", "1", etWorkAreas.getText().toString().trim(), flag, "1", tvStartTime.getText().toString().trim(),
                            tvToTime.getText().toString().trim(),
                            etDetialAddress.getText().toString().trim(), etDetial.getText().toString().trim(), "1", "1", "1", "1", "1", "1"
                    );
                }*/


            }
        });


        //作业开始时间
        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出时间选择器
                showDateDialog(1);
            }
        });
        //作业结束时间
        tvToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出时间选择器
                showDateDialog(2);
            }
        });
//        作业地址
        tvWorkAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //作业类型
        tvWorkTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出选择作业类型弹窗
                SendPopUpWindow sendPopUpWindow = new SendPopUpWindow(SendWorkActivity.this);
                if (sendPopUpWindow.isShowing()) {
                    return;
                }
                sendPopUpWindow.showAtLocation(tvWorkTab, Gravity.BOTTOM, 0, 0);
                //请求作业类型（文档24）
//                mPresenter.httpGetWorkType();
            }
        });
        //发布作业信息监听
    }

    private void showDateDialog(final int tab) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate.set(Calendar.YEAR, year);
                showDate.set(Calendar.MONTH, monthOfYear);
                showDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (tab == 1) {
                    tvStartTime.setText(DateFormat.format("yyyy-MM-dd", showDate));

                } else if (tab == 2) {
                    tvToTime.setText(DateFormat.format("yyyy-MM-dd", showDate));
                }
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void initViews() {
        etPeople = (EditText) findViewById(R.id.et_people);//联系人
        etPhone = (EditText) findViewById(R.id.et_phone);//电话号
        etHomeAddress = (EditText) findViewById(R.id.et_home_address);//家地址
        tvWorkTab = (TextView) findViewById(R.id.tv_work_tab);//作业类型
        etWorkAreas = (EditText) findViewById(R.id.et_work_areas);
        cbOne = (CheckBox) findViewById(R.id.cb_one);
        cbTwo = (CheckBox) findViewById(R.id.cb_two);
        tvWorkAddress = (TextView) findViewById(R.id.tv_work_address);//作业地点
        etDetialAddress = (EditText) findViewById(R.id.et_detial_address);//详细地址
        tvStartTime = (TextView) findViewById(R.id.tv_start_time);//作业开始时间
        tvToTime = (TextView) findViewById(R.id.tv_to_time);//作业结束时间
        tvPrice = (TextView) findViewById(R.id.tv_price);//作业价格
        etDetial = (EditText) findViewById(R.id.et_detial);//想对机手说些什么
        tvConfim = (TextView) findViewById(R.id.tv_confim);//确认发布


        //登录保存的信息
//        loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
    }

    /**
     * 发布作业成功
     */
    @Override
    public void sendSuccess(SendWorkBean sendWorkBean) {
        //发布订单成功
        startActivity(new Intent( this, SendWorkSuccessActivity.class));
        finish();
        Toast.makeText(this, "send", Toast.LENGTH_LONG).show();
    }





    /**
     * 获取作业类型成功
     */
    @Override
    public void getWorkTypeSuccess(List<WorkTypeObjBean> list) {


        //弹出选择作业类型弹窗
        SendPopUpWindow sendPopUpWindow = new SendPopUpWindow(this);
        if (sendPopUpWindow.isShowing()) {
            return;
        }
        sendPopUpWindow.showAtLocation(tvWorkTab, Gravity.BOTTOM, 0, 0);

        woekTypeObjBean = list.get(0);
        tvWorkTab.setText(woekTypeObjBean.getTypeName().toString());
        Toast.makeText(this, "chenggong", Toast.LENGTH_LONG).show();
    }

    /**
     * 获取作业类型失败
     */
    @Override
    public void getWorkTypeFailed() {

    }
}
