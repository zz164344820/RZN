package com.rzn.module_driver.ui.joborderdetial.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rzn.module_driver.R;

/**
 * RAY.LEE
 * Created by 17662on 2019/4/3.
 */

public class PayPopupwindow extends PopupWindow {
    private Context context;
    private View popUpWindow;
    private LinearLayout llZhifubao;
    private LinearLayout llWeixin;
    private ImageView ivZhifubao;
    private ImageView ivWeixin;
    private TextView tvCancel;
    private TextView tvSure;
    private String type = "";

    public PayPopupwindow(Context context) {
        this.context = context;
        initLayout();
        setPopupWindow();
    }

    private void setPopupWindow() {
        //        ColorDrawable dw = new ColorDrawable(0x30000000);
//
//        this.setBackgroundDrawable(dw);
        //设置SelectPicPopupWindow的View
        this.setContentView(popUpWindow);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
//        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景  icon_popup_cancel_order
        this.setBackgroundDrawable(dw);
    }

    private void initLayout() {
        popUpWindow = View.inflate(context, R.layout.view_pay_popupwindow, null);
        llZhifubao = (LinearLayout) popUpWindow.findViewById(R.id.ll_zhifubao);
        llWeixin = (LinearLayout) popUpWindow.findViewById(R.id.ll_weixin);
        ivZhifubao = (ImageView) popUpWindow.findViewById(R.id.iv_zhifubao);
        ivWeixin = (ImageView) popUpWindow.findViewById(R.id.iv_weixin);
        tvCancel = (TextView) popUpWindow.findViewById(R.id.tv_cancel);
        tvSure = (TextView) popUpWindow.findViewById(R.id.tv_sure);

        ivZhifubao.setVisibility(View.VISIBLE);
        ivWeixin.setVisibility(View.GONE);
        type = "zhifubao";

        llZhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "zhifubao";
                ivZhifubao.setVisibility(View.VISIBLE);
                ivWeixin.setVisibility(View.GONE);
            }
        });
        llWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "weixin";
                ivZhifubao.setVisibility(View.GONE);
                ivWeixin.setVisibility(View.VISIBLE);
            }
        });


        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPopupClickListener != null) {
                    onPopupClickListener.onClick(type);
                }
            }
        });

    }


    private OnPopupClickListener onPopupClickListener;

    public interface OnPopupClickListener {
        void onClick(String paytype);
        //支付宝，微信支付type值
    }

    public void setOnClickPopListener(OnPopupClickListener onPopupClickListener) {
        this.onPopupClickListener = onPopupClickListener;
    }
}
