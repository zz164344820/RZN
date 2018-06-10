package com.rzn.module_main.ui.mesagecenter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.commonbaselib.applictaion.BaseApplication;
import com.rzn.module_main.R;
import java.util.List;

/**
 * Created by zz on 2018/5/27.
 */

public class MessageAdapter extends BaseQuickAdapter<MessageInfo,BaseViewHolder> {
    Context context;
    public MessageAdapter(Context context, @Nullable List<MessageInfo> data) {
        super(R.layout.item_messagecenter, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageInfo item) {
          helper.setText(R.id.tv_title,item.getMsgTitle());
          helper.setText(R.id.tv_content,item.getMsgContent());
          helper.setTextColor(R.id.tv_title,"0".equals(item.getIsread())?context.getResources().getColor(R.color.text_333):context.getResources().getColor(R.color.text_888));
          helper.setBackgroundRes(R.id.ll_ivbackground,"0".equals(item.getIsread())?R.drawable.mainlib_shape_over:R.drawable.mainlib_shape_grayover);
          if("4".equals(item.getMsgType())){
              //订单类 接单
              helper.setBackgroundRes(R.id.iv_type,"0".equals(item.getIsread())?R.drawable.order_maincolor:R.drawable.order_graycolor);
          }else if("2".equals(item.getMsgType())){
              //订单类 取消订单
              helper.setBackgroundRes(R.id.iv_type,"0".equals(item.getIsread())?R.drawable.order_maincolor:R.drawable.order_graycolor);
          }else if ("1".equals(item.getMsgType())){
              //预约类 预约
              helper.setBackgroundRes(R.id.iv_type,"0".equals(item.getIsread())?R.drawable.appointment_maincolor:R.drawable.appointment_graycolor);
          }else if("3".equals(item.getMsgType())){
              //通知类  认证审核
              helper.setBackgroundRes(R.id.iv_type,"0".equals(item.getIsread())?R.drawable.notification_maincolor:R.drawable.notification_graycolor);
          }
    }
}
