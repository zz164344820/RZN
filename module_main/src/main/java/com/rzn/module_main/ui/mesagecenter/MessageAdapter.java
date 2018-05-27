package com.rzn.module_main.ui.mesagecenter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.commonbaselib.applictaion.BaseApplication;

import java.util.List;

/**
 * Created by zz on 2018/5/27.
 */

public class MessageAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public MessageAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
