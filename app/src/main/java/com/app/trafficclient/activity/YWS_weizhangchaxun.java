package com.app.trafficclient.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.Weizhangcheliang;
import com.app.trafficclient.adapter.YwsActivity12_weizhang_Adapter;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class YWS_weizhangchaxun extends AppCompatActivity {
    private List<Weizhangcheliang>weizhangcheliangList=new ArrayList<>();

    private ImageView img_add;
    private ImageView img_puls;
    private YwsActivity12_weizhang_Adapter activity12_weizhang_adapter;
    private TextView tv_time,tv_info,tv_road;

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yws_weizhangchaxun);

        tv_time=findViewById(R.id.weizhang_time);
        tv_road=findViewById(R.id.weizhang_road);
        tv_info=findViewById(R.id.weizhang_info);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView=(RecyclerView)findViewById(R.id.recycle_layout);
        recyclerView.setLayoutManager(linearLayoutManager);
        initFruit();
        http1();
        activity12_weizhang_adapter=new YwsActivity12_weizhang_Adapter(weizhangcheliangList);
        recyclerView.setAdapter(activity12_weizhang_adapter);
        img_add=findViewById(R.id.imgage_add);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void  initFruit(){

        for (int i = 0; i < MyApplication.stringList.size(); i++) {
            Weizhangcheliang weizhangcheliang=new Weizhangcheliang(MyApplication.stringList.get(i));
            weizhangcheliangList.add(weizhangcheliang);
        }

    }
private  void http1(){
    HttpRequest.post("GetAllCarPeccancy", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject jsonObject) {

            try {
                JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");

                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                Log.d("打印",jsonObject1.toString());
                Log.d("打印",jsonObject1.getString("pdatetime"));
                tv_time.setText(jsonObject1.getString("pdatetime"));
                tv_road.setText(jsonObject1.getString("paddr"));
                tv_info.setText("驾驶机动车在高速公路，城市快速路以外的道路上不按规定行驶的");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    },null);
}


}
