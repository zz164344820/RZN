package com.rzn.module_main.ui.personalinfo;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalInfoPresenter extends BasePresenterImpl<PersonalInfoContract.View> implements PersonalInfoContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        LoginResponseBean  bean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map<String,String> map = new HashMap<>();
        map.put("userId",bean.getUserId());
        mView.showLoading(false,"");
        reqData(mContext,"/user/getUser",map,129);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 129:
                UserInfo userInfo=   gson.fromJson(gson.toJson(response.getResult()),new TypeToken<UserInfo>(){}.getType());
                mView.setInfo(userInfo);
                break;
            case  130:
                ToastUtils.showShort("信息修改成功");
                mView.savePic();
                mContext.finish();
                break;
                default:
                    break;
        }

    }



    @Override
    public void setUserInfo(Map map ) {
        mView.showLoading(false,"");
        reqData(mContext,"/user/updateUser",map,130);
    }
}
