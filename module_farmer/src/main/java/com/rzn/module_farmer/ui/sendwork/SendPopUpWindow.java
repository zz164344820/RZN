package com.rzn.module_farmer.ui.sendwork;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.rzn.module_farmer.R;


/**
 * 作业类型弹窗
 * <p>
 * Created by 17662 on 2018/5/8.
 */

public class SendPopUpWindow extends PopupWindow {
    private Context context;
    private View popUpWindow;


    public SendPopUpWindow(Context context) {
        this.context = context;
        popUpWindow =  View.inflate(context, R.layout.popupwindow_send_work,null);
        setPopupWindow();


    }

    private void setPopupWindow() {
        //设置SelectPicPopupWindow的View
        this.setContentView(popUpWindow);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.PopupAnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景  icon_popup_cancel_order
        this.setBackgroundDrawable(dw);    }





}
