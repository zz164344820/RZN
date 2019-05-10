package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

import java.util.List;

/**
 * 致医健康 MVPPlugin
 * @author zz
 */
public class CommodityListContract {

     interface View extends BaseView {

         void refreshList(List<CommodityListBean> list);
         void recycleViewRestore();
     }

     interface  Presenter extends BasePresenter<View> {
        void  getCommodityList(String page, int type ,String name );
     }

}
