package com.wangky.scrollleran;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{

    private  View myView;

    private  Button translate;

    private  Button rotate;

    private  Button scale;

    private  Button pivot;

    private  Button alpha;

    private  Button propertyHolder;

    private  Button valueAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.scroll);
//        final LinearLayout lay = findViewById(R.id.lay);
//
//        Button mScrollTo = findViewById(R.id.scrollTo);
//
//        Button mScrollBy = findViewById(R.id.scrollBy);
//
//
//        mScrollTo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lay.scrollTo(-200,-80);
//            }
//        });
//
//
//        mScrollBy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lay.scrollBy(-200,-80);
//            }
//        });


        setContentView(R.layout.animate_layout);

        //布局动画
        LinearLayout myLine = findViewById(R.id.myLine);

        ScaleAnimation sa = new ScaleAnimation(0,1,0,1);

        sa.setDuration(2000);

        LayoutAnimationController la = new LayoutAnimationController(sa,0.5f);

        la.setOrder(LayoutAnimationController.ORDER_RANDOM);
       

        myLine.setLayoutAnimation(la);


        myView= findViewById(R.id.myView);
        translate = findViewById(R.id.translate);
        rotate = findViewById(R.id.rotate);
        scale = findViewById(R.id.scale);
        pivot = findViewById(R.id.pivot);
        alpha =findViewById(R.id.alpha);
        propertyHolder = findViewById(R.id.propertyHolder);

        valueAnim = findViewById(R.id.valueAnim);


        translate.setOnClickListener(this);
        rotate.setOnClickListener(this);
        scale.setOnClickListener(this);
        pivot.setOnClickListener(this);
        alpha.setOnClickListener(this);
        propertyHolder.setOnClickListener(this);

        valueAnim.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.translate :

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myView,"translationX",200);
//                objectAnimator.setDuration(1000);
//                objectAnimator.start();

                ObjectAnimator yanim = ObjectAnimator.ofFloat(myView,"translationY",200);
//                yanim.setDuration(1000);
//                yanim.start();

                AnimatorSet aset = new AnimatorSet();

                aset.setDuration(1000);

                aset.playTogether(objectAnimator,yanim);

                aset.start();

                break;


            case R.id.rotate :

//                ObjectAnimator rotate = ObjectAnimator.ofFloat(myView,"rotation",60);
//                ObjectAnimator rotate = ObjectAnimator.ofFloat(myView,"rotationX",60);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(myView,"rotationY",60);

                rotate.setDuration(1000);
                rotate.setRepeatCount(ValueAnimator.INFINITE);
                rotate.setRepeatMode(ValueAnimator.RESTART);
                rotate.start();


                break;

            case R.id.scale:

                ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(myView,"ScaleX",1f,0,1f);
                ObjectAnimator scaleAnim2 = ObjectAnimator.ofFloat(myView,"ScaleY",1f,0,1f);

                scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
                scaleAnim.setRepeatMode(ValueAnimator.REVERSE);

                scaleAnim2.setRepeatCount(ValueAnimator.INFINITE);
                scaleAnim2.setRepeatMode(ValueAnimator.REVERSE);

//                scaleAnim.setDuration(1000);
//                scaleAnim.start();
//
//                scaleAnim2.setDuration(1000);
//                scaleAnim2.start();


                AnimatorSet aset2 = new AnimatorSet();

                aset2.setDuration(1000);

                aset2.playTogether(scaleAnim,scaleAnim2);

                aset2.start();


                break;


            case R.id.pivot:

                ObjectAnimator pivotAnim = ObjectAnimator.ofFloat(myView,"pivotX",1f,0,1f);
                pivotAnim.setDuration(1000);
                pivotAnim.start();


                ObjectAnimator pivotAnim2 = ObjectAnimator.ofFloat(myView,"pivotY",1f,0,1f);
                pivotAnim2.setDuration(1000);
                pivotAnim2.start();



                break;


            case R.id.alpha:

                ObjectAnimator alphaAnim= ObjectAnimator.ofFloat(myView,"Alpha",1f,0,1f);
                alphaAnim.setDuration(1000);

                alphaAnim.start();

                break;


            case  R.id.propertyHolder:

                PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("TranslationX",100,0);
                PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("TranslationY",100,0);
                PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("rotationX",180);
                PropertyValuesHolder pvh4 = PropertyValuesHolder.ofFloat("ScaleX",1f,0,1f);
                PropertyValuesHolder pvh5 = PropertyValuesHolder.ofFloat("ScaleY",1f,0,1f);
                PropertyValuesHolder pvh6 = PropertyValuesHolder.ofFloat("Alpha",1f,0,1f);

                ObjectAnimator.ofPropertyValuesHolder(myView,pvh1,pvh2,pvh3,pvh4,pvh5,pvh6).setDuration(6000).start();


                break;


            case R.id.valueAnim :


                ValueAnimator valueA = ValueAnimator.ofInt(200,400);

                valueA.setDuration(1000);

                valueA.start();


                valueA.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                       int va = (int) animation.getAnimatedValue();

                       myView.getLayoutParams().width = va;
                       myView.getLayoutParams().height =va;

                       myView.requestLayout();

                    }
                });


                break;

        }

    }
}
