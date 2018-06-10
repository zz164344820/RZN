package com.rzn.module_main.ui.mesagecenter;

import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MesageCenterPresenter extends BasePresenterImpl<MesageCenterContract.View> implements MesageCenterContract.Presenter{
    int pager=0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getMessageList() {
       // todo 请求列表接口
        mView.showLoading(false,"");
        LoginResponseBean  loginResponseBean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map<String ,String> map = new HashMap<>();
        map.put("userId",loginResponseBean.getUserId());
        reqData(mContext,"farmHand/user/queryUserMsg",map,123);
    }

    @Override
    public void setRead(String msgId) {
        Map<String ,String> map = new HashMap<>();
        map.put("userMsgId",msgId);
        reqData(mContext,"farmHand/user/updateUserMsgRead",map,133);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        if(requestId==123){
         Type  typeToken= new TypeToken<List<MessageInfo>>(){}.getType();
         List<MessageInfo> list= gson.fromJson(gson.toJson(response.getResult()), typeToken);
         mView.setData(list);
        }
    }
}
