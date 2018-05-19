package com.rzn.module_driver.ui.driver_identification;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_driver.ui.bean.WorkTypeBean;

import java.io.File;
import java.util.List;
import java.util.Map;

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

        //void pushDriverMessage(Map<String,String> map , File oneFile , File twoFile , File  threeFile, File  fourFile);
        void pushDriverMessage(Map<String,String> map );
    }


}
