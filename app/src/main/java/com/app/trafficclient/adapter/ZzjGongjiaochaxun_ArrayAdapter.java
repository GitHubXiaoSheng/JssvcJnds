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

public class ZzjGongjiaochaxun_ArrayAdapter extends ArrayAdapter<ZzjGongjiaochaxun> {

    public int resourceId;
    public ZzjGongjiaochaxun_ArrayAdapter(@NonNull Context context, int resource, @NonNull List<ZzjGongjiaochaxun> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ZzjGongjiaochaxun zzjGongjiaochaxun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView_1 = view.findViewById(R.id.zzj_textView_dangqianrenshu);
        TextView textView_2 = view.findViewById(R.id.zzj_textView_daoda_time);
        TextView textView_3 = view.findViewById(R.id.zzj_textView_daoda_juli);

        textView_1.setText(zzjGongjiaochaxun.getId());
        textView_2.setText(zzjGongjiaochaxun.getTime());
        textView_3.setText(zzjGongjiaochaxun.getJuli());

        return view;
    }
}
