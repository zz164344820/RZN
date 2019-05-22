package com.rzn.module_main.ui.addbankcard;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.addbankcard.bean.AddBankCardBean;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class AddBankCardContract {
    interface View extends BaseView {

        void addBankCardDataSuccess(AddBankCardBean addBankCardBean);

        void addBankCardDataFailed();


    }

    interface Presenter extends BasePresenter<View> {
        void addBankCardData(Map<String, String> map);
    }
}
