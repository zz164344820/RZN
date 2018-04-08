package com.rzn.commonbaselib.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.views.CustomProgressDialog;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.ParameterizedType;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public  class MVPBaseFragment<V extends BaseView,T extends BasePresenterImpl<V>> extends Fragment implements BaseView{

    public    T mPresenter;
    public AppCompatActivity mContext;
    public CustomProgressDialog dialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            mPresenter= getInstance(this,1);
            mPresenter.attachView((V) this);

    }

    /**
     *  如果你用了support 23的库
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (AppCompatActivity) context;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.detachView();
    }


    @Override
    public AppCompatActivity get_Context() {
        return mContext;
    }

    @Override
    public void initView() {

    }



    @Override
    public void showLoading(boolean isShow,String content) {
        if(dialog==null){
            dialog=new CustomProgressDialog(getActivity(),content,isShow);
        }else if(dialog.isShowing()){
            dialog.cancel();
        }

        dialog.show();
    }


    @Override
    public void hideLoading() {
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public void showMessage() {
    }


    @Override
    public void onHttpRequestResult(ResponseBean response, int requestId) {

    }

    @Override
    public void onHttpRequestErr(String response, int requestId) {

            ToastUtils.showShortSafe("请求连接失败！请稍后再试");

    }

    @Override
    public void onHttpRequestFailure(ResponseBean response, int requestId) {

    }

    @Override
    public void netGetDownline() {
        MVPBaseActivity  activity = (MVPBaseActivity) get_Context();
        activity.netGetDownline();
    }



    public  <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
        }
}
