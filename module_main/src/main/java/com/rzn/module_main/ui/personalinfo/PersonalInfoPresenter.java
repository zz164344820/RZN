package com.rzn.module_main.ui.personalinfo;

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
        map.put("farmerId",bean.getFarmerId());
      //  reqData(mContext,"farmHand/",map,129);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);

    }
}
