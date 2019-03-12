package com.app.trafficclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.trafficclient.MyApplication;
import com.app.trafficclient.R;
import com.app.trafficclient.entry.TrafficMangItem;

import java.util.ArrayList;
import java.util.List;

public class TrafficMangAdapter extends RecyclerView.Adapter<TrafficMangAdapter.ViewHolder> {
    List<TrafficMangItem> itemList;

    public TrafficMangAdapter(List<TrafficMangItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.appContext).inflate(R.layout.item_trafficmang_frag2,
                parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrafficMangItem item = itemList.get(position);
        holder.roadTV.setText(item.getRoad());
        holder.redTV.setText(item.getRedTime());
        holder.yellowTV.setText(item.getYellowTime());
        holder.greenTV.setText(item.getGreenTime());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView roadTV;
        private TextView redTV;
        private TextView yellowTV;
        private TextView greenTV;
        public ViewHolder(View itemView) {
            super(itemView);
            roadTV = (TextView) itemView.findViewById(R.id.item_trafficmang_road_tv);
            redTV = (TextView) itemView.findViewById(R.id.item_trafficmang_red_tv);
            yellowTV = (TextView) itemView.findViewById(R.id.item_trafficmang_yellow_tv);
            greenTV = (TextView) itemView.findViewById(R.id.item_trafficmang_green_tv);
        }
    }
}
