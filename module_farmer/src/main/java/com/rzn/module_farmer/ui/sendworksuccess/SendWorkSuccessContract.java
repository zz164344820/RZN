package com.rzn.module_farmer.ui.sendworksuccess;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.RecommendDriver;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SendWorkSuccessContract {
    interface View extends BaseView {
       void loadDriverMessageSuccessed(List<RecommendDriver> list1);
    }

    interface  Presenter extends BasePresenter<View> {
       void httpLoadDriverMessage(String farmerTaskId);
    }
}
