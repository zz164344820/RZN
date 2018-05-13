package com.rzn.module_driver.ui.driver_identification;

import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.net.OkHttpLoader;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class Driver_identificationPresenter extends BasePresenterImpl<Driver_identificationContract.View> implements Driver_identificationContract.Presenter {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void pushDriverMessage(String userId, String handlerId, String name,
                                  String sex, String birthday, String idNo,
                                  String mobile, String icon, String startDate,
                                  String endDate, String years, String carType,
                                  String carNo, String carPic1, String carPic2,
                                  String machinePic1, String machinePic2,
                                  String belongs) {

        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("handlerId", handlerId);
        map.put("name", name);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("idNo", idNo);
        map.put("mobile", mobile);
        map.put("icon", icon);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("years", years);
        map.put("carType", carType);
        map.put("carNo", carNo);
       // map.put("carPic1", carPic1);
      //  map.put("carPic2", carPic2);
       // map.put("machinePic1", machinePic1);
       // map.put("machinePic2", machinePic2);
        map.put("belongs", belongs);
        //http://1724l9l212.iask.in/farmHand/handler/updateSaveHandler
        //9：机手审核认证修改保存(机手)


        /*OkHttpUtils.post()//
                .addFile("carPic1", "messenger_01.png", file)//
                .addFile("carPic2", "test1.txt", file2)//
                .addFile("machinePic1", "test1.txt", file2)//
                .addFile("machinePic2", "test1.txt", file2)//
                .url(OkHttpLoader.BASEURL+"farmHand/handler/updateSaveHandler")
                .params(map)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });*/
       reqData(mContext, "farmHand/handler/updateSaveHandler", map, 111);//


    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 111:

                mView.pushDriverMessageSuccess();
                break;
        }

    }


}
