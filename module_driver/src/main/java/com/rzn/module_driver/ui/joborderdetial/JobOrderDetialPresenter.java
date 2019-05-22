package com.rzn.module_driver.ui.joborderdetial;


import com.rzn.commonbaselib.bean.JobOrderDetialBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_driver.ui.joborderdetial.bean.ZhifubaoBean;
import com.rzn.module_driver.ui.wxapi.WechatPrePayInfoEntity;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class JobOrderDetialPresenter extends BasePresenterImpl<JobOrderDetialContract.View> implements JobOrderDetialContract.Presenter {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void getData(String farmerTaskId) {
//        15：作业订单详情（机手）  已完成
//
//        请求地址：
//        http://1724l9l212.iask.in/farmHand/handler/taskInfo


        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("farmerTaskId", farmerTaskId);
        reqData(mContext, "/handler/taskInfo", map, 111);//farmHand/
    }

    @Override
    public void getFarmerData(String farmerTaskId) {
        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("farmerTaskId", farmerTaskId);
        reqData(mContext, "/farmerTask/farmerTaskInfo", map, 222);//farmHand/
    }

    @Override
    public void cancelPost(Map<String, String> map) {
        reqData(mContext, "/farmerTask/cancelTask", map, 567);//farmHand/
    }

    @Override
    public void finishWork(Map<String, String> map) {

        reqData(mContext, "/handler/finishTask", map, 218);//farmHand/
    }

    @Override
    public void deletePost(Map<String, String> map) {
        reqData(mContext, "/farmerTask/deleteTask", map, 518);//farmHand/
    }

    @Override
    public void getZhiFuPay(Map<String, String> map) {


        reqData(mContext, "/Alipay/appPayRequest", map, 1111111111);
    }

    @Override
    public void getWeixinPay(Map<String, String> map) {
//        http://173rd88727.iok.la/farmHand/weChatPay/appWeChatPayRequest
        reqData(mContext, "/weChatPay/appWeChatPayRequest", map, 1234567);
    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                JobOrderDetialBean jobOrderDetialBean = GsonUtils.gsonParseBean(gson, response.getResult(), JobOrderDetialBean.class);
                mView.getDataSuccess(jobOrderDetialBean);
                break;
            case 222:
                JobOrderDetialBean jobOrderDetialBeans = GsonUtils.gsonParseBean(gson, response.getResult(), JobOrderDetialBean.class);
                mView.getDataSuccess(jobOrderDetialBeans);
                break;
            case 567:
//                "code": 2002,
//                    "message": "返回数据成功",
//                    "result": {
//                "farmerTaskid": "40289f6c62439f1d0162439fec3d0001",
//                        "status":
                mView.cancelPostSuccess();
                break;


            case 218:
                mView.finishWorkSuccess();
                break;
            case 518:
                mView.deletePostSuccess();
                break;

            case 1111111111:
                ZhifubaoBean zhifubaoBean = GsonUtils.gsonParseBean(gson, response.getResult(), ZhifubaoBean.class);
                mView.getZhiFuPaySuccess(zhifubaoBean);
                break;


            case 1234567:
                WechatPrePayInfoEntity wechatPrePayInfoEntity = GsonUtils.gsonParseBean(gson, response.getResult(), WechatPrePayInfoEntity.class);

                mView.getWeixinPaySucccess(wechatPrePayInfoEntity);
                break;


        }
    }
}
