package com.rzn.module_main.ui.sellagriculturalgoods.goodsinfo;


import com.rzn.commonbaselib.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodsInfoPresenter extends BasePresenterImpl<GoodsInfoContract.View> implements GoodsInfoContract.Presenter{
    @Override
    public void onCreate() {
        super.onCreate();
        reqData(mContext,"Test/index",null,111);
    }
}
