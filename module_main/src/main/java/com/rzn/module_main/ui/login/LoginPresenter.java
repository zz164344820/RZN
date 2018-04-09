package com.rzn.module_main.ui.login;

import android.content.Intent;

import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.jobscreening.JobScreeningActivity;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void login(String phone, String code) {
        Map<String,String> map = new HashMap<>();
        map.put("code",code);
        map.put("phone",phone);
        reqData(mContext,"farmHand/user/userRegister",map,111);
    }


    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 111:
                mContext.startActivity(new Intent(mContext, JobScreeningActivity.class));
                mContext.finish();
                break;
        }

    }

}
