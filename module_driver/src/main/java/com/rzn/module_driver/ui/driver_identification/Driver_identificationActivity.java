package com.rzn.module_driver.ui.driver_identification;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.drivermaksure.DriverMakeSureActivity;

import java.util.Calendar;


/**
 * MVPPlugin
 * 机手认证信息界面
 * 邮箱 784787081@qq.com
 */

public class Driver_identificationActivity extends MVPBaseActivity<Driver_identificationContract.View, Driver_identificationPresenter> implements Driver_identificationContract.View {

    private static final int IMAGE = 1;
    private EditText etName;
    private EditText etIdent;
    private EditText etData;
    private EditText etPhone;
    private EditText etWorkTab;
    private EditText etCarTab;
    private EditText etCarNumber;
    private EditText etFromHome;
    private TextView tvCommit;
    private ImageView ivPhotoCars;
    private ImageView ivPhotoCar;
    private String fag;
    private TextView tvWorkTime;
    private Calendar showDate = Calendar.getInstance();   //初始化时间选择器
    private TextView tvWorkTimeNow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_attestation);
        mPresenter.onCreate();
        initViews();
        initListener();
    }

    /**
     * 系统时间选择器
     *
     * @param tab
     */
    private void showDateDialog(final int tab) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                showDate.set(Calendar.YEAR, year);
                showDate.set(Calendar.MONTH, monthOfYear);
                showDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (tab == 1) {
                    tvWorkTime.setText(DateFormat.format("yyyy-MM-dd", showDate));
                } else if (tab == 2) {
                    tvWorkTimeNow.setText(DateFormat.format("yyyy-MM-dd", showDate));
                }
            }
        }, showDate.get(Calendar.YEAR), showDate.get(Calendar.MONTH), showDate.get(Calendar.DAY_OF_MONTH)).show();
    }


    /**
     * 初始化监听
     */
    private void initListener() {
        tvWorkTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出系统时间选择器
                showDateDialog(1);//tab   1 代表开始时间   2代表结束的时间
            }
        });

        tvWorkTimeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出系统时间选择器
                showDateDialog(2);
            }
        });


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

                    //提交机手认证信息接口
                    mPresenter.pushDriverMessage("userid", "handlerId", etName.getText() + "", "", "", "",
                            "", "", "", "", "", "", "", "", "", "",
                            "", "");

                } else {
                    //跳转用到的
                    startActivity(new Intent(Driver_identificationActivity.this, DriverMakeSureActivity.class));

                }
            }
        });


        //上传图片机手驾驶证
        ivPhotoCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fag = "one";
                //调用相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });

        //上传图片机手驾驶证
        ivPhotoCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fag = "two";
                //调用相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
    }

    /**
     * 初始化布局
     */
    private void initViews() {
        setTitle("手机认证");
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
        //从业时间
        tvWorkTime = (TextView) findViewById(R.id.tv_work_time);
        //从业时间到什么时间
        tvWorkTimeNow = (TextView) findViewById(R.id.tv_work_time_now);

        //点击上传机手驾照
        ivPhotoCars = (ImageView) findViewById(R.id.iv_photo_cars);
        ivPhotoCar = (ImageView) findViewById(R.id.iv_photo_car);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    private void showImage(String imagePath) {
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        if ("one".equals(fag)) {
            ivPhotoCars.setImageBitmap(bm);
        } else if ("two".equals(fag)) {
            ivPhotoCar.setImageBitmap(bm);
        }

    }

    //提交信息成功
    @Override
    public void pushDriverMessageSuccess() {
        //机手信息提交成功跳转下一界面

    }

    //提交信息失败
    @Override
    public void pushDriverMessageFaile() {
        Toast.makeText(this, "信息提交失败", Toast.LENGTH_SHORT).show();

    }
}
