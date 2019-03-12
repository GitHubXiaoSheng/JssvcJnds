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

public class ZzjChongzhiAdapter extends ArrayAdapter<ZzjChongzhi> {

    private int resourceId;

    public ZzjChongzhiAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ZzjChongzhi zzjChongzhi = (ZzjChongzhi) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView_1 = view.findViewById(R.id.zzj_item_xuhao);
        TextView textView_2 = view.findViewById(R.id.zzj_item_chehao);
        TextView textView_3 = view.findViewById(R.id.zzj_item_chongzhijine);
        TextView textView_4 = view.findViewById(R.id.zzj_item_caozuoren);
        TextView textView_5 = view.findViewById(R.id.zzj_item_chongzhishijian);

        textView_1.setText(zzjChongzhi.getXuhao());
        textView_2.setText(zzjChongzhi.getChehao());
        textView_3.setText(zzjChongzhi.getChongzhijine());
        textView_4.setText(zzjChongzhi.getCaozuoren());
        textView_5.setText(zzjChongzhi.getChongzhishijian());

        return view;
    }
}
