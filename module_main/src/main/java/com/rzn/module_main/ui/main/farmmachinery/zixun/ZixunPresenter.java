package com.rzn.module_main.ui.main.farmmachinery.zixun;

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

public class ZixunPresenter extends BasePresenterImpl<ZixunContract.View> implements ZixunContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getZixunData() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "1");
        reqData(mContext, "/boss/queryArticleApp", map, 111);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                Type type = new TypeToken<List<InfoBean>>() {
                }.getType();
                List<InfoBean> list = gson.fromJson(gson.toJson(response.getResult()), type);
                mView.getZixunSuccess(list);
                break;
        }

    }
}
