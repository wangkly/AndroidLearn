package com.wangky.scrollleran;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private SurfaceHolder mHolder;

    private Canvas mCanvas;

    private Boolean isDrawing = false;

    private int x =0;

    private int y =0;

    private Paint mPanit;

    private Path mPath;


    public CustomSurfaceView(Context context) {
        super(context);
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){

        mHolder= getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);

        mPanit = new Paint();
        mPath = new Path();
        mPanit.setColor(Color.RED);
        mPanit.setStyle(Paint.Style.STROKE);
        mPanit.setStrokeWidth(5);
        mPanit.setAntiAlias(true);


    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;

    }



    @Override
    public void run() {

        while (isDrawing){
            try {
                long start = System.currentTimeMillis();
                doDraw();

//                x+=1;
//                y = (int)(100*Math.sin(x*2*Math.PI/180)+400);
//                mPath.lineTo(x,y);

                long end = System.currentTimeMillis();

                if(end-start < 100){
                    Thread.sleep(100-(end-start));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }






    public void doDraw(){

        try {
            mCanvas = mHolder.lockCanvas();

            mCanvas.drawColor(Color.WHITE);

            mCanvas.drawPath(mPath,mPanit);


        }catch (Exception e){

            e.printStackTrace();

        }finally {
            if(mCanvas!=null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }




    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int ex = (int) event.getX();
        int ey = (int) event.getY();

        switch (event.getAction()){


            case MotionEvent.ACTION_DOWN:
                x = ex;
                y =ey;
                mPath.moveTo(x,y);

                break;
            case MotionEvent.ACTION_MOVE:
                x = ex;
                y =ey;

                mPath.lineTo(x,y);
                break;

        }

        return true;
    }
}
