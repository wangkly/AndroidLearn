package com.wangky.scrollleran;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class AnimateViewGroup extends ViewGroup {



    public AnimateViewGroup(Context context) {
        this(context,null);
    }

    public AnimateViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnimateViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.AnimateViewGroup);

        if(ta !=null){
            //自定义属性
           float rightPadding = ta.getDimension(R.styleable.AnimateViewGroup_rightPadding,0);
           int orientation = ta.getInt(R.styleable.AnimateViewGroup_orientation,0);


            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for(int i =0;i<count;i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int offset =getMeasuredWidth()/2;
        if (changed){
        int count = getChildCount();
            for (int i =0;i<count;i++){
                View child = getChildAt(i);
//                child.layout(i*child.getMeasuredWidth(),0,(i+1)*child.getMeasuredWidth(),child.getMeasuredHeight());
                child.layout(offset,offset,child.getMeasuredWidth()+offset,child.getMeasuredHeight()+offset);
            }

        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

               switch (event.getAction()){

                   case MotionEvent.ACTION_DOWN:

                       System.out.println(event);



                       break;

               }

        return true;


    }
}
