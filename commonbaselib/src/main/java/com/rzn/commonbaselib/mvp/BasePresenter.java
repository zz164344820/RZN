package com.rzn.commonbaselib.mvp;

import android.content.Context;

import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface  BasePresenter <V extends BaseView>{

    void attachView(V view);

    void detachView();

    void  onCreate();

    void  onResume();

    void  onPause();

    void  onDestroy();

    void  reqData(Context context, final String api, final Map<String, String> obj, int id);

    void httpRequestResult(ResponseBean response, int requestId) ;

    void httpRequestErr(String response, int requestId) ;

    void httpRequestFailure(ResponseBean response, int requestId);
}
