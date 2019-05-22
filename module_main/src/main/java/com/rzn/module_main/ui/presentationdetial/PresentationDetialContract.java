package com.rzn.module_main.ui.presentationdetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.presentationdetial.bean.PresentationBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PresentationDetialContract {
    interface View extends BaseView {
        void getDetialDataSuccess(PresentationBean presentationBean);

        void getDetialDataFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void getDetialData(Map<String, String> map);
    }
}
