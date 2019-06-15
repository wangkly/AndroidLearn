package com.wangky.scrollleran;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListViewActivity extends AppCompatActivity implements MyListView.OnRefreshListener{


    private MyListView myList;

    private MyListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list_view_layout);
        myList = findViewById(R.id.myList);

        List<Map<String,String>> mlist = new ArrayList<>();


        for(int i = 0 ;i < 20; i++){
            Map<String,String> map = new HashMap<>();
            map.put("name","wangkly"+i);
            mlist.add(map);
        }


        adapter= new MyListViewAdapter(MyListViewActivity.this,
                                mlist,R.layout.my_list_item,
                            new String[]{"name"},new int[]{R.id.item_name});



        myList.setAdapter(adapter);

        myList.setOnRefreshListener(this);


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyListViewActivity.this,"item click",Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public void onPullRefresh() {

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myList.refreshComplete();

            }
        },3000);

    }

    @Override
    public void onLoadingMore() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                List<Map<String,String>> mlist = new ArrayList<>();

                for(int i = 0 ;i < 20; i++){
                    Map<String,String> map = new HashMap<>();
                    map.put("name","lalala"+i);
                    mlist.add(map);
                }

                adapter.addDataSource(mlist);

                adapter.notifyDataSetChanged();


                myList.loadComplete();

            }
        },3000);
    }
}
