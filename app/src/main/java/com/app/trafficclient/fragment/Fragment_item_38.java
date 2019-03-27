package com.app.trafficclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.activity.Fragment38_Activity1;
import com.app.trafficclient.adapter.Zzj_Dingzhi_banche;
import com.app.trafficclient.adapter.Zzj_Dingzhi_bancheAdapter;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment_item_38 extends Fragment {

    private ListView listView;
    private Zzj_Dingzhi_bancheAdapter adapter ;
    private List<Zzj_Dingzhi_banche> list = new ArrayList<>();
    private String start,start_time,stop,stop_time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layou_item_38, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getActivity().findViewById(R.id.zzj_list_Dingzhibanche);
        addData();
    }

    private void addData() {
        HttpRequest.post("GetBusInfo", "{\"Line\":0,\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int index = 0;
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("ROWS_DETAIL"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Zzj_Dingzhi_banche zzj_dingzhi_banche = new Zzj_Dingzhi_banche();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("sites",Context.MODE_PRIVATE).edit();
                        SharedPreferences.Editor editor2 = getActivity().getSharedPreferences("sites2",Context.MODE_PRIVATE).edit();
                        editor.putString("" + index, jsonObject1.getString("sites"));
                        editor2.putString("" + index, jsonObject1.getString("map"));
                        editor.apply();
                        editor2.apply();
                        index ++;

                        zzj_dingzhi_banche.setChehao(jsonObject1.getString("name"));
                        JSONArray jsonArray1 = new JSONArray(jsonObject1.getString("time"));
                        for (int j = 0; j < jsonArray1.length(); j++) {
                            JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                            if (j == 0) {
                                start = jsonObject2.getString("site");
                                start_time = jsonObject2.getString("starttime");
                            }else if (j == 1){
                                stop = jsonObject2.getString("site");
                                stop_time = jsonObject2.getString("endtime");
                            }
                            zzj_dingzhi_banche.setQishidian(start+"---"+stop);
                            zzj_dingzhi_banche.setQidian_time(start_time);
                            zzj_dingzhi_banche.setPiaojia_licheng("票价： ￥"+jsonObject1.getString("ticket") + "     里程：" + jsonObject1.getString("mileage") + "  km");
                            zzj_dingzhi_banche.setZhongdian_time(stop_time);
                        }
                        list.add(zzj_dingzhi_banche);
                        Log.d("list的数据", list.get(i).getChehao()+"---"+list.get(i).getQishidian()+"---"+list.get(i).getQidian_time()+"---"+list.get(i).getPiaojia_licheng()+"---"+list.get(i).getZhongdian_time()+"---");
                    }
                    adapter = new Zzj_Dingzhi_bancheAdapter(getContext(), R.layout.zzj_dingzhibanche_item,list);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SharedPreferences.Editor editor = getActivity().getSharedPreferences("select", Context.MODE_PRIVATE).edit();
                            editor.putInt("id",position);
                            editor.putString("qishidian", list.get(position).getQishidian());
                            editor.putString("piaojia", list.get(position).getPiaojia_licheng());
                            editor.apply();
                            Intent intent = new Intent(getActivity(), Fragment38_Activity1.class);
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }

}
