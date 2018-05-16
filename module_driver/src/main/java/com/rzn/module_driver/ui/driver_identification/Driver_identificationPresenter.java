package com.rzn.module_driver.ui.driver_identification;

import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
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

import static com.rzn.commonbaselib.utils.GsonUtils.gsonParseList;

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
    public void getJobTypes() {
        mView.showLoading(false, "");
        reqData(mContext, "farmHand/farmerTask/queryKind", null, 124);

    }

    @Override
    public void pushDriverMessage(final Map<String,String> map , File oneFile , File twoFile , File  threeFile, File  fourFile) {
        mView.showLoading(false, "");
        OkHttpUtils.post()//
                .addFile("carPic1", oneFile.getName(), oneFile)//
                .addFile("carPic2", twoFile.getName(), twoFile)//
                .addFile("machinePic1", threeFile.getName(),threeFile)//
                .addFile("machinePic2", fourFile.getName(), fourFile)//
                .url(OkHttpLoader.BASEURL+"farmHand/handler/updateSaveHandler")
                .params(map)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                      MLog.e(e.getMessage());
                      mView.hideLoading();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MLog.e(gson.toJson(map));
                        MLog.e(response);
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                mView.pushDriverMessageSuccess();
                break;
            case 124:
//                List<WorkTypeBean> list = GsonUtils.gsonParseList(gson, response.getResult());  todo  你这个工具方法不好用，在类型转换得时候会丢失范型
                Type type = new TypeToken<List<WorkTypeBean>>(){}.getType();
                List<WorkTypeBean> list= gson.fromJson(gson.toJson(response.getResult()), type);
                MLog.e(list.size());
                mView.showPopWindow_SelectJobTypes(list);
                break;
        }

    }


}
