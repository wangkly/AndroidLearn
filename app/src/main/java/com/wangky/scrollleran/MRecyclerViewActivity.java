package com.wangky.scrollleran;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MRecyclerViewActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrecycler_view);

        mRecyclerView = findViewById(R.id.recycler);

        mSwipeRefresh = findViewById(R.id.swipeRefresh);

        mSwipeRefresh.setColorSchemeColors(Color.GREEN);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//
//        lineM.setOrientation(LinearLayoutManager.HORIZONTAL);


//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        GridLayoutManager layoutManager = new GridLayoutManager(MRecyclerViewActivity.this,2,GridLayout.VERTICAL,false);


        mRecyclerView.setLayoutManager(layoutManager);

        final List<RecyclerItem> list = new ArrayList<>();

        for (int i =0 ;i < 20;i++){

            RecyclerItem item = new RecyclerItem("wangkly"+i);

            list.add(item);
        }


        final RecyclerListAdapter adapter = new RecyclerListAdapter(list);

        mRecyclerView.setAdapter(adapter);


        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //处理刷新的逻辑

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MRecyclerViewActivity.this,"刷新成功",Toast.LENGTH_LONG).show();
                        //请求数据
                        mSwipeRefresh.setRefreshing(false);

                    }
                },3000);


            }
        });


        mRecyclerView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public void loadMore() {

                adapter.setLoadState(adapter.LOADING);

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(list.size() >= 60){
                            adapter.setLoadState(adapter.LOAD_END);
                            return;
                        }

                        for (int i =0 ;i < 20;i++){
                            RecyclerItem item = new RecyclerItem("wanggang"+i);
                            list.add(item);

                            }

                     adapter.setLoadState(adapter.LOAD_COMPLETE);
                    }
                },3000);

            }

        });


    }
}
