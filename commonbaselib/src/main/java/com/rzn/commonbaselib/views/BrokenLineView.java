package com.rzn.commonbaselib.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.ScreenUtils;

/**
 * Created by zz on 2017/7/26.
 */

public class BrokenLineView extends View {
    private Paint mPaint;

    public BrokenLineView(Context context) {
        super(context);
        initView();
    }


    public BrokenLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BrokenLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //setBackgroundColor(Color.parseColor("#222222"));
        mPaint = new Paint();
    }

    /**
     * 绘制折线图
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasBrokenLine(canvas);

    }

    private void canvasBrokenLine(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#EFEFF4"));

        int width = ScreenUtils.getScreenWidth();
        int dotx  = width/20*11;
        int extrudeWidth  = width/15;
        int extrudeHeight  =width/22;
        canvas.drawLine(0, extrudeHeight, dotx, extrudeHeight, mPaint);
        canvas.drawLine(dotx, extrudeHeight, dotx+extrudeWidth/2, 0, mPaint);
        canvas.drawLine(dotx+extrudeWidth/2, 0, dotx+extrudeWidth, extrudeHeight, mPaint);
        canvas.drawLine(dotx+extrudeWidth, extrudeHeight, width, extrudeHeight, mPaint);
    }



}