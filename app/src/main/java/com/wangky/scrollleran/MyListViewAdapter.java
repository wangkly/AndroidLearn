package com.wangky.scrollleran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MyListViewAdapter extends SimpleAdapter {


    private LayoutInflater mlayoutInflator;

    private Context mContext;

    private  List<Map<String, String>> mList;




    class ViewHolder {

        TextView tv;

    }



    public MyListViewAdapter(Context context, List<Map<String, String>> data,
                             int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);

        mContext = context;

        mlayoutInflator = LayoutInflater.from(context);

        mList = data;

    }


    public void addDataSource(List<Map<String, String>> data){

        mList.addAll(data);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view =  null;
        ViewHolder holder;
        if(convertView == null){
            view = mlayoutInflator.inflate(R.layout.my_list_item,parent,false);

            //Viewholder 为了不每次都findViewById获取实例
            holder =new ViewHolder();
            holder.tv = view.findViewById(R.id.item_name);

            view.setTag(holder);

        }else{

            view = convertView;
            holder = (ViewHolder) convertView.getTag();

        }

        holder.tv.setText(mList.get(position).get("name"));

        return view;
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
