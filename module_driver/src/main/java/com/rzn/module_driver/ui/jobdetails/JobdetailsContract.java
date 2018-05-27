package com.rzn.module_driver.ui.jobdetails;

import com.rzn.commonbaselib.bean.JobOrderDetialBean;
import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobdetailsContract {
    interface View extends BaseView {
        void getMessageSuccess(JobBean jobBean);

        void getPostSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getMessage(Map<String, String> map);

        void getPost(Map<String, String> map);
    }
}
