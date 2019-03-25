package com.app.trafficclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZzjGaosu;
import com.app.trafficclient.adapter.ZzjGaosuArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_item_33 extends Fragment {

    private ListView listView;
    private List<ZzjGaosu> zzjGaosuList = new ArrayList<>();
    private ZzjGaosuArrayAdapter zzjGaosuArrayAdapter;
    private ZzjGaosu zzjGaosu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_33, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addData();
        listView = getActivity().findViewById(R.id.zzj_fragment33_listView);
        zzjGaosuArrayAdapter = new ZzjGaosuArrayAdapter(getContext(),R.layout.zzj_fragment33_listview,zzjGaosuList);
        listView.setAdapter(zzjGaosuArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"当前"+zzjGaosuList.get(i).getText1() + zzjGaosuList.get(i).getText3(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addData() {
        for (int i = 0; i < 5; i++) {
            zzjGaosu = new ZzjGaosu("G18荣乌高速", "荣乌高速", "正常");
            zzjGaosuList.add(zzjGaosu);
        }
    }

}
