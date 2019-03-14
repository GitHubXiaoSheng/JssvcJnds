package com.app.trafficclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.trafficclient.R;

import java.util.List;

public class YwsActivityAdapter extends ArrayAdapter<Weizhang> {
    private int resourceId;


    public YwsActivityAdapter(Context context, int resource,List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Weizhang weizhang=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView=view.findViewById(R.id.gird_image);
        TextView textView=view.findViewById(R.id.gird_text);
        imageView.setImageResource(weizhang.getImg());
        textView.setText(weizhang.getText());
        return view;

    }
}