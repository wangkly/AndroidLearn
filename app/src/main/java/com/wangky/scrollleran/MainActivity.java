package com.wangky.scrollleran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mScrollerActivity;

    private Button AnimationActivity;

//    private Button AnimateViewGroupActivity;

    private Button IconAnimActivity;

    private Button SvgAnimActivity;

    private Button AlbumActivity;

    private Button MyListViewActivity;

    private Button mRecyclerView;

    private Boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mScrollerActivity = findViewById(R.id.mScrollerActivity);
        AnimationActivity = findViewById(R.id.AnimationActivity);
//        AnimateViewGroupActivity = findViewById(R.id.AnimateViewGroupActivity);
        IconAnimActivity = findViewById(R.id.IconAnimActivity);
        SvgAnimActivity = findViewById(R.id.SvgAnimActivity);
        AlbumActivity = findViewById(R.id.AlbumActivity);
        MyListViewActivity = findViewById(R.id.MyListView);
        mRecyclerView = findViewById(R.id.mRecyclerView);

        mScrollerActivity.setOnClickListener(this);
        AnimationActivity.setOnClickListener(this);
//        AnimateViewGroupActivity.setOnClickListener(this);
        IconAnimActivity.setOnClickListener(this);
        SvgAnimActivity.setOnClickListener(this);
        AlbumActivity.setOnClickListener(this);
        MyListViewActivity.setOnClickListener(this);
        mRecyclerView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.mScrollerActivity:

                Intent intent = new Intent(MainActivity.this,ScrollerActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("name","wangkly");
                intent.putExtra("data",bundle);

                startActivity(intent);

              break;

            case R.id.AnimationActivity:

                Intent intent2 = new Intent(MainActivity.this,AnimationActivity.class);

                startActivity(intent2);

                break;

//            case R.id.AnimateViewGroupActivity:
//
//                Intent intent3 = new Intent(MainActivity.this,AnimateViewGroupActivity.class);
//
//                startActivity(intent3);
//
//                break;

            case R.id.IconAnimActivity:

                Intent intent4 = new Intent(MainActivity.this,IconAnimActivity.class);

                startActivity(intent4);

                break;

            case R.id.SvgAnimActivity:

                Intent intent5 = new Intent(MainActivity.this,SvgAnimActivity.class);

                startActivity(intent5);

                break;
            case R.id.AlbumActivity:

                Intent intent6 = new Intent(MainActivity.this,AlbumActivity.class);

                startActivity(intent6);

                break;


            case R.id.MyListView:

                Intent intent7 = new Intent(MainActivity.this,MyListViewActivity.class);

                startActivity(intent7);

                break;


            case R.id.mRecyclerView:

                Intent intent8 = new Intent(MainActivity.this,MRecyclerViewActivity.class);

                startActivity(intent8);

                break;
        }

    }


}
