package com.rzn.module_driver.ui.posting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_driver.R;
import com.rzn.module_driver.ui.bean.DriverGrabOrderInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class PostingActivity extends MVPBaseActivity<PostingContract.View, PostingPresenter> implements PostingContract.View {

    private RecyclerView rvType;
    List<DriverGrabOrderInfo> list = new ArrayList<>();
    private PostingAdapter postingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_post);
        initViews();
        mPresenter.onCreate();

//        showLoading(false,"");
    }

    private void initViews() {
        setTitle("正在接单");
        rvType = (RecyclerView) findViewById(R.id.rv_type);
        rvType.setLayoutManager(new LinearLayoutManager(this));
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("1");
//        list.add("1");
//        list.add("1");

        postingAdapter = new PostingAdapter(R.layout.driver_item_driverlists, list);
        rvType.setAdapter(postingAdapter);

    }

    @Override
    public void getListSuccess(List<DriverGrabOrderInfo> tempList) {
        list.addAll(tempList);
        postingAdapter.notifyDataSetChanged();
    }
}
