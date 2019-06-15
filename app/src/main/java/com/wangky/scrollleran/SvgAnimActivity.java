package com.wangky.scrollleran;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SvgAnimActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView svg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.svg_anim_layout);

        svg = findViewById(R.id.svg);

        svg.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.svg:

                Drawable drawable = svg.getDrawable();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }

              break;

        }

    }


}
