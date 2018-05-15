package com.rzn.module_driver.ui.driver_identification;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_driver.ui.bean.WorkTypeBean;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class Driver_identificationContract {
    interface View extends BaseView {
        void pushDriverMessageSuccess();

        void pushDriverMessageFaile();

        void  showPopWindow_SelectJobTypes(List<WorkTypeBean> list);

    }

    interface Presenter extends BasePresenter<View> {
        void  getJobTypes();

        void pushDriverMessage(String userId,String handlerId,String name,String sex,String birthday,String idNo,
                               String mobile,String icon,String startDate,String endDate,String years,String carType,String carNo,
                               String carPic1,String carPic2,String machinePic1,String machinePic2,String belongs
                               );
    }


}
