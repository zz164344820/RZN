package com.rzn.module_main.ui.applygetmoney;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.applygetmoney.bean.BankListBean;
import com.rzn.module_main.ui.applygetmoney.bean.BankMessageBean;

import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ApplyGetMoneyContract {
    interface View extends BaseView {

        void getBankListDataSuccess(List<BankMessageBean> bankListBeanList);

        void getBankListDataFailed();

        void getMoneyDataSuccess();

        void getMoneyDataFailed();

    }

    interface Presenter extends BasePresenter<View> {
        void getBankListData(Map<String, String> map);

        void getMoneyData(Map<String, String> map);
    }
}
