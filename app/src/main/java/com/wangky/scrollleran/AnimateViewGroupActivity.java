package com.wangky.scrollleran;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

public class AnimateViewGroupActivity extends AppCompatActivity implements View.OnClickListener{

    private Button first;

    private Button second;

    private Button third;

    private Button main;

    private Boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.group_layout);

        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        main = findViewById(R.id.main);

        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);
        main.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.main:
                if(flag){
                    startAnima();

                }else{
                    closeAnima();
                }


              break;

        }

    }


    public void startAnima(){

        ObjectAnimator oba1 = ObjectAnimator.ofFloat(first,"translationX",-300);
        ObjectAnimator oba2 = ObjectAnimator.ofFloat(second,"translationX",-150);
        ObjectAnimator oba3 = ObjectAnimator.ofFloat(second,"translationY",-150);
        ObjectAnimator oba4 = ObjectAnimator.ofFloat(third,"translationY",-300);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
//        set.setInterpolator(new AccelerateDecelerateInterpolator());
//        set.setInterpolator(new AccelerateInterpolator());
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.playTogether(oba1,oba2,oba3,oba4);
        set.start();
        flag =false;
    }



    public void closeAnima(){
        ObjectAnimator oba1 = ObjectAnimator.ofFloat(first,"translationX",0);
        ObjectAnimator oba2 = ObjectAnimator.ofFloat(second,"translationX",0);
        ObjectAnimator oba3 = ObjectAnimator.ofFloat(second,"translationY",0);
        ObjectAnimator oba4 = ObjectAnimator.ofFloat(third,"translationY",0);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(oba1,oba2,oba3,oba4);
        set.start();
        flag =true;
    }
}
