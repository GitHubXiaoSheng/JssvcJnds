package com.app.trafficclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.activity.ZL_MetroInfoActivity;
import com.app.trafficclient.adapter.Zl_Frag32_Adapter;
import com.app.trafficclient.entry.ZL_MetroEntry;
import com.app.trafficclient.usebean.ZL_Metro;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_32 extends Fragment {
    private String TAG = "TAG";
    private View view;
    private TextView metroPlan;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private Zl_Frag32_Adapter adapter;
    private List<ZL_Metro.Metro> metroList;
    private List<ZL_MetroEntry> entryList;
    private ZL_Metro.Metro metro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_item_32, container, false);
        initView();
        initData();
        return view;
    }

    private void initView(){
        metroPlan = (TextView) view.findViewById(R.id.frag32_metroplan_tv);
        metroPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ZL_MetroInfoActivity.class);
                intent.putExtra("MetroId", entryList.get(0).getId());
                Log.d("TAG", "onClick: id="+entryList.get(0).getId());
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.frag32_recycler);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        entryList = new ArrayList<>();
        adapter = new Zl_Frag32_Adapter(getContext(),entryList);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        HttpRequest.post("GetMetroInfo", "{\"Line\":\"0\",\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ZL_Metro metroBean = new Gson().fromJson(jsonObject.toString(), ZL_Metro.class);
                metroList = metroBean.getROWS_DETAIL();
                for (int i = 0; i < metroList.size(); i++) {
                    entryList.add(new ZL_MetroEntry(metroList.get(i).getId(), metroList.get(i).getName()));
                }
                Log.d(TAG, "onResponse: "+metroBean.toString());
                adapter.notifyDataSetChanged();
            }
        },null);
    }

}
