package com.rzn.module_main.ui.main.farmmachinery;


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

public class FarmMachineryFragment extends MVPBaseFragment<FarmMachineryContract.View, FarmMachineryPresenter> implements FarmMachineryContract.View {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
//        inflater.inflate(R.layout.)
//        return rootView;
    }
}
