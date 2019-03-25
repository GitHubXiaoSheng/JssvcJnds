package com.app.trafficclient.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.usebean.ZL_Metro;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

public class ZL_MetroInfoActivity extends AppCompatActivity {
    private String TAG = "TAG";
    private TextView backTV;
    private ImageView metroImg;
    private String metroId = "";

    private ZL_Metro.Metro metro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zl_activity_metro_info);
        metroId = getIntent().getStringExtra("MetroId");
        initView();
        initData();
    }

    private void initView() {
        backTV = (TextView) findViewById(R.id.metro_back_tv);
        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        metroImg = (ImageView) findViewById(R.id.metro_img);
    }

    private void initData(){
        if(!metroId.equals("")){
            String param = "{\"Line\":\""+metroId+"\",\"UserName\":\"user1\"}";
            HttpRequest.post("GetMetroInfo", param, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    ZL_Metro metroBean = new Gson().fromJson(jsonObject.toString(), ZL_Metro.class);
                    metro = metroBean.getROWS_DETAIL().get(0);
                    HttpRequest.bitmap(metro.getMap(), new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            metroImg.setImageBitmap(bitmap);
                        }
                    },null);
                }
            },null);
        }
    }

}
