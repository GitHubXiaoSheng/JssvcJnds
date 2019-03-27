package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.app.trafficclient.R;
import java.util.List;

public class Zzj_Dingzhi_bancheAdapter extends ArrayAdapter<Zzj_Dingzhi_banche> {

    private int resourceId;

    public Zzj_Dingzhi_bancheAdapter(Context context, int resource, List<Zzj_Dingzhi_banche> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zzj_Dingzhi_banche dingzhi_banche = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        TextView textView_1 = view.findViewById(R.id.zzj_textView_chehao);
        TextView textView_2 = view.findViewById(R.id.zzj_textView_qishidian);
        TextView textView_3 = view.findViewById(R.id.zzj_textView_qidian_time);
        TextView textView_4 = view.findViewById(R.id.zzj_textView_piaojia_licheng);
        TextView textView_5 = view.findViewById(R.id.zzj_textView_zhongdian_time);

        textView_1.setText(dingzhi_banche.getChehao());
        textView_2.setText(dingzhi_banche.getQishidian());
        textView_3.setText(dingzhi_banche.getQidian_time());
        textView_4.setText(dingzhi_banche.getPiaojia_licheng());
        textView_5.setText(dingzhi_banche.getZhongdian_time());

        return view;
    }
}
