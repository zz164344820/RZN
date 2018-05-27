package com.rzn.module_farmer.ui.selectaddress;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.FarmerDriverMessageBean;
import com.rzn.module_farmer.bean.SelectAddressBean;

import java.util.List;

/**
 * Created by 17662 on 2018/5/15.
 */

public class SelectAddressAdapter extends BaseQuickAdapter<SelectAddressBean, BaseViewHolder> {
    public SelectAddressAdapter( @Nullable List<SelectAddressBean> data) {
        super(R.layout.item_select_work, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectAddressBean item) {
        String type ;
        if("1".equals(item.getFlag())){
            type="集中连片";
        }else {
            type="零星分散";
        }
        helper.setText(R.id.tv_title, item.getTypes());
        helper.setText(R.id.tv_size, item.getAreas()+"亩/"+type+"/"+item.getFlagNum()+"块");
        helper.setText(R.id.tv_work_adress, item.getAddress());
        helper.setText(R.id.tv_time, item.getStartDate() + "至" + item.getEndDate());
        helper.setChecked(R.id.tv_checked,item.isChecked());
        helper.addOnClickListener(R.id.tv_checked);
    }
}
