package com.rzn.module_main.ui.getmoneydetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.getmoneydetial.bean.DetialBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class GetMoneyDetialContract {
    interface View extends BaseView {

        void getDetialDataSuccess( DetialBean detialBean);

        void getDetialDataFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void getDetialData(Map<String, String> map);
    }
}
