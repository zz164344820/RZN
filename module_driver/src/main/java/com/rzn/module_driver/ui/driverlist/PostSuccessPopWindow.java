package com.rzn.module_driver.ui.driverlist;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rzn.module_driver.R;

/**
 * Created by 17662 on 2018/5/19.
 */

public class PostSuccessPopWindow extends PopupWindow {
    private Context context;
    private final View popUpWindow;
    private TextView tvKnow;
    OnPostClickListener onPostClickListener;

    public PostSuccessPopWindow(Context context) {
        this.context = context;
        popUpWindow = View.inflate(context, R.layout.pop_get_post, null);
        initView();
        initListener();
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
        this.setBackgroundDrawable(dw);
    }

    private void initListener() {
        tvKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPostClickListener != null) {
                    onPostClickListener.onClick();
                }
            }
        });
    }

    private void initView() {
        tvKnow = (TextView) popUpWindow.findViewById(R.id.tv_know);

    }

    public interface OnPostClickListener {
        void onClick();
    }

    private void setOnPostClickListener(OnPostClickListener onPostClickListener) {
        this.onPostClickListener = onPostClickListener;
    }
}
