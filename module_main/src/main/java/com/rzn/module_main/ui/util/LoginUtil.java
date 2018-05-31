package com.rzn.module_main.ui.util;

import android.text.TextUtils;

import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.utils.FileSaveUtils;

/**
 * Created by 17662 on 2018/5/31.
 */

public class LoginUtil {


    /**
     * 获取用户userId
     *
     * @return
     */
    public static Boolean getUserId() {
        String userId = "";
        boolean isLogin = false;
        LoginResponseBean responseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if (responseBean != null) {
            userId = responseBean.getUserId();
        }
        if (TextUtils.isEmpty(userId)) {
            isLogin = false;
        } else {
            isLogin = true;
        }
        return isLogin;
    }
}
