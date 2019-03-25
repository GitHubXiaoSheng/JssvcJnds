package com.app.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.app.trafficclient.R;
import com.app.trafficclient.entry.Bus;
import com.app.trafficclient.entry.Frag28_Adapter;
import com.app.trafficclient.entry.PlatformEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_28 extends Fragment {
    private String TAG = "TAG";
    private View view;
    private ExpandableListView listView;
    private Frag28_Adapter adapter;
    private List<PlatformEntry> entryList;
    private List<Bus> busList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_item_28,container,false);
        initView();
        return view;

    }

    private void initView(){
        entryList = new ArrayList<>();
        busList = new ArrayList<>();
        listView = (ExpandableListView) view.findViewById(R.id.frag28_expandlist);
        for (int i=0;i<2 ;i++) {
            for (int j=0;j<(int)(Math.random()*10) ;j++) {
                Bus bus = new Bus(String.valueOf(i+1)+"号站台",String.valueOf((int)(Math.random()*1000+100))+"米");
                busList.add(bus);
            }
            entryList.add(new PlatformEntry(true,String.valueOf(i+1)+"号站台",busList));
        }
        adapter = new Frag28_Adapter(getContext(), entryList);
        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                ImageView imageView = (ImageView) view.findViewById(R.id.frag28item_1_list_img);
                Log.d(TAG, "onGroupClick: isopen1="+entryList.get(i).isOpen());
                if(entryList.get(i).isOpen()){
                    imageView.setImageResource(R.drawable.dayu_2);
                    entryList.get(i).setOpen(false);
                }else {
                    imageView.setImageResource(R.drawable.dayu);
                    entryList.get(i).setOpen(true);
                }
                Log.d(TAG, "onGroupClick: isopen2="+entryList.get(i).isOpen());
                return false;
            }
        });
    }


}
