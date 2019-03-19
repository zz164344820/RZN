package com.rzn.module_main.ui.keepstation;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_main.R;
import com.zyhealth.expertlib.utils.GlideUtils;

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
        helper.setText(R.id.tv_fanwei,item.getRepairScope());
        helper.setText(R.id.tv_address,item.getBusinessAddress());
        helper.setOnClickListener(R.id.tv_call, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(item.getPhone())){
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + item.getPhone());
                    intent.setData(data);
                    mContext.startActivity(intent);
                }else{
                Toast.makeText(mContext, "暂无联系电话", Toast.LENGTH_LONG).show();
                }

            }
        });

        GlideUtils.loadImageView(mContext,item.getFactoryPic1() , (ImageView) helper.getView(R.id.iv_photo));

        helper.setText(R.id.tv_title, item.getFactoryName());
    }
}
