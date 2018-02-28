package com.rzn.commonbaselib.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NosrollViewPager extends ViewPager {

	public NosrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;// 自己不处理事件，把ViewPager默认的滑动事件干掉
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;// 不拦截孩子的事件
	}

}
