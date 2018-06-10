package com.rzn.njt.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by zz on 2018/3/25.
 */

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction() + ", extras: ");

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            openNotification(context, bundle);
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }


    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String myValue = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            myValue = extrasJson.optString("msg_type");
        } catch (Exception e) {
            Log.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
        //1: 预约  2：取消  3：认证   4：抢单
        if ("3".equals(myValue)) {
            // TODO: 2018/6/2 认证提醒 跳转技手认证
            ARouter.getInstance().build("/main/main").withString("value", "3").navigation();
        } else if ("4".equals(myValue)) {
            // TODO: 2018/6/2 接单提醒（接单，农户发布的作业有人接单了，跳转到具体页面）
            ARouter.getInstance().build("/main/main").withString("value", "4").navigation();
        } else if ("1".equals(myValue)) {
            // TODO: 2018/6/2  预约提醒（预约，有农户预约技手，跳转到具体页面）
            ARouter.getInstance().build("/main/main").withString("value", "1").navigation();
        } else if ("2".equals(myValue)) {
            // TODO: 2018/6/2  预约提醒（预约，取消提醒，跳转到具体页面）
            ARouter.getInstance().build("/main/main").withString("value", "2").navigation();
        }

    }
}
