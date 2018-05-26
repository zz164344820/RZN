package com.rzn.module_main.ui.myadvice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.rzn.commonbaselib.mvp.MVPBaseActivity;
import com.rzn.module_main.R;
import com.rzn.module_main.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyAdviceActivity extends MVPBaseActivity<MyAdviceContract.View, MyAdvicePresenter> implements MyAdviceContract.View {
    @BindView(R2.id.ed_feedback)
    EditText edFeedback;
    @BindView(R2.id.ed_phoneNum)
    EditText edPhoneNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_feedback);
        ButterKnife.bind(this);
        initViews();
        mPresenter.onCreate();
    }

    private void initViews() {
        setTitle("意见反馈");
    }

    @OnClick(R2.id.tv_commit)
    public void onViewClicked() {
        if(TextUtils.isEmpty(edFeedback.getText().toString().trim())){
            ToastUtils.showShort("反馈信息不能为空");
        }else if(TextUtils.isEmpty(edPhoneNum.getText().toString().trim())){
            ToastUtils.showShort("联系方式不能为空");
        }else {
            mPresenter.commitInfo(edFeedback.getText().toString().trim(),edPhoneNum.getText().toString().trim());
        }
    }
}
