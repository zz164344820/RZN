package com.rzn.module_driver.ui.driverordermessage;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonParseUtils;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.OrederInfo;
import com.rzn.module_driver.ui.bean.PlaceBean;
import com.zyhealth.expertlib.utils.MLog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverOrderMessageActivity extends MVPBaseActivity<DriverOrderMessageContract.View, DriverOrderMessagePresenter> implements DriverOrderMessageContract.View, OnAddressSelectedListener {

    private TextView tvStartPost;
    private CheckBox rbOne;
    private CheckBox rbTwo;
    private CheckBox rbThree;
    private TextView tv_address, tv_address2;
    private LinearLayout llMoreAddress, ll_time2;
    private TextView tvTimeStart, tvTimeStart2;
    private TextView tvTimeEnd, tvTimeEnd2;
    private LinearLayout llMoreTime;
    private Calendar showDate = Calendar.getInstance();   //初始化时间选择器
    BottomDialog bottomDialog;
    Province province, province2;
    City city, city2;
    County county, county2;
    private CheckBox cbWorkTime;
    private CheckBox cbWorkAres;
    private EditText etMessage;
    private RelativeLayout rgAll;
    private List<OrederInfo> orderList;
    List<OrederInfo> tempList = new ArrayList<>();//入参的作业类型集合
    private int num;
    private TextView tvOne;
    private TextView tvTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_orders_message);
        initViews();
        initListener();
        mPresenter.onCreate();

    }

    int flag = 0;

    private void initListener() {

        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                bottomDialog.show();
            }
        });
        tv_address2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                bottomDialog.show();
            }
        });

        tvStartPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new HashMap<>();
                if (province == null || city == null || county == null) {
                    ToastUtils.showShort("作业地址不能为空");
                    return;
                }
                List<PlaceBean> list = setPlaceList();
                map.put("taskPlaceDetail", GsonParseUtils.GsonString(list));
                LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                map.put("handlerId", loginResponseBean.getHandlerId());//机手id
                // map.put("handlerInfoId", "");//机手id详情
                tempList.clear();
                if (rbOne.isChecked()) {
                    tempList.add(orderList.get(0));
                }
                if (rbTwo.isChecked()) {
                    tempList.add(orderList.get(1));
                }
                if (tempList.size() == 0 && !cbWorkAres.isChecked()) {
                    ToastUtils.showShort("请选择作业类型");
                    return;
                }
                if(!cbWorkTime.isChecked()){
                    if (TextUtils.isEmpty(tvTimeStart.getText().toString()) || TextUtils.isEmpty(tvTimeEnd.getText().toString())) {
                        ToastUtils.showShort("作业开始时间或结束时间不能为空");
                        return;
                    } else if (!TextUtils.isEmpty(tvTimeStart2.getText().toString()) && TextUtils.isEmpty(tvTimeEnd2.getText().toString())) {
                        ToastUtils.showShort("选填结束时间不能为空");
                        return;
                    } else if (TextUtils.isEmpty(tvTimeStart2.getText().toString()) && !TextUtils.isEmpty(tvTimeEnd2.getText().toString())) {
                        ToastUtils.showShort("选填开始时间不能为空");
                        return;
                    }
                }
                map.put("kindTypeDetail", GsonParseUtils.GsonString(tempList));//作业类型数组
                map.put("timeStart1", tvTimeStart.getText().toString());//開始时间
                map.put("timeEnd1", tvTimeEnd.getText().toString());//结束时间
                map.put("timeStart2", tvTimeStart2.getText().toString());//开始时间
                map.put("timeEnd2", tvTimeEnd2.getText().toString());//结束时间
                if (cbWorkAres.isChecked()) {
                    map.put("anywhere", "1");//是否随地作业 0否1是
                } else {
                    map.put("anywhere", "0");//是否随地作业 0否1是
                }
                if (cbWorkTime.isChecked()) {
                    map.put("anytime", "1");//是否随时作业 0否1是
                } else {
                    map.put("anytime", "0");//是否随时作业 0否1是
                }
                map.put("remark", etMessage.getText().toString());//补充说明

                mPresenter.supplementOrderInfo(map);
            }
        });
        llMoreAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_address2.setVisibility(View.VISIBLE);
                llMoreAddress.setVisibility(View.GONE);
            }
        });
        llMoreTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_time2.setVisibility(View.VISIBLE);
                llMoreTime.setVisibility(View.GONE);
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
                showDateDialog(2);//tab   1 代表开始时间   2代表结束的时间
            }
        });

        tvTimeStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出系统时间选择器
                showDateDialog(3);//tab   1 代表开始时间   2代表结束的时间
            }
        });

        tvTimeEnd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(4);//tab   1 代表开始时间   2代表结束的时间
            }
        });

        rbOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbOne.isChecked()) {
                    tvOne.setVisibility(View.VISIBLE);
                    tvOne.setText(orderList.get(0).getUnitPrice() + "元/亩");
                } else {
                    tvOne.setVisibility(View.GONE);
                }

            }
        });
        rbTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbTwo.isChecked()) {
                    tvTwo.setVisibility(View.VISIBLE);
                    tvTwo.setText(orderList.get(1).getUnitPrice() + "元/亩");
                } else {
                    tvTwo.setVisibility(View.GONE);
                }

            }
        });


    }

    private List<PlaceBean> setPlaceList() {
        List<PlaceBean> list = new ArrayList<>();
        PlaceBean placeBean = new PlaceBean();
        placeBean.setProvinceCode(province.getId() + "");
        placeBean.setProvinceName(province.getName());
        placeBean.setCityCode(city.getId() + "");
        placeBean.setCityName(city.getName());
        placeBean.setAreaCode(county.getId() + "");
        placeBean.setAreaName(county.getName());
        list.add(placeBean);

        if (province2 != null && city2 != null && county2 != null) {
            PlaceBean placeBean2 = new PlaceBean();
            placeBean2.setProvinceCode(province2.getId() + "");
            placeBean2.setProvinceName(province2.getName());
            placeBean2.setCityCode(city2.getId() + "");
            placeBean2.setCityName(city2.getName());
            placeBean2.setAreaCode(county2.getId() + "");
            placeBean2.setAreaName(county2.getName());
            list.add(placeBean2);
        }
        return list;
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
                } else if (tab == 3) {
                    tvTimeStart2.setText(DateFormat.format("yyyy-MM-dd", showDate));
                } else if (tab == 4) {
                    tvTimeEnd2.setText(DateFormat.format("yyyy-MM-dd", showDate));
                }
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void initViews() {
        setTitle("完善接单信息");
        tvStartPost = (TextView) findViewById(R.id.tv_start_post);
        rbOne = (CheckBox) findViewById(R.id.rb_one);
        rbTwo = (CheckBox) findViewById(R.id.rb_two);
        rbThree = (CheckBox) findViewById(R.id.rb_three);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_address2 = (TextView) findViewById(R.id.tv_address2);
        llMoreAddress = (LinearLayout) findViewById(R.id.ll_more_address);
        tvTimeStart = (TextView) findViewById(R.id.tv_time_start);
        tvTimeStart2 = (TextView) findViewById(R.id.tv_time_start2);
        tvTimeEnd = (TextView) findViewById(R.id.tv_time_end);
        tvTimeEnd2 = (TextView) findViewById(R.id.tv_time_end2);
        llMoreTime = (LinearLayout) findViewById(R.id.ll_more_time);
        ll_time2 = (LinearLayout) findViewById(R.id.ll_time2);
        cbWorkTime = (CheckBox) findViewById(R.id.cb_work_time);
        cbWorkAres = (CheckBox) findViewById(R.id.cb_work_ares);
        etMessage = (EditText) findViewById(R.id.et_message);
        rgAll = (RelativeLayout) findViewById(R.id.rg_all);
        tvOne = (TextView) findViewById(R.id.tv_one);
        tvTwo = (TextView) findViewById(R.id.tv_two);


        rbOne.setVisibility(View.GONE);
        rbTwo.setVisibility(View.GONE);
        rbThree.setVisibility(View.GONE);
        bottomDialog = new BottomDialog(this);
        bottomDialog.setOnAddressSelectedListener(this);


    }


    @Override
    public void setOrderInfo(List<OrederInfo> list) {
        orderList = list;
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

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        if (flag == 0) {
            this.province = province;
            this.city = city;
            this.county = county;
            tv_address.setText(province.getName() + "  " + city.getName() + "  " + county.getName());
        } else {
            this.province2 = province;
            this.city2 = city;
            this.county2 = county;
            tv_address2.setText(province.getName() + "  " + city.getName() + "  " + county.getName());
        }
        bottomDialog.dismiss();

    }
}
