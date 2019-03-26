package com.app.trafficclient.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZL_Frag35Adapter;
import com.app.trafficclient.entry.ZL_Frag35Entry;
import com.app.trafficclient.usebean.ZL_Frag35Bean;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment_item_35 extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private GridLayoutManager manager;
    private List<ZL_Frag35Entry> entryList;
    private ZL_Frag35Adapter adapter;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layou_item_35, container, false);
        initView();
        initData();
        return view;
    }

    private void initView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.frag35_recycler);
        manager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(manager);
        entryList = new ArrayList<>();
        adapter = new ZL_Frag35Adapter(getContext(), entryList);
        recyclerView.setAdapter(adapter);

    }

    private void initData(){
        HttpRequest.post("GetSpotInfo", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ZL_Frag35Bean bean = new Gson().fromJson(jsonObject.toString(), ZL_Frag35Bean.class);
                final ZL_Frag35Bean.SpotInfo spotInfo = bean.getROWS_DETAIL().get(0);
                HttpRequest.bitmap(spotInfo.getImg(), new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        for (int i = 0; i < 5; i++) {
                            entryList.add(new ZL_Frag35Entry(spotInfo.getId(),bitmap,spotInfo.getName(),String.valueOf(spotInfo.getTicket())));
                        }
                        adapter.notifyDataSetChanged();
                    }
                },null);
            }
        },null);
    }


}
