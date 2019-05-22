package com.rzn.module_driver.ui.joborderdetial;


import com.rzn.commonbaselib.bean.JobOrderDetialBean;
import com.rzn.commonbaselib.mvp.BasePresenter;
import com.rzn.commonbaselib.mvp.BaseView;
import com.rzn.module_driver.ui.joborderdetial.bean.ZhifubaoBean;
import com.rzn.module_driver.ui.wxapi.WechatPrePayInfoEntity;

import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobOrderDetialContract {
    interface View extends BaseView {
        void getDataSuccess(JobOrderDetialBean jobOrderDetialBean);

        void getDataFaile();

        void cancelPostSuccess();

        void finishWorkSuccess();

        void deletePostSuccess();


        void getZhiFuPaySuccess(ZhifubaoBean zhifubaoBean);

        void getZhiFuPayFailed();


        void getWeixinPaySucccess(WechatPrePayInfoEntity wechatPrePayInfoEntity);

        void getWeixinPayFailed();

    }

    interface Presenter extends BasePresenter<View> {
        void getData(String farmerTaskId);

        void getFarmerData(String farmerTaskId);

        void cancelPost(Map<String, String> map);

        void finishWork(Map<String, String> map);

        void deletePost(Map<String, String> map);


        //获取支付宝得串
        void getZhiFuPay(Map<String, String> map);

        //微信
        void getWeixinPay(Map<String, String> map);

    }
}
