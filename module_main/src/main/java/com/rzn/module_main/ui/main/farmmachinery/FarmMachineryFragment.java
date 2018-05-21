package com.rzn.module_main.ui.main.farmmachinery;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;
import com.rzn.module_main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FarmMachineryFragment extends MVPBaseFragment<FarmMachineryContract.View, FarmMachineryPresenter> implements FarmMachineryContract.View {

    private View rootView;
    private RecyclerView rcWorkList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_farmer_driver, container, false);
        initViews();
        return rootView;
    }

    private void initViews() {
        rcWorkList = (RecyclerView) rootView.findViewById(R.id.rc_work_List);


        rcWorkList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<String> s = new ArrayList<>();
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        s.add("1");
        FarmMachineryAdapter farmMachineryAdapter = new FarmMachineryAdapter(R.layout.item_farm_machinery, s);
        rcWorkList.setAdapter(farmMachineryAdapter);

    }
}
