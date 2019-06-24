package com.test.viewtesst;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TestView extends View {

    private Paint paint;
    private Rect rect;
    private static final int paintSize = 10;
    public TestView(Context context) {
        super(context);
        init();
    }
    private void init(){
        paint = new Paint();
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE );
        paint.setStrokeWidth(paintSize);
        rect = new Rect();
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int padding = getPaddingLeft();

        int w= getMySize(100,widthMeasureSpec);
        int h = getMySize(100,heightMeasureSpec);
        Log.e("lz", "onMeasure: ===="+padding);

        if (w < h) {
            h = w;
        } else {
            w = h;
        }
        rect.set(getPaddingLeft(),0,w+getPaddingLeft(),h);
        // 设置大小
        setMeasuredDimension(w+getPaddingLeft()+paintSize*2, h+paintSize*2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getPaddingLeft();
        canvas.drawRect(rect,paint);
    }

    private int getMySize(int defaultSize, int measureSpec){
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.UNSPECIFIED:{//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST:{//如果测量模式是最大取值为size
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY:{//如果是固定的大小，那就不要去改变它
                mySize = size;
            }
        }
        Log.e("lw", "onMeasure: "+mySize+"---------------"+mode );

        return mySize;
    }
}
