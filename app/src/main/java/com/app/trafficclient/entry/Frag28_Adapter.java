package com.app.trafficclient.entry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.app.trafficclient.R;

import java.util.List;


public class Frag28_Adapter extends BaseExpandableListAdapter {
    private Context context;
    private List<PlatformEntry> entryList;

    public Frag28_Adapter(Context context, List<PlatformEntry> entryList) {
        this.context = context;
        this.entryList = entryList;
    }

    @Override
    public int getGroupCount() {
        return entryList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return entryList.get(i).getBusList().size();
    }

    @Override
    public Object getGroup(int i) {
        return entryList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return entryList.get(i).getBusList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.frag28_item_1,viewGroup,false);
            TextView textView = (TextView) view.findViewById(R.id.frag28item_1_platform_tv);

//            Log.d("TAG", "getGroupView: b="+b);
            PlatformEntry entry = entryList.get(i);
            textView.setText(entry.getPlatformNum());
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.frag28_item_2,viewGroup,false);
            TextView busNum = (TextView) view.findViewById(R.id.frag28item_2_busnum_tv);
            TextView busInfo = (TextView) view.findViewById(R.id.frag28item_2_businfo_tv);
            Bus bus = entryList.get(1).getBusList().get(i1);
            busNum.setText(bus.getBusNum());
            busInfo.setText(bus.getDistance());

        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
