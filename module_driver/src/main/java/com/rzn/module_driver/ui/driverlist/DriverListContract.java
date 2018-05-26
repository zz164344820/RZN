package com.rzn.module_driver.ui.driverlist;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;
import com.rzn.module_driver.ui.bean.WorkTypeBean;

import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverListContract {
    interface View extends BaseView {
        //获取抢单列表成功
        void getListSuccess();

        //获取抢单列表失败
        void getListFailed();

        DriverListAdapter setAdapter(List<DriverGrabOrderInfo> list);

        void  recycleViewRestore();

        void  getWorkTypeSuccess(List<WorkTypeBean> worTypeList);

    }

    interface Presenter extends BasePresenter<View> {


        void httpGetWorkType();

        /*获取抢单list*/
        void getDriverList(Map<String,String> map);
    }
}
