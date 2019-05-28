package com.rzn.module_main.ui.sellagriculturalgoods;



import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.module_main.ui.sellagriculturalgoods.commoditylist.CommodityListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SellAgriculturalGoodsPresenter extends BasePresenterImpl<SellAgriculturalGoodsContract.View> implements SellAgriculturalGoodsContract.Presenter{


    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
       String[] mTitles = {" 全部 ", " 水果 ", " 蔬菜 "," 粮油 " ," 其他 "};
       List<String> list = Arrays.asList(mTitles);
       ArrayList<CommodityListFragment> listFragment = new ArrayList();

       for(int i =0;i<mTitles.length;i++){
           listFragment.add(CommodityListFragment.getInstance(mTitles[i],i,mView.getquery()));
       }
        mView.setViewPager(list,listFragment);
    }
}
