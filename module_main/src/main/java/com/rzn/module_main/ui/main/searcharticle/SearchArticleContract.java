package com.rzn.module_main.ui.main.searcharticle;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.main.farmmachinery.InfoBean;

import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SearchArticleContract {
    interface View extends BaseView {

        void getDataSuccess(List<InfoBean> list);

        void getDataFailed();

    }

    interface Presenter extends BasePresenter<View> {
        void getData(Map<String, String> map);

    }
}
