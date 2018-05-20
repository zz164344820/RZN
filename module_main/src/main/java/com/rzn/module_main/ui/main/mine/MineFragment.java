package com.rzn.module_main.ui.main.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MineFragment extends MVPBaseFragment<MineContract.View, MinePresenter> implements MineContract.View {
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=   inflater.inflate(R.layout.act_myself,container,false);
        return rootView;
    }
}
