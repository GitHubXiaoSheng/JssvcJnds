package com.app.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZzjWeizhangshipinArrayAdapter;
import com.app.trafficclient.adapter.Zzjweizhangshipin;
import java.util.ArrayList;
import java.util.List;

public class Fragment_item_30 extends Fragment {

    private List<Zzjweizhangshipin> zzjweizhangshipinList = new ArrayList<>();
    private ZzjWeizhangshipinArrayAdapter zzjWeizhangshipinArrayAdapter;
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_30, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = getActivity().findViewById(R.id.zzj_fragment30_GridView);
        addData();
        zzjWeizhangshipinArrayAdapter = new ZzjWeizhangshipinArrayAdapter(getContext(),R.layout.zzj_fragment30_item,zzjweizhangshipinList);
        gridView.setAdapter(zzjWeizhangshipinArrayAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    private void addData() {
        for (int i = 0; i < 5; i++) {
            Zzjweizhangshipin zzjweizhangshipin = new Zzjweizhangshipin(R.drawable.zzj_fragment30_youku_logo, "视频名称");
            zzjweizhangshipinList.add(zzjweizhangshipin);
        }
    }

}