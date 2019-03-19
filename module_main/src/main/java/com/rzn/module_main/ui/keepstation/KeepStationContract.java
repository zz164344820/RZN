package com.rzn.module_main.ui.keepstation;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KeepStationContract {
    interface View extends BaseView {
        void getKeepDataSuccess(List<KeepStationBean> list);

        void getKeepDataFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void getKeepData(String searchStr, String longitude, String latitude);
    }
}
