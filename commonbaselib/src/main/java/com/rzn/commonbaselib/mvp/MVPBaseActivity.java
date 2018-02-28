package com.rzn.commonbaselib.mvp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.R;
import com.rzn.commonbaselib.views.CustomProgressDialog;
import com.zyhealth.expertlib.LibApplication;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.utils.MLog;

import java.lang.reflect.ParameterizedType;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView {
    public CustomProgressDialog dialog;
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
        // mPresenter.onCreate();
        MLog.e("当前页面名称：---" + getClass().getSimpleName());
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }


    /**
     * @param title 设置title
     */
    public TextView setTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.tv_title);
        textView.setText(title);
        findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        return textView;
    }

    /**
     * @param right
     * @return 设置右侧文本
     */
    public TextView setRightText(String right) {
        TextView textView = (TextView) findViewById(R.id.tv_right);
        textView.setText(right);
        return textView;
    }

    /**
     * @param right
     * @return 设置ImageView
     */
    public ImageView setRightImage(int right) {
        ImageView imageView = (ImageView) findViewById(R.id.iv_right);
        imageView.setImageResource(right);
        return imageView;
    }


    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        onBack();
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
        if(response.getCode()!=2100){
            ToastUtils.showShortSafe(response.getMessage());
        }
    }

    @Override
    public void netGetDownline() {
        isDiffLogin=true;
        SPUtils.getInstance().remove("token");
    }




    /**
     * 调用软键盘的enter键
     */
    public View.OnKeyListener onKey = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (keyCode == KeyEvent.KEYCODE_ENTER) {

                InputMethodManager imm = (InputMethodManager) v.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                if (imm.isActive()) {

                    imm.hideSoftInputFromWindow(v.getApplicationWindowToken(),
                            0);
                    complete_enter();
                }
                return true;
            }
            return false;
        }

    };

    protected void complete_enter() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * @param context
     * @param intent
     * 该接受广播中处理单点登陆
     */
   public boolean  isDiffLogin;
    BroadcastReceiver myReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {


        }
    };



    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Activity get_Context() {
        return this;
    }

    @Override
    public void initView() {

    }


    @Override
    public void showLoading(boolean isShow,String content) {
        if (dialog == null) {
            dialog = new CustomProgressDialog(this,content, isShow);
        } else if (dialog.isShowing()) {
            dialog.cancel();
        }
        dialog.show();
    }



    @Override
    public void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void showMessage() {

    }


    public boolean isExistLoginActivity() {
        boolean isExist = false;
        for (int i = 0, size = LibApplication.mActivityStack.size(); i < size; i++) {
            if (null != LibApplication.mActivityStack.get(i)) {
                if (LibApplication.mActivityStack.get(i).getClass().getSimpleName().equals("LoginActivity")) {
                    isExist = true;
                }
            }
        }
        return isExist;
    }
}
