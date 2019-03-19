package com.app.trafficclient.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZzjHonglvdeng;
import com.app.trafficclient.adapter.ZzjHonglvdengArrayAdapter;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragment_item_21 extends Fragment {

    private Spinner spinner;
    private List<String> stringList = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    private ListView listView;
    private List<ZzjHonglvdeng> zzjHonglvdengLists = new ArrayList<>();
    private ZzjHonglvdengArrayAdapter zzjHonglvdengArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_21, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        addData();
    }

    private void paiXu(int i) {
        if (i == 1) {
            Collections.sort(zzjHonglvdengLists, new Comparator<ZzjHonglvdeng>() {
                @Override
                public int compare(ZzjHonglvdeng t0, ZzjHonglvdeng t1) {
                    return t0.getLukou().compareTo(t1.getLukou());
                }
            });
        }else if (i == 2) {
            Collections.sort(zzjHonglvdengLists, new Comparator<ZzjHonglvdeng>() {
                @Override
                public int compare(ZzjHonglvdeng t0, ZzjHonglvdeng t1) {
                    return t1.getLukou().compareTo(t0.getLukou());
                }
            });
        }else if (i == 3) {
            Collections.sort(zzjHonglvdengLists, new Comparator<ZzjHonglvdeng>() {
                @Override
                public int compare(ZzjHonglvdeng t0, ZzjHonglvdeng t1) {
                    return t0.getHongdeng().compareTo(t1.getHongdeng());
                }
            });
        }else if (i == 4) {
            Collections.sort(zzjHonglvdengLists, new Comparator<ZzjHonglvdeng>() {
                @Override
                public int compare(ZzjHonglvdeng t0, ZzjHonglvdeng t1) {
                    return t1.getHongdeng().compareTo(t0.getHongdeng());
                }
            });
        }
        zzjHonglvdengArrayAdapter.notifyDataSetChanged();
    }




    private void initView() {
        listView = getActivity().findViewById(R.id.zzj_fragment21_listView);
        spinner = getActivity().findViewById(R.id.zzj_fragment21_spinner);
        stringList.add("路口升序");
        stringList.add("路口降序");
        stringList.add("红灯升序");
        stringList.add("红灯降序");
        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (stringList.get(i).equals("路口升序")) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            paiXu(1);
                        }
                    }, 1500);
                }else if (stringList.get(i).equals("路口降序")) {
                    paiXu(2);
                }else if (stringList.get(i).equals("红灯升序")) {
                    paiXu(3);
                }else if (stringList.get(i).equals("红灯降序")) {
                    paiXu(4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        zzjHonglvdengArrayAdapter = new ZzjHonglvdengArrayAdapter(getContext(),R.layout.zzj_fragment_21_item,zzjHonglvdengLists);
        listView.setAdapter(zzjHonglvdengArrayAdapter);
    }

    private void addData() {
        HttpRequest.post("GetTrafficLightConfigAction", "{\"TrafficLightId\":\"1\", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    ZzjHonglvdeng zzjHonglvdeng = new ZzjHonglvdeng("1", jsonObject.getString("RedTime"), jsonObject.getString("YellowTime"), jsonObject.getString("GreenTime"));
                    zzjHonglvdengLists.add(zzjHonglvdeng);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetTrafficLightConfigAction", "{\"TrafficLightId\":\"2\", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    ZzjHonglvdeng zzjHonglvdeng = new ZzjHonglvdeng("2", jsonObject.getString("RedTime"), jsonObject.getString("YellowTime"), jsonObject.getString("GreenTime"));
                    zzjHonglvdengLists.add(zzjHonglvdeng);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetTrafficLightConfigAction", "{\"TrafficLightId\":\"3\", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    ZzjHonglvdeng zzjHonglvdeng = new ZzjHonglvdeng("3", jsonObject.getString("RedTime"), jsonObject.getString("YellowTime"), jsonObject.getString("GreenTime"));
                    zzjHonglvdengLists.add(zzjHonglvdeng);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetTrafficLightConfigAction", "{\"TrafficLightId\":\"4\", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    ZzjHonglvdeng zzjHonglvdeng = new ZzjHonglvdeng("4", jsonObject.getString("RedTime"), jsonObject.getString("YellowTime"), jsonObject.getString("GreenTime"));
                    zzjHonglvdengLists.add(zzjHonglvdeng);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetTrafficLightConfigAction", "{\"TrafficLightId\":\"5\", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    ZzjHonglvdeng zzjHonglvdeng = new ZzjHonglvdeng("5", jsonObject.getString("RedTime"), jsonObject.getString("YellowTime"), jsonObject.getString("GreenTime"));
                    zzjHonglvdengLists.add(zzjHonglvdeng);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }
}
