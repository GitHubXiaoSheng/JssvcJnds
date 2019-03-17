package com.app.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZL_AccountAdapter;
import com.app.trafficclient.entry.ZL_AccountEntry;
import com.app.trafficclient.usebean.ZL_Balance;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Fragment_item_9 extends Fragment {
    private View view;
    private TextView batchRechargeTv,rechargeRecordTv;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private List<ZL_AccountEntry> entryList;
    private List<ZL_AccountEntry> plateList = new ArrayList<>();
    private ZL_AccountAdapter accountAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_layout_9, container, false);
        initView();
//        setListener();
        initData(1);
        return view;
    }

    private void initView(){
        batchRechargeTv = (TextView) view.findViewById(R.id.frag9_account_batchRecharge_tv);
        rechargeRecordTv = (TextView) view.findViewById(R.id.frag9_account_toprecord_tv);
        recyclerView = (RecyclerView) view.findViewById(R.id.frag9_account_recycler);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        entryList = new ArrayList<>();
        accountAdapter = new ZL_AccountAdapter(getContext(),entryList);
        recyclerView.setAdapter(accountAdapter);
    }

    private void initData(final int car){
        String param = "{\"CarId\":"+String.valueOf(car)+", \"UserName\":\"user1\"}";
        HttpRequest.post("GetCarAccountBalance", param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ZL_Balance ZLBalance = new Gson().fromJson(jsonObject.toString(), ZL_Balance.class);
                if(car>4){
                    accountAdapter.notifyDataSetChanged();
                    return;
                }else {
                    entryList.add(new ZL_AccountEntry(car,R.drawable.zl_car_icon,"辽A1000"+car,"张三", ZLBalance.getBalance()));
                    initData(car+1);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyApplication.appContext,"数据请求出错了",Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void setListener(){
//        batchRechargeTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }


}
