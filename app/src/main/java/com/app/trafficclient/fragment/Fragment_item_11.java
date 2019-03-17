package com.app.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZL_Frag11Adapter;
import com.app.trafficclient.entry.ZL_Frag11_TrafficMangEntry;
import com.app.trafficclient.usebean.ZLTrafficConfig;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_11 extends Fragment {
    private String TAG = "Fragment_item_11";
    private View view;
    private Spinner spinner;
    private Button queryBtn,batchBtn;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private List<ZL_Frag11_TrafficMangEntry> entryList;
    private ZL_Frag11Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_layout_11, container, false);
        initView();
        setListeners();
        initData(1);
        return view;
    }

    private void initView(){
        spinner = (Spinner) view.findViewById(R.id.frag11_spinner);
        queryBtn = (Button) view.findViewById(R.id.frag11_query_btn);
        batchBtn = (Button) view.findViewById(R.id.frag11_batch_btn);
        recyclerView = (RecyclerView) view.findViewById(R.id.frag11_recycler);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        entryList = new ArrayList<>();
        adapter = new ZL_Frag11Adapter(getContext(),entryList);
        recyclerView.setAdapter(adapter);
    }

    private void initData(final int i){
        String param = "{\"TrafficLightId\":\""+String.valueOf(i)+"\", \"UserName\":\"user1\"}";
        HttpRequest.post("GetTrafficLightConfigAction", param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ZLTrafficConfig config = new Gson().fromJson(jsonObject.toString(), ZLTrafficConfig.class);
                if (config.getRESULT().equals("S")) {
                    if(config != null){
                        entryList.add(new ZL_Frag11_TrafficMangEntry(i,Integer.valueOf(config.getRedTime()),Integer.valueOf(config.getYellowTime()),
                                Integer.valueOf(config.getGreenTime()),false));
                    }
                    initData(i+1);
                }else {
                    adapter.notifyDataSetChanged();
                    return;
                }
            }
        },null);
    }

    private void setListeners(){
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort(spinner.getSelectedItemPosition());
            }
        });
        batchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.zl_frag11_dialog, null);
                final EditText redEt = (EditText) view1.findViewById(R.id.frag11_dialog_red_et);
                final EditText yellowEt = (EditText) view1.findViewById(R.id.frag11_dialog_yellow_et);
                final EditText greenEt = (EditText) view1.findViewById(R.id.frag11_dialog_green_et);
                Button okBtn = (Button) view1.findViewById(R.id.frag11_dialog_ok_btn);
                Button cancelBtn = (Button) view1.findViewById(R.id.frag11_dialog_cancel_btn);
                builder.setCancelable(false).setView(view1);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String red = redEt.getText().toString().trim();
                        String yellow = yellowEt.getText().toString().trim();
                        String green = greenEt.getText().toString().trim();
                        if(!red.equals("") && !yellow.equals("") && !green.equals("")){
                            batchSet(red,yellow,green);
                            alertDialog.cancel();
                        }
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });
    }

    private void sort(int type) {
        Log.d(TAG, "sort: type="+type);
        switch (type) {
            case 0:
                Collections.sort(entryList, new Comparator<ZL_Frag11_TrafficMangEntry>() {
                    @Override
                    public int compare(ZL_Frag11_TrafficMangEntry max, ZL_Frag11_TrafficMangEntry min) {
                        return new Integer(max.getRoad()).compareTo(min.getRoad());
                    }
                });
                break;
            case 1:
                Collections.sort(entryList, new Comparator<ZL_Frag11_TrafficMangEntry>() {
                    @Override
                    public int compare(ZL_Frag11_TrafficMangEntry max, ZL_Frag11_TrafficMangEntry min) {
                        return new Integer(min.getRoad()).compareTo(max.getRoad());
                    }
                });
                break;
            case 2:
                Collections.sort(entryList, new Comparator<ZL_Frag11_TrafficMangEntry>() {
                    @Override
                    public int compare(ZL_Frag11_TrafficMangEntry max, ZL_Frag11_TrafficMangEntry min) {
                        return new Integer(max.getRedTime()).compareTo(min.getRedTime());
                    }
                });
                break;
            case 3:
                Collections.sort(entryList, new Comparator<ZL_Frag11_TrafficMangEntry>() {
                    @Override
                    public int compare(ZL_Frag11_TrafficMangEntry max, ZL_Frag11_TrafficMangEntry min) {
                        return new Integer(min.getRedTime()).compareTo(max.getRedTime());
                    }
                });
                break;
            case 4:
                Collections.sort(entryList, new Comparator<ZL_Frag11_TrafficMangEntry>() {
                    @Override
                    public int compare(ZL_Frag11_TrafficMangEntry max, ZL_Frag11_TrafficMangEntry min) {
                        return new Integer(max.getYellowTime()).compareTo(min.getYellowTime());
                    }
                });
                break;
            case 5:
                Collections.sort(entryList, new Comparator<ZL_Frag11_TrafficMangEntry>() {
                    @Override
                    public int compare(ZL_Frag11_TrafficMangEntry max, ZL_Frag11_TrafficMangEntry min) {
                        return new Integer(min.getYellowTime()).compareTo(max.getYellowTime());
                    }
                });
                break;
            case 6:
                Collections.sort(entryList, new Comparator<ZL_Frag11_TrafficMangEntry>() {
                    @Override
                    public int compare(ZL_Frag11_TrafficMangEntry max, ZL_Frag11_TrafficMangEntry min) {
                        return new Integer(max.getGreenTime()).compareTo(min.getGreenTime());
                    }
                });
                break;
            case 7:
                Collections.sort(entryList, new Comparator<ZL_Frag11_TrafficMangEntry>() {
                    @Override
                    public int compare(ZL_Frag11_TrafficMangEntry max, ZL_Frag11_TrafficMangEntry min) {
                        return new Integer(min.getGreenTime()).compareTo(max.getGreenTime());
                    }
                });
                break;
            default:break;
        }
        adapter.notifyDataSetChanged();
    }

    private void refreshData(){
        entryList.clear();
        initData(1);
        sort(spinner.getSelectedItemPosition());
    }


    private void batchSet(final String red, final String yellow, final String green){
        final List<ZL_Frag11_TrafficMangEntry> batchList = adapter.getEntryList();
        for(int j=0;j<batchList.size();j++){
            final ZL_Frag11_TrafficMangEntry entry = batchList.get(j);
            if(entry.isChoose()){
                String param = "{\"TrafficLightId\":"+entry.getRoad()+",\"RedTime\":"+red+",\"GreenTime\":"+green+",\"YellowTime\":"+yellow+",\"UserName\":\"user1\"}";
                HttpRequest.post("SetTrafficLightConfig", param, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            if (jsonObject.getString("RESULT").equals("S")) {
                                Toast.makeText(MyApplication.appContext, entry.getRoad() + "路口设置成功！", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, null);
            }
        }
        refreshData();
    }
}
