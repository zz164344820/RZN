package com.rzn.module_main.ui.main.home;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.mesagecenter.MessageInfo;

import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeContract {
    interface View extends BaseView {
        void getMessageSuccess(List<MessageInfo> list);

    }

    interface Presenter extends BasePresenter<View> {
        void getMessage(Map<String, String> map);
    }
}
