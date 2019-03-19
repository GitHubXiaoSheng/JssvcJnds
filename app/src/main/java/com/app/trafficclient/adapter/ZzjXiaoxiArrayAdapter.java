package com.app.trafficclient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.trafficclient.R;

import java.util.List;

public class ZzjXiaoxiArrayAdapter extends ArrayAdapter<ZzjXiaoxi> {

    private int resourceId;

    public ZzjXiaoxiArrayAdapter(@NonNull Context context, int resource, @NonNull List<ZzjXiaoxi> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ZzjXiaoxi zzjXiaoxi = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        TextView textView_1 = view.findViewById(R.id.zzj_fragment_18_1_item_xuhao);
        TextView textView_2 = view.findViewById(R.id.zzj_fragment_18_1_item_leixing);
        TextView textView_3 = view.findViewById(R.id.zzj_fragment_18_1_item_yuzhi);
        TextView textView_4 = view.findViewById(R.id.zzj_fragment_18_1_item_dangqianzhi);

        textView_1.setText(zzjXiaoxi.getXuhao());
        textView_2.setText(zzjXiaoxi.getLeixing());
        textView_3.setText(zzjXiaoxi.getYuzhi());
        textView_4.setText(zzjXiaoxi.getDangqianzhi());

        return view;
    }
}
