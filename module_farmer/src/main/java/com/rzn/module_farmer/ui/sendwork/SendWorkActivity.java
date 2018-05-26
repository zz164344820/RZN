package com.rzn.module_farmer.ui.sendwork;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.SendWorkBean;
import com.rzn.module_farmer.bean.WorkTypeBean;
import com.rzn.module_farmer.bean.WorkTypeObjBean;
import com.rzn.module_farmer.ui.sendworksuccess.SendWorkSuccessActivity;

import java.util.Calendar;
import java.util.List;

import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;


/**
 * 发布作业
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendWorkActivity extends MVPBaseActivity<SendWorkContract.View, SendWorkPresenter> implements SendWorkContract.View ,OnAddressSelectedListener{


    private EditText etPeople;
    private EditText etPhone;
    private EditText etHomeAddress;
    private TextView tvWorkTab;
    private TextView tvWorkAddress ;
    private EditText etDetialAddress;
    private TextView tvStartTime;
    private TextView tvToTime;
    private EditText tvPrice;
    private EditText etDetial;
    private TextView tvConfim;
    private Calendar showDate = Calendar.getInstance();
    private LoginResponseBean loginResponseBean;
    private WorkTypeObjBean woekTypeObjBean;
    private EditText etWorkAreas;
    private CheckBox cbOne;
    private CheckBox cbTwo;
    private String farmerTaskId = "";
    private String flag = "1";
    private String kind;
    private String kindType;
    private String kindTypeId;
    private String unitPrice;
    private ImageView iv_address;
    BottomDialog bottomDialog;

    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;

    Province province;//省
    County county;    //市
    City  city;       //县


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_release_assignments);
        mPresenter.onCreate();
        initViews();
        initListenter();
        initLocation();
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


                LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");

                if (!TextUtils.isEmpty(etPeople.getText().toString().trim()) &&
                        !TextUtils.isEmpty(etPhone.getText().toString().trim()) &&
                        !TextUtils.isEmpty(etHomeAddress.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvWorkTab.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvWorkAddress.getText().toString().trim()) &&
                        !TextUtils.isEmpty(etDetialAddress.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvStartTime.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvToTime.getText().toString().trim()) &&
                        !TextUtils.isEmpty(tvPrice.getText().toString().trim()) &&
                        !TextUtils.isEmpty(etDetial.getText().toString().trim())) {
                    mPresenter.httpSendWork(loginResponseBean.getUserId(), farmerTaskId, etPeople.getText().toString().trim(),
                            etPhone.getText().toString().trim(), etHomeAddress.getText().toString().trim()
                            , kind, kindType, kindTypeId, unitPrice, etWorkAreas.getText().toString().trim(), flag, "1", tvStartTime.getText().toString().trim(),
                            tvToTime.getText().toString().trim(),
                            etDetialAddress.getText().toString().trim(), etDetial.getText().toString().trim(),province,city,county
                    );
                } else {
                   ToastUtils.showShort("请完善作业信息!");
                }


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
                bottomDialog.show();
            }
        });
        //作业类型
        tvWorkTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求作业类型（文档24）
                mPresenter.httpGetWorkType();
            }
        });

        iv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationClient.startLocation();
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
                    tvStartTime.setText(DateFormat.format("yyyy-MM-dd", showDate));

                } else if (tab == 2) {
                    tvToTime.setText(DateFormat.format("yyyy-MM-dd", showDate));
                }
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void initViews() {
        setTitle("发布作业需求");
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
        tvPrice = (EditText) findViewById(R.id.tv_price);//作业价格
        etDetial = (EditText) findViewById(R.id.et_detial);//想对机手说些什么
        tvConfim = (TextView) findViewById(R.id.tv_confim);//确认发布
        iv_address = (ImageView) findViewById(R.id.iv_address);//定位
        bottomDialog = new BottomDialog(SendWorkActivity.this);
        bottomDialog.setOnAddressSelectedListener(this);
    }

    /**
     * 发布作业成功
     */
    @Override
    public void sendSuccess(SendWorkBean sendWorkBean) {
        //发布订单成功
        startActivity(new Intent(this, SendWorkSuccessActivity.class));
        finish();
    }


    /**
     * 获取作业类型成功
     */
    @Override
    public void getWorkTypeSuccess(final List<WorkTypeBean> list) {

        //弹出选择作业类型弹窗
        SendPopUpWindow sendPopUpWindow = new SendPopUpWindow(this, list);
        sendPopUpWindow.setOnListener(new SendPopUpWindow.OnClickListener() {
            @Override
            public void onClick(int position, int typePosition) {
                //获取作业类型
                kind = list.get(position).getKindId();
                kindType = list.get(position).getTypeArray().get(typePosition).getTypeId();
                kindTypeId = list.get(position).getTypeArray().get(typePosition).getKindId();
                unitPrice = list.get(position).getTypeArray().get(typePosition).getTypeUnitPrice();
                tvWorkTab.setText(list.get(position).getKindName() + "    " + list.get(position).getTypeArray().get(typePosition).getTypeName());
            }
        });
        if (sendPopUpWindow.isShowing()) {
            return;
        }
        sendPopUpWindow.showAtLocation(tvWorkTab, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        this.province = province;
        this.city=city;
        this.county =county;
        tvWorkAddress.setText(province.getName()+"  "+city.getName()+"  "+county.getName());
        bottomDialog.dismiss();

    }

    private void initLocation() {
        //声明AMapLocationClient类对象
        mLocationClient = new AMapLocationClient(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //单次定位
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                etHomeAddress.setText(aMapLocation.getDistrict()+aMapLocation.getStreet()+aMapLocation.getStreetNum());
            }
        });
        mLocationClient.setLocationOption(mLocationOption);

    }

}
