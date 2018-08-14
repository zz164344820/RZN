package com.rzn.module_main.ui.myadvice;


import com.blankj.utilcode.util.ToastUtils;
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

public class MyAdvicePresenter extends BasePresenterImpl<MyAdviceContract.View> implements MyAdviceContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void commitInfo(String feedback, String contactWay) {
        mView.showLoading(false,"");
        LoginResponseBean bean= (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        Map<String,String> map = new HashMap<>();
        map.put("userId",bean.getUserId());
        map.put("phone",contactWay);
        map.put("content",feedback);
        reqData(mContext,"/boss/createIdea",map,111);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        ToastUtils.showShort("感谢您的反馈！");
        mContext.finish();
    }
}
