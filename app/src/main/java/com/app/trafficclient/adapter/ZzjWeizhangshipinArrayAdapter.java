package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.trafficclient.R;

import java.util.List;

public class ZzjWeizhangshipinArrayAdapter extends ArrayAdapter<Zzjweizhangshipin> {

    private int resourceId;

    public ZzjWeizhangshipinArrayAdapter( Context context, int resource, List<Zzjweizhangshipin> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Zzjweizhangshipin zzjweizhangshipin = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView = view.findViewById(R.id.zzj_fragment30_item_imageView);
        TextView textView = view.findViewById(R.id.zzj_fragment30_item_textView);

        imageView.setImageResource(zzjweizhangshipin.getImage());
        textView.setText(zzjweizhangshipin.getName());

        return view;
    }
}
