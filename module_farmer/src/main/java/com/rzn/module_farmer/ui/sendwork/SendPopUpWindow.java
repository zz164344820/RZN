package com.rzn.module_farmer.ui.sendwork;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rzn.module_farmer.R;
import com.rzn.module_farmer.bean.WorkTypeBean;
import com.rzn.module_farmer.bean.WorkTypeObjBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 作业类型弹窗
 * <p>
 * Created by 17662 on 2018/5/8.
 */

public class SendPopUpWindow extends PopupWindow {
    private Context context;
    private View popUpWindow;
    private List<WorkTypeBean> list;
    List<WorkTypeBean> lists = new ArrayList<>();
    List<WorkTypeObjBean> typeList = new ArrayList<>();
    private TypesAdapter typesAdapter;
    private TextView tvCancel;
    private TextView tvSure;
    private TypeAdapter typeAdapter;

    private int positions = 0;
    private int typePosition = 0;


    public SendPopUpWindow(Context context, List<WorkTypeBean> list) {
        this.context = context;
        this.list = list;
        popUpWindow = View.inflate(context, R.layout.popupwindow_send_work, null);
        initView();
        initListener();
        setPopupWindow();


    }


    private void setPopupWindow() {
        //设置SelectPicPopupWindow的View
        this.setContentView(popUpWindow);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.PopupAnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景  icon_popup_cancel_order
        this.setBackgroundDrawable(dw);
    }


    private void initView() {

        tvCancel = (TextView) popUpWindow.findViewById(R.id.tv_cancel);
        tvSure = (TextView) popUpWindow.findViewById(R.id.tv_sure);
        //一级列表
        RecyclerView rvType = (RecyclerView) popUpWindow.findViewById(R.id.rv_type);
        rvType.setLayoutManager(new LinearLayoutManager(context));
        lists.addAll(list);
        typeAdapter = new TypeAdapter(R.layout.item_type, lists);
        rvType.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                positions = position;
                lists.clear();
                typeAdapter.getPost(list.get(position).getKindName());
                lists.addAll(list);
                typeAdapter.notifyDataSetChanged();

                typeList.clear();
                typeList.addAll(list.get(position).getTypeArray());
                typesAdapter.notifyDataSetChanged();
            }
        });
        //二级列表
        RecyclerView rvTypes = (RecyclerView) popUpWindow.findViewById(R.id.rv_types);
        rvTypes.setLayoutManager(new LinearLayoutManager(context));
        typeList.addAll(list.get(0).getTypeArray());
        typesAdapter = new TypesAdapter(R.layout.item_type, typeList);
        rvTypes.setAdapter(typesAdapter);
        typesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                typesAdapter.getPosition(position);
//                typesAdapter.notifyDataSetChanged();
                typePosition = position;

                typeList.clear();
                typesAdapter.getPost(list.get(positions).getTypeArray().get(position).getTypeName());
                typeList.addAll(list.get(positions).getTypeArray());
                typesAdapter.notifyDataSetChanged();

            }
        });
    }

    private void initListener() {
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //回调到主页
                if (onClickListener != null) {
                    onClickListener.onClick(positions, typePosition);
                }
                dismiss();
            }
        });
    }

    OnClickListener onClickListener;

    public interface OnClickListener {
        void onClick(int position, int typePosition);
    }

    public void setOnListener(OnClickListener onListener) {
        this.onClickListener = onListener;
    }

}
