package com.app.trafficclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.trafficclient.R;
import com.app.trafficclient.adapter.Weizhangcheliang;
import com.app.trafficclient.adapter.YwsActivity12_weizhang_Adapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class YWS_weizhangchaxun extends AppCompatActivity {
    private List<Weizhangcheliang>weizhangcheliangList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yws_weizhangchaxun);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle_layout);
        recyclerView.setLayoutManager(linearLayoutManager);
        initFruit();
        YwsActivity12_weizhang_Adapter activity12_weizhang_adapter=new YwsActivity12_weizhang_Adapter(weizhangcheliangList);
        recyclerView.setAdapter(activity12_weizhang_adapter);


    }
    public void  initFruit(){

        Intent intent=getIntent();
        ;
        Weizhangcheliang weizhangcheliang=new Weizhangcheliang(intent.getStringExtra("chepaihao"));
        weizhangcheliangList.add(weizhangcheliang);


    }
}
