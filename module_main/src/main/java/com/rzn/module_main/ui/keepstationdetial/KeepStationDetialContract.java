package com.rzn.module_main.ui.keepstationdetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.keepstationdetial.bean.StationDetialBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class KeepStationDetialContract {
    interface View extends BaseView {
        void getDetialDataSuccess(StationDetialBean stationDetialBean);

        void getDetialDataFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void getDetialData(Map<String, String> map);
    }
}
