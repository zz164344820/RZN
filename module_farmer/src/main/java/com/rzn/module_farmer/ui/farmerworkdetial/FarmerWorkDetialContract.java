package com.rzn.module_farmer.ui.farmerworkdetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_farmer.bean.FarmerOrderDetialBean;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class FarmerWorkDetialContract {
    interface View extends BaseView {
        //获取数据成功
        void onLoadSuccess( FarmerOrderDetialBean farmerOrderDetialBean);

        //获取数据失败
        void onLoafFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void httpLoadData(String farmerTaskId);
    }
}
