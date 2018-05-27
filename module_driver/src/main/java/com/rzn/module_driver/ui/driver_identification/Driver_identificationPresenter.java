package com.rzn.module_driver.ui.driver_identification;

import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_driver.ui.bean.DriverIdentBean;
import com.rzn.module_driver.ui.bean.WorkTypeBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.net.OkHttpLoader;
import com.zyhealth.expertlib.utils.MLog;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class Driver_identificationPresenter extends BasePresenterImpl<Driver_identificationContract.View> implements Driver_identificationContract.Presenter {
    File file1;
    File file2;
    File file3;
    File file4;


    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void getJobTypes() {
        mView.showLoading(false, "");
        reqData(mContext, "farmHand/farmerTask/queryKind", null, 124);

    }

    @Override
    public void uploadImage(List<File> files) {

        Map<String, String> headMap = new HashMap<>();
        Map<String, String> bodyMap = new HashMap<>();
        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        headMap.put("userId", loginResponseBean.getUserId());
        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                bodyMap.put("type", "1");
            } else {
                bodyMap.put("type", "2");
            }
            OkHttpUtils.post()//
                    .addFile("file", files.get(i).getName(), files.get(i))//
                    .url(OkHttpLoader.BASEURL + "farmHand/handler/upFile")
                    .params(bodyMap)//
                    .headers(headMap)//
                    .build()//
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            MLog.e(e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            MLog.e(response);
                        }
                    });
        }


    }

    @Override
    public void pushDriverMessage(final Map<String, String> map) {
        mView.showLoading(false, "");
        reqData(mContext, "farmHand/handler/updateSaveHandler", map, 111);
    }

    @Override
    public void getDriverMessage(Map<String, String> map) {
        reqData(mContext, "farmHand/handler/queryHandler", map, 218);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                HandlerIdBean HandlerIdBean = GsonUtils.gsonParseBean(gson, response.getResult(), HandlerIdBean.class);
                LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                loginResponseBean.setHandlerId(HandlerIdBean.getHandlerId());
                //将数据进行保存
                FileSaveUtils.fileSaveObject(loginResponseBean, "loginBean");
                mView.pushDriverMessageSuccess();
                break;
            case 124:
//                List<WorkTypeBean> list = GsonUtils.gsonParseList(gson, response.getResult());  todo  你这个工具方法不好用，在类型转换得时候会丢失范型
                Type type = new TypeToken<List<WorkTypeBean>>() {
                }.getType();
                List<WorkTypeBean> list = gson.fromJson(gson.toJson(response.getResult()), type);
                MLog.e(list.size());
                mView.showPopWindow_SelectJobTypes(list);
                break;
            case 218:
//                LoginResponseBean loginResponseBean = GsonUtils.gsonParseBean(gson, response.getResult(), LoginResponseBean.class);
                DriverIdentBean driverIdentBean = GsonUtils.gsonParseBean(gson, response.getResult(), DriverIdentBean.class);
//              gson.fromJson(response.getResult(),DriverIdentBean.class);
                mView.getDriverMessageSuccess(driverIdentBean);
                break;
        }

    }


}
