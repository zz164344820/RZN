package com.rzn.module_main.ui.mesagecenter;

import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MesageCenterContract {
    interface View extends BaseView {
        void  setData(List<MessageInfo> list);
        
    }

    interface  Presenter extends BasePresenter<View> {
        void getMessageList();
    }
}
