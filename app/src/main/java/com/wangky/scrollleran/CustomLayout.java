package com.wangky.scrollleran;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class CustomLayout extends ViewGroup {

    Scroller mScroller;

    public CustomLayout(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public CustomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

       int count = getChildCount();


       for (int i =0 ;i<count;i++){
          View child = getChildAt(i);

          measureChild(child,widthMeasureSpec,heightMeasureSpec);
       }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
        }
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
                scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
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

                Log.d("action_move******", "onTouchEvent:offsetX " + offsetX);

//              ((View)getParent()).scrollBy(-offsetX,0);
                scrollTo(offsetX,0);
//                scrollBy(200,0);

                break;

            case MotionEvent.ACTION_UP:
                mScroller.startScroll(getScrollX(),getScrollY(),-getScrollX(),-getScrollY());
                invalidate();

                break;
        }

        return  true;

    }
}
