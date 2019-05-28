package com.rzn.module_main.ui.sellagriculturalgoods;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_main.ui.sellagriculturalgoods.commoditylist.CommodityListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SellAgriculturalGoodsContract {
    interface View extends BaseView {

       void setViewPager(List<String> list,ArrayList<CommodityListFragment> fragmentList);
       String getquery();
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
