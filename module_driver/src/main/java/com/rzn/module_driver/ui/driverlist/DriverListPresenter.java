package com.rzn.module_driver.ui.driverlist;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.module_driver.ui.jobdetails.JobdetailsActivity;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DriverListPresenter extends BasePresenterImpl<DriverListContract.View> implements DriverListContract.Presenter {

    DriverListAdapter driverListAdapter;
    List<String> list  = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        /*在presenter中写apdater是因为数据源在这里*/
        setAdapter();
        getDriverList();

    }

    private void getDriverList() {

        Map<String,String> map = new HashMap<>();
        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
       if(!TextUtils.isEmpty(loginResponseBean.getHandlerId())){
           mView.showLoading(false,"");
           map.put("handlerId",loginResponseBean.getHandlerId());
           reqData(mContext,"farmHand/handler/recommendFarmerTask",map,122);
      }
    }

    private void setAdapter() {
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        driverListAdapter =  mView.setAdapter(list);
        driverListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               // ToastUtils.showShortSafe("条目");
                //跳转作业详情界面
                mContext.startActivity(new Intent(mContext, JobdetailsActivity.class));
            }
        });

        driverListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Map<String,String> map = new HashMap<>();
                LoginResponseBean  bean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
                map.put("handlerId",bean.getHandlerId());
                map.put("farmerTaskId","");

                reqData(mContext,"farmHand/handler/grabTask",map ,166);
               // ToastUtils.showShortSafe("点击抢单按钮");
            }
        });

    }

    @Override
    public void getList(String userId, String farmerId) {
        mView.showLoading(false, "");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        reqData(mContext, "", map, 111);

    }

    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 111:
                break;
            case  166:
                // TODO: 2018/5/18 弹窗 
                break;
        }
    }
}
