package com.rzn.module_driver.ui.jobOrder.myjoborder.wait_pay;

import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_driver.ui.bean.MyWorkDetialBean;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class Await_PayPresenter extends BasePresenterImpl<Await_PayContract.View> implements Await_PayContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void getList(Map<String, String> map) {
        mView.showLoading(false, "");
        reqData(mContext, "/handler/taskListToHandler", map, 111);//farmHand/
    }

    @Override
    public void getFarmerListSuccess(Map<String, String> map) {
        mView.showLoading(false, "");
        reqData(mContext, "/farmerTask/farmerTaskList", map, 222);//farmHand/
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {

        switch (requestId) {
            case 111:
                Type type = new TypeToken<List<MyWorkDetialBean>>() {
                }.getType();
                List<MyWorkDetialBean> list = gson.fromJson(gson.toJson(response.getResult()), type);
                mView.getListSuccess(list);
                break;
            case 222:
                Type types = new TypeToken<List<MyWorkDetialBean>>() {
                }.getType();
                List<MyWorkDetialBean> lists = gson.fromJson(gson.toJson(response.getResult()), types);
                mView.getListSuccess(lists);
                break;
        }
    }
}
