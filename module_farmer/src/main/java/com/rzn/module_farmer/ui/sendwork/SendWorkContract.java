package com.rzn.module_farmer.ui.sendwork;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_farmer.bean.SendWorkBean;
import com.rzn.module_farmer.bean.WorkTypeObjBean;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SendWorkContract {
    interface View extends BaseView {
        void sendSuccess(SendWorkBean sendWorkBean);

        void sendFailed();

        void getWorkTypeSuccess( List<WorkTypeObjBean> list);

        void getWorkTypeFailed();
    }

    interface Presenter extends BasePresenter<View> {

        void httpGetWorkType();

        void httpSendWork(String userId, String farmerTaskId, String name, String mobile, String address,
                          String kind, String kindType, String kindTypeId, String unitPrice, String areas, String flag, String flagNum,
                          String startDate, String endDate, String taskPlace, String remark, String provinceName, String provinceCode,
                          String cityName, String cityCode, String areaName, String areaCode);
    }
}
