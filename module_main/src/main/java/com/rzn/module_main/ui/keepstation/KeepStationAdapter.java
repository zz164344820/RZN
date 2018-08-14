package com.rzn.module_main.ui.keepstation;

import android.content.Intent;
import android.net.Uri;
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

public class KeepStationAdapter extends BaseQuickAdapter<KeepStationBean, BaseViewHolder> {
    public KeepStationAdapter(int layoutResId, @Nullable List<KeepStationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final KeepStationBean item) {
        helper.addOnClickListener(R.id.ll_go_there);
//        helper.addOnClickListener(R.id.tv_call);
        helper.setOnClickListener(R.id.tv_call, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + item.getTel());
                intent.setData(data);
                mContext.startActivity(intent);

//                Toast.makeText(mContext, "暂无联系电话", Toast.LENGTH_LONG).show();
            }
        });

        helper.setText(R.id.tv_title, item.getBusinessAddress());
    }
}
