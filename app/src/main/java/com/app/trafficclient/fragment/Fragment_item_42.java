package com.app.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;


public class Fragment_item_42 extends Fragment {
private TextView rechargeTv,cheweoTv;
private Button reflesh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_42, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        HttpparkInfo();
        HttpparkInfo1();
        rechargeTv=getActivity().findViewById(R.id.recharge_tv);
        cheweoTv=getActivity().findViewById(R.id.chewei_tv);
        reflesh=getActivity().findViewById(R.id.reflesh_button);
        reflesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shuaxin();
            }
        });

    }
    private void HttpparkInfo(){
        HttpRequest.post("GetPakingListReport", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        cheweoTv.setText("收费标准:"+jsonObject1.getString("Money")+"元/小时");
                        Log.d("tag",jsonObject1.getString("Money"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);

    }
    private void HttpparkInfo1(){
        HttpRequest.post("GetParkFree", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    rechargeTv.setText("剩余车位:   "+jsonObject.getString("ParkFreeId")+"/10");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }
    Timer timer;
    private void shuaxin(){
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               HttpparkInfo();
               HttpparkInfo1();
            }
        },1,3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
