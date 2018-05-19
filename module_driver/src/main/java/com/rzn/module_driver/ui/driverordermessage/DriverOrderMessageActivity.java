package com.rzn.module_driver.ui.driverordermessage;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.OrederInfo;
import com.zyhealth.expertlib.utils.MLog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverOrderMessageActivity extends MVPBaseActivity<DriverOrderMessageContract.View, DriverOrderMessagePresenter> implements DriverOrderMessageContract.View {

    private TextView tvStartPost;
    private RadioButton rbOne;
    private RadioButton rbTwo;
    private RadioButton rbThree;
    private EditText etAddress;
    private LinearLayout llMoreAddress;
    private TextView tvTimeStart;
    private TextView tvTimeEnd;
    private LinearLayout llMoreTime;
    private Calendar showDate = Calendar.getInstance();   //初始化时间选择器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_orders_message);
        initViews();
        initListener();
        mPresenter.onCreate();
//        showLoading(false,"");

    }


    private void initListener() {
        tvStartPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                mPresenter.supplementOrderInfo(map);
            }
        });
        rbOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rbTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rbThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        llMoreAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        llMoreTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出系统时间选择器
                showDateDialog(1);//tab   1 代表开始时间   2代表结束的时间
            }
        });
        tvTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void showDateDialog(final int tab) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate.set(Calendar.YEAR, year);
                showDate.set(Calendar.MONTH, monthOfYear);
                showDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (tab == 1) {
                    tvTimeStart.setText(DateFormat.format("yyyy-MM-dd", showDate));
                } else if (tab == 2) {
                    tvTimeEnd.setText(DateFormat.format("yyyy-MM-dd", showDate));
                }
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void initViews() {
        setTitle("完善接单信息");
        tvStartPost = (TextView) findViewById(R.id.tv_start_post);
        rbOne = (RadioButton) findViewById(R.id.rb_one);
        rbTwo = (RadioButton) findViewById(R.id.rb_two);
        rbThree = (RadioButton) findViewById(R.id.rb_three);
        etAddress = (EditText) findViewById(R.id.et_address);
        llMoreAddress = (LinearLayout) findViewById(R.id.ll_more_address);
        tvTimeStart = (TextView) findViewById(R.id.tv_time_start);
        tvTimeEnd = (TextView) findViewById(R.id.tv_time_end);
        llMoreTime = (LinearLayout) findViewById(R.id.ll_more_time);

        rbOne.setVisibility(View.GONE);
        rbTwo.setVisibility(View.GONE);
        rbThree.setVisibility(View.GONE);

    }


    @Override
    public void setOrderInfo(List<OrederInfo> list) {
        MLog.e(list.size() + "--------");
        if (list.size() == 1) {
            rbOne.setVisibility(View.VISIBLE);
            rbOne.setText(list.get(0).getKindName());
        } else if (list.size() == 2) {
            rbOne.setVisibility(View.VISIBLE);
            rbTwo.setVisibility(View.VISIBLE);
            rbOne.setText(list.get(0).getKindName());
            rbTwo.setText(list.get(1).getKindName());
        } else {
            rbOne.setVisibility(View.GONE);
            rbTwo.setVisibility(View.GONE);
            rbThree.setVisibility(View.GONE);
        }
    }
}
