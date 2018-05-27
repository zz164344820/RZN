package com.rzn.module_driver.ui.jobdetails;

import com.rzn.commonbaselib.bean.JobOrderDetialBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobdetailsPresenter extends BasePresenterImpl<JobdetailsContract.View> implements JobdetailsContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getMessage(Map<String, String> map) {
        reqData(mContext, "farmHand/farmerTask/farmerTaskInfo", map, 222);
    }

    @Override
    public void getPost(Map<String, String> map) {
        reqData(mContext,"farmHand/handler/grabTask",map ,166);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId){
            case 166:
                mView.getPostSuccess();
                break;
            case 222:
                JobBean jobBean = GsonUtils.gsonParseBean(gson, response.getResult(), JobBean.class);
                mView.getMessageSuccess(jobBean);
                break;
        }

    }
}
