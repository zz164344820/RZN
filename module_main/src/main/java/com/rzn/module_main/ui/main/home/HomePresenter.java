package com.rzn.module_main.ui.main.home;

import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.mesagecenter.MessageInfo;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getMessage(Map<String, String> map) {
//        Map<String ,String> map = new HashMap<>();
//        map.put("userId",loginResponseBean.getUserId());
        reqData(mContext, "/user/queryUserMsg", map, 123);//farmHand/
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 123:
                Type typeToken= new TypeToken<List<MessageInfo>>(){}.getType();
                List<MessageInfo> list= gson.fromJson(gson.toJson(response.getResult()), typeToken);
                mView.getMessageSuccess(list);

                break;
        }
    }
}
