package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.app.trafficclient.R;
import java.util.List;

public class FankuiAdapter extends ArrayAdapter<Fankui> {

    private int resourceId;
    public FankuiAdapter(Context context, int resource, List<Fankui> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fankui fankui = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        TextView textView_title = view.findViewById(R.id.fankui_title);
        TextView textView_time = view.findViewById(R.id.fankui_time);

        textView_title.setText(fankui.getTitle());
        textView_time.setText(fankui.getTiem());

        return view;
    }
}
