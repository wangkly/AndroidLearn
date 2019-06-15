package com.wangky.scrollleran;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class AnimateView extends View {

    private int mRadius;

    private int mCenterX;

    private int mCenterY;


    private float width;

    private float height;



    public AnimateView(Context context) {
        super(context);
    }

    public AnimateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams la = getLayoutParams();

        int lwidth = la.width;
        int lheight =la.height;

        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSzie = MeasureSpec.getSize(heightMeasureSpec);

        mRadius = Math.min(wSize,hSzie)/2;


    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mpait  = new Paint();
        mpait.setColor(Color.RED);
        mpait.setStrokeWidth(2);
        mpait.setStyle(Paint.Style.STROKE);
        mpait.setAntiAlias(true);

        canvas.drawCircle(width/2,height/2,mRadius,mpait);

        canvas.save();


    }




}
