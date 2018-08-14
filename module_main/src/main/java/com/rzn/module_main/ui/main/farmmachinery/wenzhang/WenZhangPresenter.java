package com.rzn.module_main.ui.main.farmmachinery.wenzhang;

import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.main.farmmachinery.InfoBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WenZhangPresenter extends BasePresenterImpl<WenZhangContract.View> implements WenZhangContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getWenZhangData(Map<String,String> map) {
//        Map<String, String> map = new HashMap<>();
        reqData(mContext, "/boss/queryArticleApp", map, 111);
    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 111:
                Type type = new TypeToken<List<InfoBean>>(){}.getType();
                List<InfoBean> list= gson.fromJson(gson.toJson(response.getResult()), type);
                mView.getWenZhangSuccess(list);
                break;
        }
    }
}
