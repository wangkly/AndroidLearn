package com.wangky.scrollleran;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class CustomDragView extends ViewGroup{

    private ViewDragHelper mViewDragHelper;

    private View first,second;

    private int x,y;



   private ViewDragHelper.Callback  callback = new ViewDragHelper.Callback() {
       @Override
       public boolean tryCaptureView(@NonNull View view, int i) {
           return true;


       }

       @Override
       public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
           super.onViewCaptured(capturedChild, activePointerId);

           x = capturedChild.getLeft();
           y = capturedChild.getTop();
       }

       @Override
       public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
           super.onViewReleased(releasedChild, xvel, yvel);

           mViewDragHelper.smoothSlideViewTo(releasedChild,x,y);
           ViewCompat.postInvalidateOnAnimation(CustomDragView.this);
       }

       @Override
       public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
           return left;
       }

       @Override
       public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
           return top;
       }
   };


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
       first = getChildAt(0);
       second = getChildAt(1);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mViewDragHelper.continueSettling(false)){

            ViewCompat.postInvalidateOnAnimation(this);
        }


    }






    public CustomDragView(Context context) {
        super(context);

        initView();
    }

    public CustomDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
            if(changed){

                int count = getChildCount();

                for (int i =0 ;i<count;i++){
                    View child = getChildAt(i);

                    child.layout(i*child.getMeasuredWidth(),0,(i+1)*child.getMeasuredWidth(),child.getMeasuredHeight());

                }

            }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count  = getChildCount();
        for(int i = 0;i<count;i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
    }

    public  void initView(){

        mViewDragHelper = ViewDragHelper.create(CustomDragView.this,callback);

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return  mViewDragHelper.shouldInterceptTouchEvent(ev);

    }






    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mViewDragHelper.processTouchEvent(event);

        return true;

    }







}
