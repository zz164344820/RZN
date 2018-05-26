package com.rzn.module_driver.ui.driverlist;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.BasePresenterImpl;
import com.rzn.commonbaselib.utils.FileSaveUtils;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;
import com.rzn.module_driver.ui.bean.WorkTypeBean;
import com.rzn.module_driver.ui.jobdetails.JobdetailsActivity;
import com.zyhealth.expertlib.bean.ResponseBean;

import java.lang.reflect.Type;
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
    List<DriverGrabOrderInfo> list  = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        /*在presenter中写apdater是因为数据源在这里*/
        setAdapter();

        getDriverList(new HashMap<String, String>());

    }

    @Override
    public void httpGetWorkType() {
        mView.showLoading(false,"");
        reqData(mContext, "farmHand/farmerTask/queryKind", null, 222);
    }

    @Override
    public void getDriverList(Map<String,String> map) {
        LoginResponseBean loginResponseBean = (LoginResponseBean) FileSaveUtils.readObject("loginBean");
        if(!TextUtils.isEmpty(loginResponseBean.getHandlerId())){
           mView.showLoading(false,"");
           map.put("handlerId",loginResponseBean.getHandlerId());
           reqData(mContext,"farmHand/handler/recommendFarmerTask",map,122);
      }
    }

    private void setAdapter() {

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
                map.put("farmerTaskId",list.get(position).getId());
                reqData(mContext,"farmHand/handler/grabTask",map ,166);
            }
        });

    }



    @Override
    public void httpRequestResult(ResponseBean response, int requestId) {
        super.httpRequestResult(response, requestId);
        switch (requestId) {
            case 122:
                List<DriverGrabOrderInfo> tempList = gson.fromJson(gson.toJson(response.getResult()),new TypeToken<List<DriverGrabOrderInfo>>(){}.getType());
                list.addAll(tempList);
                driverListAdapter.notifyDataSetChanged();
                break;
            case  166:
                // TODO: 2018/5/18 弹窗 
                break;
            case 222:
                Type type2 = new TypeToken<List<WorkTypeBean>>(){}.getType();
                List<WorkTypeBean> list2= gson.fromJson(gson.toJson(response.getResult()), type2);
                mView.getWorkTypeSuccess(list2);
                break;
        }
    }
}
