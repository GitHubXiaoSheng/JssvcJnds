package com.app.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.trafficclient.R;

import java.util.ArrayList;
import java.util.List;

public class Yws_Sport_Fragment extends Fragment {
    List<String> sciencelist=new ArrayList<>();
    private ArrayAdapter<String> scienceadapter;
    private ListView science_listview;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yws__sport, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        science_listview=getActivity().findViewById(R.id.sport_listview);
        sciencelist.add("张占中：已停止业务 向美申请展示");
        sciencelist.add("张占中：已停止业务 向美申请展示");
        sciencelist.add("张占中：已停止业务 向美申请展示");
        sciencelist.add("张占中：已停止业务 向美申请展示");
        sciencelist.add("张占中：已停止业务 向美申请展示");
        sciencelist.add("张占中：已停止业务 向美申请展示");
        sciencelist.add("张占中：已停止业务 向美申请展示");

        scienceadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,sciencelist);
        science_listview.setAdapter(scienceadapter);
    }
}
