package com.wangky.scrollleran;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {

    private Paint mPaint;


    private Path mPath;


    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){

        mPaint= new Paint();
        mPaint.setStrokeWidth(0);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(false);
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setAlpha(100);

        mPath = new Path();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(100,100,400,400);

        canvas.drawColor(Color.WHITE);
        canvas.drawRect(rectF,mPaint);


        Paint arcPatint= new Paint();
        arcPatint.setStrokeWidth(2);
        arcPatint.setColor(Color.GREEN);
        arcPatint.setAntiAlias(false);
        arcPatint.setStyle(Paint.Style.FILL);


        canvas.drawArc(rectF,90,180,true,arcPatint);
        canvas.drawArc(rectF,90,-90,false,arcPatint);



        Paint OvaPaint = new Paint();

        OvaPaint.setColor(Color.BLACK);

        OvaPaint.setStrokeWidth(5);

        OvaPaint.setStyle(Paint.Style.STROKE);


        RectF rectF2 = new RectF(500,100,900,400);

        canvas.drawOval(rectF2,OvaPaint);



        canvas.drawCircle(500,200,200,mPaint);


        mPath.moveTo(400,400);
        mPath.lineTo(300,300);
        mPath.lineTo(400,100);

        canvas.drawPath(mPath,OvaPaint);


    }




}
