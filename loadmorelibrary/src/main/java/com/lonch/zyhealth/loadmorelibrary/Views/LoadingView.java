package com.lonch.zyhealth.loadmorelibrary.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lonch.zyhealth.loadmorelibrary.R;

import java.io.InputStream;

/**
 * 描述
 * Created by fww
 * date 16/4/25.
 */
public class LoadingView extends View {
    private Bitmap bitmap;
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Canvas canvas;
    private  int h ,w;
    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint1 = new Paint();
        paint1.setColor(getResources().getColor(R.color.circle_image_view));
        paint2 = new Paint();
        paint2.setColor(Color.parseColor("#CCCCCC"));
        paint3 = new Paint();
        paint3.setColor(Color.WHITE);
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap2, 374, 29, true);
            bitmap = bitmap1.extractAlpha();// 获取一个透明图片
            h = bitmap.getHeight();//初始化y轴坐标
            w = bitmap.getWidth();//初始化x轴坐标
        }else{
            bitmap = bitmap2.extractAlpha();// 获取一个透明图片
            h = bitmap.getHeight();//初始化y轴坐标
            w = bitmap.getWidth();//初始化x轴坐标
        }

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height ;
        /*if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else
        {
            float textWidth = bitmap.getWidth();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else
        {
            float textHeight = bitmap.getHeight();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
*/



        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        } else {
            float textHeight = bitmap.getHeight();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }

        if (widthMode == MeasureSpec.EXACTLY)
        {
            width = widthSize;
        } else {
            float textWidth = bitmap.getWidth();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        this.canvas = canvas;
        canvas.drawColor(Color.TRANSPARENT); // 画布颜色

        canvas.drawBitmap(bitmap, 0, 0, paint2); //画一个灰的图片
        canvas.save();
        //按X来裁剪区域
        canvas.clipRect(0, 0, w,
                bitmap.getHeight()+0);
        canvas.drawBitmap(bitmap, 0, 0, paint1);
        canvas.restore();
    }
    public void updateView(int i){
        h =i;
        w=i;
       postInvalidate();

    }
}
