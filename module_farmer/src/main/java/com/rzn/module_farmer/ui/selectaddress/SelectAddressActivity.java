package com.rzn.module_farmer.ui.selectaddress;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rzn.commonbaselib.bean.LoginResponseBean;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.commonbaselib.utils.GsonUtils;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.FarmTaskInfo;
import com.rzn.module_farmer.bean.SelectAddressBean;
import com.rzn.module_farmer.ui.sendwork.SendWorkActivity;
import com.zyhealth.expertlib.bean.ResponseBean;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class SelectAddressActivity extends MVPBaseActivity<SelectAddressContract.View, SelectAddressPresenter> implements SelectAddressContract.View {

    private TextView tvSure;
    private RecyclerView swipeTarget;
    List<SelectAddressBean> list = new ArrayList<>();
    SelectAddressAdapter selectAddressAdapter;
    int chekedIndex =0;
    private String handlerId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_select_work);
        mPresenter.onCreate();
        initViews();
        initListener();
//        showLoading(false,"");
    }

    @Override
    public void onHttpRequestResult(ResponseBean response, int requestId) {
        super.onHttpRequestResult(response, requestId);
        if(requestId==111){
            Gson gson = new Gson();
            Type type = new TypeToken<List<SelectAddressBean>>(){}.getType();
            List<SelectAddressBean>  tempList =  gson.fromJson(gson.toJson(response.getResult()),type);
            list.clear();
            list.addAll(tempList);
            list.get(0).setChecked(true);
            selectAddressAdapter.notifyDataSetChanged();
        }else if(requestId==222){
            ToastUtils.showShort("预约成功！");
            ARouter.getInstance().build("/module/JobScreeningActivity").navigation();
        }
    }

    private void initViews() {
        handlerId=getIntent().getStringExtra("handlerId");
        setTitle("选择要匹配的作业需求");
        //确认预约按钮
        tvSure = (TextView) findViewById(R.id.tv_sure);
        //recyclerView
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        swipeTarget.setLayoutManager(new LinearLayoutManager(this));
         selectAddressAdapter = new SelectAddressAdapter(list);
        swipeTarget.setAdapter(selectAddressAdapter);
        selectAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.tv_checked){
                    if(position==chekedIndex){
                        list.get(chekedIndex).setChecked(((CheckBox)view).isChecked());
                        return;
                    }
                    list.get(chekedIndex).setChecked(false);
                    selectAddressAdapter.notifyItemChanged(chekedIndex);
                    list.get(position).setChecked(true);
                    chekedIndex=position;
                    selectAddressAdapter.notifyItemChanged(chekedIndex);

                }
            }
        });

        findViewById(R.id.ll_work_need).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectAddressActivity.this, SendWorkActivity.class));
            }
        });

    }

    private void initListener() {
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求预约接口
                if(list.size()>0){
                    if(list.get(chekedIndex).isChecked()){
                        mPresenter.affirmAppointment(list.get(chekedIndex).getFarmerTaskId(),handlerId);
                    }else{
                        ToastUtils.showShort("请先选择作业");
                    }
                }

            }
        });
    }


}
