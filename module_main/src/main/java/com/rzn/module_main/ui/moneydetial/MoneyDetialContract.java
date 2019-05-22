package com.rzn.module_main.ui.moneydetial;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.moneydetial.bean.AccountWaterListBean;

import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MoneyDetialContract {
    interface View extends BaseView {

        void getListDataSuccess(List<AccountWaterListBean> accountWaterListBeanList);

        void getListDataFailed();
    }

    interface Presenter extends BasePresenter<View> {

        void getListData(Map<String, String> map);

    }
}
