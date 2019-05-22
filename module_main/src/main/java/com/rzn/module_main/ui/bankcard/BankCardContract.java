package com.rzn.module_main.ui.bankcard;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.applygetmoney.bean.BankMessageBean;

import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BankCardContract {
    interface View extends BaseView {
        void getBankCardListSuccess(List<BankMessageBean> bankListBeanList);

        void getBankCardListFailed();

        void detelBankCardSuccess();

        void detelBankCardFailed();

    }

    interface Presenter extends BasePresenter<View> {
        void getBankCardList(Map<String, String> map);

        void detelBankCard(Map<String, String> map);

    }
}
