package com.rzn.commonbaselib.applictaion;

import android.view.View;

import com.rzn.commonbaselib.mvp.MVPBaseFragment;

/**
 * Created by zz on 2018/3/4.
 */

public interface IViewDelegate {
    MVPBaseFragment getFragment(String name);

    View getView(String name);
}
