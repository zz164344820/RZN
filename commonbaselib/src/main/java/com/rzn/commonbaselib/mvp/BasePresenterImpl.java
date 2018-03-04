package com.rzn.commonbaselib.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.zyhealth.expertlib.bean.ReqBean;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.net.HttpRequestListener;
import com.zyhealth.expertlib.net.OkHttpLoader;
import com.zyhealth.expertlib.utils.MLog;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * MVPPlugin
 *
 * */

public  class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{
   public AppCompatActivity mContext;
   public   Gson  gson;
    protected Set<ReqBean> set = new HashSet<>();//使用set的好处的可以去除重复请求同一个网络

    protected V mView;
    @Override
    public void attachView(V view) {
        mView=view;

    }

    @Override
    public void detachView() {
        mView=null;
    }

    @Override
    public void onCreate() {
        gson=  new Gson();
        this.mContext=mView.get_Context();
  //      StatusBarUtil.setColorNoTranslucent(mContext,mContext.getResources().getColor(R.color.essential_colour));
        mView.initView();
    }

    @Override
    public void onResume() {
    }


    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
    }



    @Override
    public void reqData(final Context context, final String api, final Map<String,String> obj, final int id) {


                        OkHttpLoader.postformJson(context, api, obj, id, new HttpRequestListener() {

                            @Override
                            public void onSuccess(ResponseBean response) {
                                if(mView!=null){
                                    mView.hideLoading();
                                    //网络请求成功调用方法
                                    if (response.getCode() == 2002) {
                                        mView.onHttpRequestResult(response,id);
                                        httpRequestResult(response,id);
                                    } else{
                                        mView.onHttpRequestFailure(response,id);
                                        httpRequestFailure(response,id);
                                        if(response.getCode() == 2100){
                                            mView.netGetDownline();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(String err) {
                                if(mView==null){
                                    MLog.e("mView是空");
                                }else{
                                    mView.hideLoading();
                                    set.add(new ReqBean(context, api, obj, id));
                                    mView.onHttpRequestErr(err,id);
                                    httpRequestErr(err,id);
                                }

                            }
                        });
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {

    }

    @Override
    public void httpRequestErr(String response, int requestId) {

    }

    @Override
    public void httpRequestFailure(ResponseBean response, int requestId) {

    }


    protected void onClickErr() {
        Iterator<ReqBean> iterator = set.iterator();
        while (iterator.hasNext()) {
            ReqBean reqBean = iterator.next();
            reqData(reqBean.getContext(), reqBean.getUrl(), reqBean.getMap(), reqBean.getRequestId());
        }
        set.clear();//清空set数据
    }
}
