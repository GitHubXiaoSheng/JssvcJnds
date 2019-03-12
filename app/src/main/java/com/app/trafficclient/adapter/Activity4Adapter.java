package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.usebean.MyWeather;

import java.util.List;

public class Activity4Adapter extends BaseAdapter {
    private Context context;
    private List<MyWeather> myWeathers;

    public Activity4Adapter(Context context, List<MyWeather> myWeathers) {
        this.context = context;
        this.myWeathers = myWeathers;
    }

    @Override
    public int getCount() {
        return myWeathers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity4_list_item, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        holder.tv_date.setText(myWeathers.get(position).getDate());
        holder.tv_tem.setText(myWeathers.get(position).getTem()+"℃");
        holder.tv_type.setText(myWeathers.get(position).getType());
        if (myWeathers.get(position).getType().equals("晴")){
            holder.tv_image.setBackgroundResource(R.drawable.w001);
        }else if (myWeathers.get(position).getType().equals("阴")){
            holder.tv_image.setBackgroundResource(R.drawable.w002);
        }else {
            holder.tv_image.setBackgroundResource(R.drawable.w003);

        }


        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv_date;
        public TextView tv_image;
        public TextView tv_type;
        public TextView tv_tem;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            this.tv_image = (TextView) rootView.findViewById(R.id.tv_image);
            this.tv_type = (TextView) rootView.findViewById(R.id.tv_type);
            this.tv_tem = (TextView) rootView.findViewById(R.id.tv_tem);
        }

    }
}
