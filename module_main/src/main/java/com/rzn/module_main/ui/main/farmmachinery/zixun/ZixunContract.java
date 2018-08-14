package com.rzn.module_main.ui.main.farmmachinery.zixun;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.main.farmmachinery.InfoBean;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ZixunContract {
    interface View extends BaseView {
        void getZixunSuccess(List<InfoBean> list);

        void getZixunFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void getZixunData();
    }
}
