package com.rzn.module_main.ui.keepstation;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;

import java.util.List;

import mlxy.utils.T;

/**
 * Created by 17662 on 2018/5/21.
 */

public class KeepStationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public KeepStationAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.ll_go_there);
//        helper.addOnClickListener(R.id.tv_call);
        helper.setOnClickListener(R.id.tv_call, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "暂无联系电话", Toast.LENGTH_LONG).show();
            }
        });
        if ("0".equals(item)) {
            helper.setText(R.id.tv_title, "南京冠世机械设备有限公司农机维修站");
        } else if ("1".equals(item)) {
            helper.setText(R.id.tv_title, "农机配件");
        } else if ("2".equals(item)) {
            helper.setText(R.id.tv_title, "南京冠世机械设备有限公司农机维修站-北门");
        }
    }
}
