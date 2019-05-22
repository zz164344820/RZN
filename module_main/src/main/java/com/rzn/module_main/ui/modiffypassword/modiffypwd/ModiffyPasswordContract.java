package com.rzn.module_main.ui.modiffypassword.modiffypwd;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.modiffypassword.modiffypwd.bean.ModiffyBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ModiffyPasswordContract {
    interface View extends BaseView {


        void pushPasswordDataSuccess(ModiffyBean modiffyBean);

        void pushPasswordDataFailed();

    }

    interface Presenter extends BasePresenter<View> {
        void pushPasswordData(Map<String, String> map);
    }
}
