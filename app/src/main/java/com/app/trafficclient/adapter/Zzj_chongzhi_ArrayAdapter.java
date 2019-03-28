package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.app.trafficclient.R;
import java.util.List;

public class Zzj_chongzhi_ArrayAdapter extends ArrayAdapter<Zzj_chongzhi> {

    private int resourceId;

    public Zzj_chongzhi_ArrayAdapter(Context context, int resource, List<Zzj_chongzhi> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zzj_chongzhi zzj_chongzhi = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView_1 = view.findViewById(R.id.zzj_f_7_3_xuhao);
        TextView textView_2 = view.findViewById(R.id.zzj_f_7_3_chehao);
        TextView textView_3 = view.findViewById(R.id.zzj_f_7_3_jin_E);
        TextView textView_4 = view.findViewById(R.id.zzj_f_7_3_riqi);

        textView_1.setText(zzj_chongzhi.getXuhao());
        textView_2.setText(zzj_chongzhi.getChehao());
        textView_3.setText(zzj_chongzhi.getJin_e());
        textView_4.setText(zzj_chongzhi.getRiqi());

        return view;
    }
}
