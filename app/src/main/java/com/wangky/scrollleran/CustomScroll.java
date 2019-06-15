package com.wangky.scrollleran;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class CustomScroll extends View {

    Scroller mScroller = new Scroller(this.getContext());


    public CustomScroll(Context context) {
        super(context);
    }

    public CustomScroll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScroll(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);



    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();

       if(mScroller.computeScrollOffset()){

           ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());

           invalidate();

       }

    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int lastX = 0;
        int lastY = 0;

            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    lastX = (int) event.getX();
                    lastY = (int) event.getY();

                    break;

                case MotionEvent.ACTION_MOVE:
                    int offsetX = x-lastX;
                    int offsetY = y-lastY;

                    ((View)getParent()).scrollBy(-offsetX,-offsetY);


                    break;

                case MotionEvent.ACTION_UP:

                    View  vg = (View) getParent();

                    mScroller.startScroll(vg.getScrollX(),vg.getScrollY(),-vg.getScrollX(),-vg.getScrollY());

                    invalidate();

                    break;

            }



        return true;

    }




}
