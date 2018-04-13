package com.rzn.module_main.ui.login;

import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_main.ui.bean.LoginResponseBean;
import com.zyhealth.expertlib.bean.ResponseBean;
import com.zyhealth.expertlib.utils.GlideUtils;
import com.zyhealth.expertlib.utils.MLog;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void login(String phone, String code) {
        mView.showLoading(false,"");
        Map<String, String> map = new HashMap<>();
        map.put("code", "1234");
        map.put("phone", "18888888888");
        reqData(mContext, "farmHand/user/userRegister", map, 111);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                //登录成功后接收到的数据
                LoginResponseBean loginResponseBean= GsonUtils.gsonParseBean(gson,response.getResult(),LoginResponseBean.class);
                    //将数据进行保存
                FileSaveUtils.fileSaveObject(loginResponseBean, "loginBean");
                //登录成功回调
                mView.loginSuccess();

                break;
        }

    }

}
