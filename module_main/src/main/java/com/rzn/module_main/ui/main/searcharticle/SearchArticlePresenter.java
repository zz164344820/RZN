package com.rzn.module_main.ui.main.searcharticle;


import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.main.farmmachinery.InfoBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SearchArticlePresenter extends BasePresenterImpl<SearchArticleContract.View> implements SearchArticleContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
//        reqData(mContext,"Test/index",null,111);


    }


    @Override
    public void getData(Map<String, String> map) {
        reqData(mContext, "/boss/queryArticleApp", map, 111);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 111:
                Type type = new TypeToken<List<InfoBean>>(){}.getType();
                List<InfoBean> list= gson.fromJson(gson.toJson(response.getResult()), type);
                mView.getDataSuccess(list);
                break;
        }
    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {
        super.httpRequestFailure(response, requestId);
    }

    @Override
    public void httpRequestErr(String response, int requestId) {
        super.httpRequestErr(response, requestId);
    }
}
