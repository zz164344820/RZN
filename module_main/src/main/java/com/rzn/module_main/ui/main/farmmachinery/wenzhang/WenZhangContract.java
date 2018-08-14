package com.rzn.module_main.ui.main.farmmachinery.wenzhang;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.main.farmmachinery.InfoBean;

import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class WenZhangContract {
    interface View extends BaseView {
        void getWenZhangSuccess(List<InfoBean> list);

        void getWenZhangFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void getWenZhangData(Map<String,String> map);

    }
}
