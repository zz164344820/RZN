package com.lonch.zyhealth.loadmorelibrary;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

/**
 * Created by zz on 2017/6/9.
 */

public class LoadMoreUtils {

    public static void recycleViewRestore(SwipeToLoadLayout swipeToLoadLayout) {
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        } else if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
    }
}
