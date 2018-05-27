package com.rzn.module_farmer.ui.farmerlist;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.WorkTypeBean;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerListContract {
    interface View extends BaseView {

        void loadDriverMessageSuccessed(List<FarmerDriverMessageBean> list);
        void getWorkTypeSuccess(List<WorkTypeBean> list);
        void loadDriverMessageFailed();
        void  recycleViewRestore();
    }

    interface Presenter extends BasePresenter<View> {

        void httpGetWorkType();
        void httpLoadDriverMessage(String userId,String page,String areaCode,String kindTypeId);

    }
}
