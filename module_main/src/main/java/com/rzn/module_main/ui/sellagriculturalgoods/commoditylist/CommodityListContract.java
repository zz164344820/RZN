package com.rzn.module_main.ui.sellagriculturalgoods.commoditylist;


import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;

/**
 * 致医健康 MVPPlugin
 * @author zz
 */
public class CommodityListContract {

     interface View extends BaseView {

     }

     interface  Presenter extends BasePresenter<View> {
        void  getCommodityList(String text);
     }

}
