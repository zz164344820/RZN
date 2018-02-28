package com.lonch.zyhealth.loadmorelibrary.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.aspsine.swipetoloadlayout.SwipeRefreshHeaderLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lonch.zyhealth.loadmorelibrary.R;

/**
 * Created by Aspsine on 2015/9/9.
 */
public class TwitterRefreshHeaderView2 extends SwipeRefreshHeaderLayout {

    private LoadingView iv_slogon_above;
    private ImageView iv_gif;


    private int mHeaderHeight;

    Context context;

    public TwitterRefreshHeaderView2(Context context) {
        this(context, null);
    }

    public TwitterRefreshHeaderView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwitterRefreshHeaderView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_classic);

    }

    int iv_width;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iv_slogon_above = (LoadingView) findViewById(R.id.iv_slogon_above);
        iv_gif = (ImageView) findViewById(R.id.iv_gif);
        pulldown();
    }

    public  int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onRefresh() {
       // tvRefresh.setText("正在刷新");
        iv_slogon_above.setVisibility(INVISIBLE);
        Glide.with(context).load(R.drawable.guang).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv_gif);
    }

    @Override
    public void onPrepare() {
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }




    @Override
    public void onMove(final int y, boolean isComplete, boolean automatic) {

        if (!isComplete) {
            iv_slogon_above.setVisibility(VISIBLE);
            Glide.with(context).load("").asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv_gif);
            if (y > mHeaderHeight) {
                iv_slogon_above.updateView(iv_width);
              //  tvRefresh.setText("释放刷新");
            } else if (y < mHeaderHeight) {
                iv_slogon_above.updateView(iv_width*(y-dp2px(4))/mHeaderHeight);

            }
        }

    }



    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
    }

    @Override
    public void onComplete() {
       // rotated = false;
       // tvRefresh.setText("刷新完成");
    }

    @Override
    public void onReset() {
       // rotated = false;

    }




    /**
     * 下拉动画同意调用
     */
    public void pulldown() {
        iv_slogon_above.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    /**
                     * 当ll_safe_des执行完onLayout之后调用，只要内部的子View高度发生变化就会引起
                     * ll_safe_des重新layout，那么又触发onGlobalLayout方法回调
                     */
                    @Override
                    public void onGlobalLayout() {
                        // 一般用完这个监听器，需要立即移除
                        iv_slogon_above.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        iv_width = iv_slogon_above.getWidth();
                       /* // 2.隐藏ll_safe_des，将ll_safe_des的高度设置为0
                        iv_slogon_above.getLayoutParams().width = 0;
                        iv_slogon_above.requestLayout();*/
                    }
                });
    }



}
