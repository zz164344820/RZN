package com.rzn.module_main.ui.personalinfo;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.EncodeUtils;
import com.rzn.commonbaselib.bean.ImagePath;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonParseUtils;
import com.rzn.commonbaselib.utils.PicturePressUtil;
import com.rzn.commonbaselib.utils.SelectStatePopWindow;
import com.rzn.module_main.R;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.net.GenericsCallback;
import com.zyhealth.expertlib.net.JsonGenericsSerializator;
import com.zyhealth.expertlib.net.OkHttpLoader;
import com.zyhealth.expertlib.utils.GlideUtils;
import com.zyhealth.expertlib.utils.MLog;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import okhttp3.Call;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalInfoActivity extends MVPBaseActivity<PersonalInfoContract.View, PersonalInfoPresenter> implements PersonalInfoContract.View {
    TextView tv_save;
    RadioGroup  rg_sex;
    EditText ed_name;
    ImageView iv_header;
    LinearLayout rootView;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    final SelectStatePopWindow[] window = new SelectStatePopWindow[1];
    ImagePath imagePath;
    UserInfo userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_person_center);
        mPresenter.onCreate();
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("个人信息");
     tv_save=(TextView)    findViewById(R.id.tv_save);
     rg_sex =(RadioGroup) findViewById(R.id.rg_sex);
     ed_name=(EditText) findViewById(R.id.ed_name);
     iv_header=(ImageView) findViewById(R.id.iv_header);
     rootView=(LinearLayout) findViewById(R.id.rootView);
     iv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectPic(window);
            }
        });
     tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userInfo!=null){
                    userInfo.setName(ed_name.getText().toString().trim());
                    if(imagePath!=null){
                        userInfo.setPic(imagePath.getFileName());
                    }
                   if(rg_sex.getCheckedRadioButtonId()==R.id.cb_boy) {
                       userInfo.setSex("1");
                   }else{
                       userInfo.setSex("0");
                   }
                    mPresenter.setUserInfo(userInfo);
                }

            }
        });
    }

    @Override
    public void setInfo(UserInfo userInfo) {
        this.userInfo =userInfo;
        ed_name.setText(userInfo.getName());
        GlideUtils.loadImageRound(this, EncodeUtils.urlDecode(userInfo.getPic()),iv_header,60);
        if("0".equals(userInfo.getSex())){
            rg_sex.check(R.id.cb_gril);
        }else{
            rg_sex.check(R.id.cb_boy);
        }
    }

    @Override
    public void savePic() {
        if(imagePath!=null){
            LoginResponseBean bean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
            bean.setPic(imagePath.getImgUrl());
            FileSaveUtils.fileSaveObject(bean,"loginBean");
        }
    }

    private void showSelectPic(final SelectStatePopWindow[] window) {
        window[0] = new SelectStatePopWindow(this, R.layout.pop_bottom, new View.OnClickListener() {
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

        window[0].showAtLocation(rootView, Gravity.BOTTOM, 10, 10);
    }



    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                Bitmap bm = PicturePressUtil.getimage(resultList.get(0).getPhotoPath());
                String one = saveBitmapToSDCard(bm, "one");
                uploadImage(new File(one));

            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(PersonalInfoActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };



    /**
     * 保存bitmap到SD卡
     *
     * @param bitmap
     * @param imagename
     */
    private String saveBitmapToSDCard(Bitmap bitmap, String imagename) {
        File file = new File(this.getExternalCacheDir(), imagename + ".png");
        //        String path = "/sdcard/" + "img-" + imagename + ".jpg";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            if (fos != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
                fos.close();
            }

            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void uploadImage(File file) {

        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("type", "0");
        OkHttpUtils.post()//
                .addFile("file", file.getName(), file)//
                .url(OkHttpLoader.BASEURL + "/handler/upFile")//farmHand/  todo
                .params(bodyMap)//
                .build()//
                .execute(new GenericsCallback<ResponseBean>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(ResponseBean response, int id) {
                        imagePath = GsonParseUtils.GsonToBean(response.getResult(), ImagePath.class);
                        MLog.e(imagePath.getImgUrl());
                        GlideUtils.loadImageRound(PersonalInfoActivity.this, EncodeUtils.urlDecode(imagePath.getImgUrl()),iv_header,40);

                    }
                });
    }

}
