package com.rzn.module_farmer.ui.selectaddress;


import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SelectAddressPresenter extends BasePresenterImpl<SelectAddressContract.View> implements SelectAddressContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        getFarmTaskList();

    }

    private void getFarmTaskList() {
        mView.showLoading(false,"");
        Map<String,String> map = new HashMap<>();
        LoginResponseBean responseBean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        map.put("userId",responseBean.getUserId());
        map.put("status","1");
        reqData(mContext,"/farmerTask/farmerTaskList",map,111);//farmHand/
    }


    @Override
    public void affirmAppointment(String farmerTaskId, String handlerId) {
        mView.showLoading(false,"");
        Map<String,String> map = new HashMap<>();
        map.put("farmerTaskId",farmerTaskId);
        map.put("handlerId",handlerId);
        reqData(mContext,"/farmerTask/confirmAppoint",map,222);//farmHand/
    }
}
