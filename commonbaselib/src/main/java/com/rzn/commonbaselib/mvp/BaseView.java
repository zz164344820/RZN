package com.rzn.commonbaselib.mvp;

import android.support.v7.app.AppCompatActivity;

import com.zyhealth.expertlib.bean.ResponseBean;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface BaseView {
     AppCompatActivity get_Context();

     void  initView();

     void  showLoading(boolean isShow, String content);

     void  hideLoading();

     void showMessage();

     void onHttpRequestResult(ResponseBean response, int requestId) ;

     void onHttpRequestErr(String response, int requestId) ;
     /*服务器拒绝调用*/
     void onHttpRequestFailure(ResponseBean response, int requestId) ;



}
