package com.app.trafficclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.usebean.Yws_Science;

import java.util.List;

public class Yws_Science_Adapter extends ArrayAdapter {
    private int resourceId;

    public Yws_Science_Adapter( Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Yws_Science yws_science=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//        TextView textView=view.findViewById(R.id.color3_tv)

        return view;

    }
}
