package com.rzn.commonbaselib.views;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.zyhealth.expertlib.utils.MLog;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class GradientEffect {

    private Context mContent;
    private float alpha=1f;

    public GradientEffect(Context mContent) {
        this.mContent = mContent;
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    backgroundAlpha((float)msg.obj);
                    break;
            }
        }
    };

    //颜色变深
    public void setBackgroundAlphaAdd() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(alpha>0.5f){
                    try {
                        //4是根据弹出动画时间和减少的透明度计算
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg =mHandler.obtainMessage();
                    msg.what = 1;
                    //每次减少0.01，精度越高，变暗的效果越流畅
                    alpha-=0.01f;
                    msg.obj =alpha ;
                    mHandler.sendMessage(msg);
                }
            }

        }).start();
    }

    //颜色变浅
    public void setBackgroundAlphaSub() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                //此处while的条件alpha不能<= 否则会出现黑屏
                while(alpha<1f){
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MLog.d("HeadPortrait","alpha:"+alpha);
                    Message msg =mHandler.obtainMessage();
                    msg.what = 1;
                    alpha+=0.01f;
                    msg.obj =alpha ;
                    mHandler.sendMessage(msg);
                }
            }

        }).start();
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = ((Activity)mContent).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity)mContent).getWindow().setAttributes(lp);
        ((Activity)mContent).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
