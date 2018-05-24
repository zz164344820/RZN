package com.rzn.module_driver.ui.jobOrder.havefinished;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_driver.ui.bean.MyWorkDetialBean;

import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HaveFinishedContract {
    interface View extends BaseView {
        void getListSuccess(List<MyWorkDetialBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        void getList(Map<String,String> map);
        void getFarmerListSuccess(Map<String,String> map);
    }
}
