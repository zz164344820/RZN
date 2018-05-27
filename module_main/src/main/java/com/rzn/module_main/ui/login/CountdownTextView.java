package com.rzn.module_main.ui.login;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.rzn.module_main.R;


/**
 *  自定义倒计时TextView
 *  后续可继续在该类进行扩展开发
 *
 */
public class CountdownTextView extends AppCompatTextView {
    private Handler handler;
    private CountdownCallBack callBack;
    private int time =-1;
    private boolean isStop=false;

    public interface CountdownCallBack {
        void  countdownFinsh();
    }



    public CountdownTextView(Context context) {
        super(context);
    }

    public CountdownTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void startCountdown(int seconds, CountdownCallBack callBack) {
        this.callBack = callBack;
        time = seconds;
        setBackgroundColor(getResources().getColor(R.color.darkgray));
        setText("再次获取("+time+")");
        handler = new Handler();
        handler.postDelayed(runnable, 1000);
    }


    public boolean isComplete(){
        if(time==0)
            return true;
        else
            return  false;
    }


    public void onPause() {
        isStop = true;
    }

    public void onResume() {
        if(time>0 && isStop) {
            isStop = false;
            handler.postDelayed(runnable, 1000);
        }
    }

    public void onDestroy() {
        handler = null;
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            setText("再次获取("+time+")");
            if (time >0 && !isStop) {
                handler.postDelayed(this, 1000);
            } else if (time == 0) {
                callBack.countdownFinsh();
            }
        }
    };

}
