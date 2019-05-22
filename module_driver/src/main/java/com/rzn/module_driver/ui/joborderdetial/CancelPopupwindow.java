package com.rzn.module_driver.ui.joborderdetial;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rzn.module_driver.R;

/**
 * RAY.LEE
 * Created by 17662on 2019/4/3.
 */

public class CancelPopupwindow extends PopupWindow {
    private Context context;
    private View popUpWindow;

    private TextView tvCancel;
    private TextView tvSure;
    private String type = "";
    private EditText editText;

    public CancelPopupwindow(Context context) {
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
        popUpWindow = View.inflate(context, R.layout.cancel_popup_window, null);

        tvCancel = (TextView) popUpWindow.findViewById(R.id.tv_cancel);
        tvSure = (TextView) popUpWindow.findViewById(R.id.tv_sure);

        editText = (EditText) popUpWindow.findViewById(R.id.et_cancel_text);


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
                    type = editText.getText().toString().trim();
                    if (TextUtils.isEmpty(type)) {
                        Toast.makeText(context, "请填写取消原因！", Toast.LENGTH_SHORT).show();
                        return;
                    }
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
