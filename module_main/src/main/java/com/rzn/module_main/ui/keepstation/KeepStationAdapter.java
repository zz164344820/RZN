package com.rzn.module_main.ui.keepstation;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;

import java.util.List;

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
        if ("0".equals(item)) {
            helper.setText(R.id.tv_title, "南京冠世机械设备有限公司农机维修站");
        } else if ("1".equals(item)) {
            helper.setText(R.id.tv_title, "农机配件");
        } else if ("2".equals(item)) {
            helper.setText(R.id.tv_title, "南京冠世机械设备有限公司农机维修站-北门");
        }
    }
}
