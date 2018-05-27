package com.rzn.module_driver.ui.driver_identification;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonParseUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.commonbaselib.utils.SelectStatePopWindow;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.DriverIdentBean;
import com.rzn.module_driver.ui.bean.ImagePath;
import com.rzn.module_driver.ui.bean.SelectWorkTypeBean;
import com.rzn.module_driver.ui.bean.WorkTypeBean;
import com.rzn.module_driver.ui.bean.WorkTypeObjBean;
import com.rzn.module_driver.ui.drivermaksure.DriverMakeSureActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.net.GenericsCallback;
import com.zyhealth.expertlib.net.JsonGenericsSerializator;
import com.zyhealth.expertlib.net.OkHttpLoader;
import com.zyhealth.expertlib.utils.GlideUtils;
import com.zyhealth.expertlib.utils.MLog;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import okhttp3.Call;

import static com.zyhealth.expertlib.net.OkHttpLoader.gson;


/**
 * MVPPlugin
 * 机手认证信息界面
 * 邮箱 784787081@qq.com
 */
@Route(path = "/driver/driverident")
public class Driver_identificationActivity extends MVPBaseActivity<Driver_identificationContract.View, Driver_identificationPresenter> implements Driver_identificationContract.View {

    private static final int IMAGE = 1;

    private LinearLayout llRootView, ll_jobOrderType2;
    private EditText etName;
    private EditText etIdent;
    private TextView tvData;
    private EditText etPhone;
    private TextView etWorkTab, tv_work_tab2;
    private EditText etCarTab;
    private EditText etCarNumber;
    private EditText etFromHome;
    private EditText tv_year;
    private TextView tvCommit;
    private ImageView ivPhotoCars, iv_addOrderType, iv_subtractOrderType;
    private ImageView ivPhotoCar;
    private String fag;
    private TextView tvWorkTime;
    private Calendar showDate = Calendar.getInstance();   //初始化时间选择器
    private TextView tvWorkTimeNow;
    private ImageView ivCarPhotoOne;
    private ImageView ivCarPhotoTwo;
    private String kind;
    private String kindType;
    private String kindTypeId;
    private String unitPrice;
    private RadioButton cbBoy;
    private RadioButton cbGril;
    private String flag;
    private WorkTypeBean workTypeBean;
    SelectWorkTypeBean workType1;
    SelectWorkTypeBean workType2;
    List<File> listFils = new ArrayList<>();
    private String setting;
    int showMoreJobType = 0;
    String handlerId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_driver_attestation);
        setting = getIntent().getStringExtra("setting");
        ButterKnife.bind(this);
        mPresenter.onCreate();
        initViews();
        initData();
        initListener();
    }

    private void initData() {
        if ("setting".equals(setting)) {
            LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
            Map<String, String> map = new HashMap<>();
            map.put("handlerId", loginResponseBean.getHandlerId());
            mPresenter.getDriverMessage(map);
        }
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
                    tvWorkTime.setText(DateFormat.format("yyyy-MM", showDate));
                } else if (tab == 2) {
                    tvWorkTimeNow.setText(DateFormat.format("yyyy-MM", showDate));
                } else if (tab == 3) {
                    tvData.setText(DateFormat.format("yyyy-MM-dd", showDate));
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

        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出系统时间选择器
                showDateDialog(3);
            }
        });

        iv_addOrderType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_jobOrderType2.setVisibility(View.VISIBLE);
                iv_addOrderType.setVisibility(View.INVISIBLE);
            }
        });

        iv_subtractOrderType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_jobOrderType2.setVisibility(View.GONE);
                iv_addOrderType.setVisibility(View.VISIBLE);
                workType2 = null;
                tv_work_tab2.setText("");
            }
        });

        tv_work_tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMoreJobType = 1;
                mPresenter.getJobTypes();
            }
        });
        etWorkTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreJobType = 0;
                mPresenter.getJobTypes();
            }
        });


        tvCommit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (isEmpty()) {
                    if (cbBoy.isChecked()) {
                        flag = "1";

                    } else if (cbGril.isChecked()) {
                        flag = "2";
                    }
                    if (workType1 == null) {
                        ToastUtils.showShort("请选择作业类型");
                        return;
                    }
//                    tempList.add(workType1);
//                    if (workType1 != null) {
                    tempList.add(workType1);
                    if (workType1 != null) {
                        tempList.add(workType2);
                    }
                    LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");

                    Map<String, String> map = new HashMap<>();
                    map.put("userId", loginResponseBean.getUserId());
                    map.put("handlerId", handlerId);
                    map.put("name", etName.getText().toString());
                    map.put("sex", flag);
                    map.put("birthday", tvData.getText().toString().trim());
                    map.put("idNo", etCarNumber.getText().toString().trim());
                    map.put("mobile", etPhone.getText().toString().trim());
                    map.put("icon", "");
                    map.put("startDate", tvWorkTime.getText().toString().trim());
                    map.put("endDate", tvWorkTimeNow.getText().toString().trim());
                    map.put("years", tv_year.getText().toString().trim());
                    map.put("carType", etCarTab.getText().toString().trim());
                    map.put("carNo", etCarNumber.getText().toString().trim());
                    map.put("belongs", etFromHome.getText().toString());
                    map.put("kindTypeDetail", gson.toJson(tempList));
                    map.put("carPic1", onePath);
                    map.put("carPic2", twoPath);
                    map.put("machinePic1", threePath);
                    map.put("machinePic2", fourPath);
                    mPresenter.pushDriverMessage(map);

                } else {
                    ToastUtils.showShort("请完善全部信息");
                }
            }
        });

        final SelectStatePopWindow[] window = new SelectStatePopWindow[1];
        //上传图片机手驾驶证
        findViewById(R.id.rl_photo_cars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fag = "one";
                showSelectPic(window);
            }
        });

        //上传图片机手驾驶证
        findViewById(R.id.rl_photo_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fag = "two";
                showSelectPic(window);
            }
        });
        //上传农机照片
        findViewById(R.id.rl_car_photo_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fag = "three";
                showSelectPic(window);
            }
        });
        //上传农机照片
        findViewById(R.id.rl_car_photo_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fag = "four";
                showSelectPic(window);
            }
        });


    }

    private boolean isEmpty() {
        return !TextUtils.isEmpty(etName.getText()) &&
                !TextUtils.isEmpty(etIdent.getText()) &&
                !TextUtils.isEmpty(tvData.getText()) &&
                !TextUtils.isEmpty(etPhone.getText()) &&
                !TextUtils.isEmpty(etWorkTab.getText()) &&
                !TextUtils.isEmpty(etCarTab.getText()) &&
                !TextUtils.isEmpty(etCarNumber.getText()) &&
                !TextUtils.isEmpty(etFromHome.getText()) &&
                !TextUtils.isEmpty(onePath) &&
                !TextUtils.isEmpty(threePath) &&
                !TextUtils.isEmpty(tvWorkTime.getText().toString()) &&
                !TextUtils.isEmpty(tvWorkTimeNow.getText().toString());
    }

    private void showSelectPic(final SelectStatePopWindow[] window) {
        window[0] = new SelectStatePopWindow(Driver_identificationActivity.this, R.layout.pop_bottom, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.camera) {
                    GalleryFinal.openCamera(REQUEST_CODE_CAMERA, mOnHanlderResultCallback);
                    window[0].dismiss();
                } else if (v.getId() == R.id.tv_photo) {
                    int size = 1;
                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, size, mOnHanlderResultCallback);
                    window[0].dismiss();
                }

            }
        });

        window[0].showAtLocation(llRootView, Gravity.BOTTOM, 10, 10);
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
        tvData = (TextView) findViewById(R.id.tv_date);
        //联系电话
        etPhone = (EditText) findViewById(R.id.et_phone);
        //作业类型
        etWorkTab = (TextView) findViewById(R.id.et_work_tab);
        tv_work_tab2 = (TextView) findViewById(R.id.tv_work_tab2);
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

        iv_addOrderType = (ImageView) findViewById(R.id.iv_addOrderType);
        iv_subtractOrderType = (ImageView) findViewById(R.id.iv_subtractOrderType);
        ll_jobOrderType2 = (LinearLayout) findViewById(R.id.ll_jobOrderType2);

        tv_year = (EditText) findViewById(R.id.tv_year);

        //点击上传机手驾照
        ivPhotoCars = (ImageView) findViewById(R.id.iv_photo_cars);
        ivPhotoCar = (ImageView) findViewById(R.id.iv_photo_car);

        //点击上传农机驾照
        ivCarPhotoOne = (ImageView) findViewById(R.id.iv_car_photo_one);
        ivCarPhotoTwo = (ImageView) findViewById(R.id.iv_car_photo_two);
        llRootView = (LinearLayout) findViewById(R.id.ll_rootView);
        //男女
        cbBoy = (RadioButton) findViewById(R.id.cb_boy);
        cbGril = (RadioButton) findViewById(R.id.cb_gril);


//        if ("setting".equals(setting)) {
//            etName.setText();
//            etIdent.setText();
//            tvData.setText();
//            etPhone.setText();
//            etWorkTab.setText();
//            etCarTab.setText();
//            etCarNumber.setText();
//            etFromHome.setText();
//            tvWorkTime.setText();
//            tvWorkTimeNow.setText();
//            tv_year.setText();
//
//        }


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
            File file = new File(imagePath);
            String FileName = file.getName();

            MLog.e(imagePath + "-----" + FileName);
            showImage(imagePath);
            c.close();
        }
    }

    String onePath;
    String twoPath;
    String threePath;
    String fourPath;

    private void showImage(String imagePath) {
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        if ("one".equals(fag)) {
            ivPhotoCars.setImageBitmap(bm);
            ivPhotoCars.setVisibility(View.VISIBLE);
            //onePath = imagePath;
            uploadImage(new File(imagePath), 1, "1");
        } else if ("two".equals(fag)) {
            // twoPath = imagePath;
            uploadImage(new File(imagePath), 2, "1");
            ivPhotoCar.setImageBitmap(bm);
            ivPhotoCar.setVisibility(View.VISIBLE);
        } else if ("three".equals(fag)) {
            // threePath = imagePath;
            uploadImage(new File(imagePath), 3, "2");
            ivCarPhotoOne.setImageBitmap(bm);
            ivCarPhotoOne.setVisibility(View.VISIBLE);
        } else if ("four".equals(fag)) {
            // fourPath = imagePath;
            uploadImage(new File(imagePath), 4, "2");
            ivCarPhotoTwo.setImageBitmap(bm);
            ivCarPhotoTwo.setVisibility(View.VISIBLE);
        }

    }


    public void uploadImage(File file, final int pos, String type) {

        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("type", type);

        OkHttpUtils.post()//
                .addFile("file", file.getName(), file)//
                .url(OkHttpLoader.BASEURL + "farmHand/handler/upFile")
                .params(bodyMap)//
                .build()//
                .execute(new GenericsCallback<ResponseBean>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(ResponseBean response, int id) {
                        ImagePath imagePath = GsonParseUtils.GsonToBean(response.getResult(), ImagePath.class);
                        MLog.e(imagePath.getFileName());
                        if (pos == 1) {
                            onePath = imagePath.getFileName();
                        } else if (pos == 2) {
                            twoPath = imagePath.getFileName();
                        } else if (pos == 3) {
                            threePath = imagePath.getFileName();
                        } else if (pos == 4) {
                            fourPath = imagePath.getFileName();
                        }

                    }
                });
    }


    //提交信息成功
    @Override
    public void pushDriverMessageSuccess() {
        //机手信息提交成功跳转下一界面
        startActivity(new Intent(this, DriverMakeSureActivity.class));
        finish();
    }

    //提交信息失败
    @Override
    public void pushDriverMessageFaile() {
        Toast.makeText(this, "信息提交失败", Toast.LENGTH_SHORT).show();

    }


    List<SelectWorkTypeBean> tempList = new ArrayList<>();

    @Override
    public void showPopWindow_SelectJobTypes(final List<WorkTypeBean> list) {
        //弹出选择框
        //弹出选择作业类型弹窗
        SendPopUpWindow sendPopUpWindow = new SendPopUpWindow(this, list);
        sendPopUpWindow.setOnListener(new SendPopUpWindow.OnClickListener() {
            @Override
            public void onClick(int position, int typePosition) {


//                if (showMoreJobType == 0) {
                if (showMoreJobType == 0) {
                    etWorkTab.setText(list.get(position).getKindName() + "    " + list.get(position).getTypeArray().get(typePosition).getTypeName());
                    workType1 = new SelectWorkTypeBean();
                    workType1.setKindId(list.get(position).getKindId());
                    workType1.setKindName(list.get(position).getKindName());
                    workType1.setKindTypeId(list.get(position).getTypeArray().get(typePosition).getTypeId());
                    workType1.setKindTypeName(list.get(position).getTypeArray().get(typePosition).getTypeName());
                    workType1.setUnitPrice(list.get(position).getTypeArray().get(typePosition).getTypeUnitPrice());
                } else {
                    tv_work_tab2.setText(list.get(position).getKindName() + "    " + list.get(position).getTypeArray().get(typePosition).getTypeName());
                    workType2 = new SelectWorkTypeBean();
                    workType2.setKindId(list.get(position).getKindId());
                    workType2.setKindName(list.get(position).getKindName());
                    workType2.setKindTypeId(list.get(position).getTypeArray().get(typePosition).getTypeId());
                    workType2.setKindTypeName(list.get(position).getTypeArray().get(typePosition).getTypeName());
                    workType2.setUnitPrice(list.get(position).getTypeArray().get(typePosition).getTypeUnitPrice());
                }

            }
        });
        if (sendPopUpWindow.isShowing()) {
            return;
        }
        sendPopUpWindow.showAtLocation(etWorkTab, Gravity.BOTTOM, 0, 0);


    }

    @Override
    public void getDriverMessageSuccess(DriverIdentBean bean) {
        if ("setting".equals(setting)) {
            handlerId = bean.getHandlerId();
            etName.setText(bean.getName());
            etIdent.setText(bean.getIdNo());
            tvData.setText(bean.getBirthday());
            etPhone.setText(bean.getMobile());
            etCarTab.setText(bean.getCarType());
            etCarNumber.setText(bean.getCarNo());
            etFromHome.setText(bean.getBelongs());
            tvWorkTime.setText(bean.getStartDate());
            tvWorkTimeNow.setText(bean.getEndDate());
            tv_year.setText(bean.getYears());

            if ("2".equals(bean.getSex())) {
                cbGril.setChecked(true);
            } else if ("1".equals(bean.getSex())) {
                cbBoy.setChecked(true);
            }


            if (bean.getHandlerKindTypeArray().size() == 2) {
                etWorkTab.setText(bean.getHandlerKindTypeArray().get(0).getKindName() + " " + bean.getHandlerKindTypeArray().get(0).getKindTypeName());
                ll_jobOrderType2.setVisibility(View.VISIBLE);
                iv_addOrderType.setVisibility(View.INVISIBLE);
                tv_work_tab2.setText(bean.getHandlerKindTypeArray().get(1).getKindName() + " " + bean.getHandlerKindTypeArray().get(1).getKindTypeName());
            } else if (bean.getHandlerKindTypeArray().size() == 1) {
                etWorkTab.setText(bean.getHandlerKindTypeArray().get(0).getKindName() + " " + bean.getHandlerKindTypeArray().get(0).getKindTypeName());
            }


            if (TextUtils.isEmpty(bean.getCarPic1())) {
                ivPhotoCar.setVisibility(View.GONE);
            } else {
                ivPhotoCar.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(bean.getCarPic2())) {
                ivPhotoCars.setVisibility(View.GONE);
            } else {
                ivPhotoCars.setVisibility(View.VISIBLE);
            }


            if (TextUtils.isEmpty(bean.getMachinePic1())) {
                ivCarPhotoOne.setVisibility(View.GONE);
            } else {
                ivCarPhotoOne.setVisibility(View.VISIBLE);
            }
            if (TextUtils.isEmpty(bean.getMachinePic2())) {
                ivCarPhotoTwo.setVisibility(View.GONE);
            } else {
                ivCarPhotoTwo.setVisibility(View.VISIBLE);
            }

//            ivPhotoCars.setVisibility(View.VISIBLE);
            onePath = bean.getCarPic1();
            twoPath = bean.getCarPic2();
            threePath = bean.getMachinePic1();
            fourPath = bean.getMachinePic2();
            GlideUtils.loadImageView(this, bean.getCarPic1(), ivPhotoCar);
            GlideUtils.loadImageView(this, bean.getCarPic2(), ivPhotoCars);
            GlideUtils.loadImageView(this, bean.getMachinePic1(), ivCarPhotoOne);
            GlideUtils.loadImageView(this, bean.getMachinePic2(), ivCarPhotoTwo);


        }

    }


    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                showImage(resultList.get(0).getPhotoPath());

            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(Driver_identificationActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };
}
