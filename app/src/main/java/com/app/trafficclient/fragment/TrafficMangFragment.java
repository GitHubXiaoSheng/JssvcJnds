package com.app.trafficclient.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.TrafficMangAdapter;
import com.app.trafficclient.entry.TrafficMangItem;
import com.app.trafficclient.usebean.TrafficConfig;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TrafficMangFragment extends Fragment {
    private final String TAG = "TrafficMangFragment";
    private View view;
    private Spinner spinner;
    private Button queryBtn;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private TrafficMangAdapter adapter;
    private List<TrafficMangItem> trafficList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_traffic_mang, container, false);
        initView();
        initData(1);
        setListener();
        return view;
    }

    private void initView(){
        spinner = (Spinner)view.findViewById(R.id.trafficmang_sort_spinner);
        queryBtn = (Button) view.findViewById(R.id.trafficmang_query_btn);
        recyclerView = (RecyclerView) view.findViewById(R.id.trafficmang_recycler);
        manager = new LinearLayoutManager(MyApplication.appContext);
        recyclerView.setLayoutManager(manager);
        trafficList = new ArrayList<>();
        adapter = new TrafficMangAdapter(trafficList);
        recyclerView.setAdapter(adapter);
    }

    private void setListener(){
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort(spinner.getSelectedItemPosition());
            }
        });
    }

    private void initData(final int road){
        String param = "{\"TrafficLightId\":\""+String.valueOf(road)+"\", \"UserName\":\"user1\"}";
        HttpRequest.post("GetTrafficLightConfigAction", param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                TrafficConfig config = new Gson().fromJson(jsonObject.toString(),TrafficConfig.class);
                if(config.getRESULT().equals("S")){
                    trafficList.add(new TrafficMangItem(String.valueOf(road),config.getRedTime(),config.getYellowTime(),config.getGreenTime()));
                    initData(road+1);
                }else {
                    adapter.notifyDataSetChanged();
                    return;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyApplication.appContext,"请求出现错误！",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sort(int type){
        switch (type){
            case 0:
                Collections.sort(trafficList, new Comparator<TrafficMangItem>() {
                    @Override
                    public int compare(TrafficMangItem item, TrafficMangItem t1) {
                        return new Integer(item.getRoad()).compareTo(Integer.valueOf(t1.getRoad()));
                    }
                });
                break;
            case 1:
                Collections.sort(trafficList, new Comparator<TrafficMangItem>() {
                    @Override
                    public int compare(TrafficMangItem item, TrafficMangItem t1) {
                        return new Integer(t1.getRoad()).compareTo(Integer.valueOf(item.getRoad()));
                    }
                });
                break;
            case 2:
                Collections.sort(trafficList, new Comparator<TrafficMangItem>() {
                    @Override
                    public int compare(TrafficMangItem item, TrafficMangItem t1) {
                        return new Integer(item.getRedTime()).compareTo(Integer.valueOf(t1.getRedTime()));
                    }
                });
                break;
            case 3:
                Collections.sort(trafficList, new Comparator<TrafficMangItem>() {
                    @Override
                    public int compare(TrafficMangItem item, TrafficMangItem t1) {
                        return new Integer(t1.getRedTime()).compareTo(Integer.valueOf(item.getRedTime()));
                    }
                });
                break;
            case 4:
                Collections.sort(trafficList, new Comparator<TrafficMangItem>() {
                    @Override
                    public int compare(TrafficMangItem item, TrafficMangItem t1) {
                        return new Integer(item.getYellowTime()).compareTo(Integer.valueOf(t1.getYellowTime()));
                    }
                });
                break;
            case 5:
                Collections.sort(trafficList, new Comparator<TrafficMangItem>() {
                    @Override
                    public int compare(TrafficMangItem item, TrafficMangItem t1) {
                        return new Integer(t1.getYellowTime()).compareTo(Integer.valueOf(item.getYellowTime()));
                    }
                });
                break;
            case 6:
                Collections.sort(trafficList, new Comparator<TrafficMangItem>() {
                    @Override
                    public int compare(TrafficMangItem item, TrafficMangItem t1) {
                        return new Integer(item.getGreenTime()).compareTo(Integer.valueOf(t1.getGreenTime()));
                    }
                });
                break;
            case 7:
                Collections.sort(trafficList, new Comparator<TrafficMangItem>() {
                    @Override
                    public int compare(TrafficMangItem item, TrafficMangItem t1) {
                        return new Integer(t1.getGreenTime()).compareTo(Integer.valueOf(item.getGreenTime()));
                    }
                });
                break;
            default:break;
        }
        adapter.notifyDataSetChanged();
    }
}
