package com.wangky.scrollleran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MRecyclerViewActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrecycler_view);

        mRecyclerView = findViewById(R.id.recycler);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//
//        lineM.setOrientation(LinearLayoutManager.HORIZONTAL);


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);



        mRecyclerView.setLayoutManager(layoutManager);

        List<RecyclerItem> list = new ArrayList<>();

        for (int i =0 ;i < 20;i++){

            RecyclerItem item = new RecyclerItem("wangkly"+i);

            list.add(item);
        }


        RecyclerListAdapter adapter = new RecyclerListAdapter(list);

        mRecyclerView.setAdapter(adapter);


    }
}
