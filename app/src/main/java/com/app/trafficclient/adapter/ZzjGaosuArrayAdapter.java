package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.trafficclient.R;

import java.util.List;

public class ZzjGaosuArrayAdapter extends ArrayAdapter<ZzjGaosu> {

    private int resourceId;
    public ZzjGaosuArrayAdapter( Context context, int resource, List<ZzjGaosu> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ZzjGaosu zzjGaosu = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView_1 = view.findViewById(R.id.zzj_fragment33_listView_item_1);
        TextView textView_2 = view.findViewById(R.id.zzj_fragment33_listView_item_2);
        TextView textView_3 = view.findViewById(R.id.zzj_fragment33_listView_item_3);

        textView_1.setText(zzjGaosu.getText1());
        textView_2.setText(zzjGaosu.getText2());
        textView_3.setText(zzjGaosu.getText3());

        return view;
    }
}
