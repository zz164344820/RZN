package com.rzn.commonbaselib.utils;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.rzn.commonbaselib.R;
import com.rzn.commonbaselib.listener.ConfirmationCallBack;

/**
 * Created by zz on 2018/8/15.
 */

public class RZNUtils {


    /**
     * 统一弹出框,项目中确认样式
     */
    public static Dialog popupDialog_Confirm(final Activity context , String content , String left , String right , final ConfirmationCallBack callBack) {
        View view= View.inflate(context, R.layout.dialog_affirm,null);
        final Dialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(TextUtils.isEmpty(left)?false:true);
        dialog.setCancelable(TextUtils.isEmpty(left)?false:true);
        dialog.show();
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setWindowAnimations(R.style.showDialog);
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);//解决弹出框有4个小黑角问题
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = SizeUtils.dp2px(280);
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CLIP_VERTICAL);
        dialogWindow.setAttributes(lp);
        TextView tv_cancle = (TextView) view.findViewById(R.id.tv_cancle);
        view.findViewById(R.id.line).setVisibility(TextUtils.isEmpty(left)?View.GONE:View.VISIBLE);
        tv_cancle.setVisibility(TextUtils.isEmpty(left)?View.GONE:View.VISIBLE);

        tv_cancle.setText(left);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callBack.cancel();
            }
        });
        TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        tv_confirm.setText(right);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callBack.confirm();
            }
        });

        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_content.setText(content);
        return  dialog;

    }

}
