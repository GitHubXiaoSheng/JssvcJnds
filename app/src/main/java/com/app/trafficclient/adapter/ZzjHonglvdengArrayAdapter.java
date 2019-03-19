package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.trafficclient.R;

import java.util.List;

public class ZzjHonglvdengArrayAdapter extends ArrayAdapter<ZzjHonglvdeng> {

    private int resourceId;

    public ZzjHonglvdengArrayAdapter(Context context, int resource,List<ZzjHonglvdeng> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        ZzjHonglvdeng zzjHonglvdeng = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView_1 = view.findViewById(R.id.zzj_fragment_21_item_lukou);
        TextView textView_2 = view.findViewById(R.id.zzj_fragment_21_item_hongdeng);
        TextView textView_3 = view.findViewById(R.id.zzj_fragment_21_item_huangdeng);
        TextView textView_4 = view.findViewById(R.id.zzj_fragment_21_item_lvdeng);

        textView_1.setText(zzjHonglvdeng.getLukou());
        textView_2.setText(zzjHonglvdeng.getHongdeng());
        textView_3.setText(zzjHonglvdeng.getHuangdeng());
        textView_4.setText(zzjHonglvdeng.getLvdeng());

        return view;
    }
}
