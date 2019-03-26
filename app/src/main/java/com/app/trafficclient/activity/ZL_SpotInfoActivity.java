package com.app.trafficclient.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.usebean.ZL_Frag35Bean;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

public class ZL_SpotInfoActivity extends AppCompatActivity {

    private TextView backTv;
    private ImageView spotImg;
    private TextView spotInfoTv;
    private TextView telTv;
    private int[] ratingTvs = new int[]{R.id.spotinfo_index1_tv,R.id.spotinfo_index2_tv,
            R.id.spotinfo_index3_tv,R.id.spotinfo_index4_tv,R.id.spotinfo_index5_tv};
    private TextView ratingTv;
    private int spotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zl_activity_spot_info);
        spotId = getIntent().getIntExtra("SpotId",0);
        initView();
        initData();
    }

    private void initView(){
        spotImg = (ImageView) findViewById(R.id.spotinfo_img);
        spotInfoTv = (TextView) findViewById(R.id.spotinfo_info_tv);
        telTv = (TextView) findViewById(R.id.spotinfo_tel_tv);
        backTv = (TextView) findViewById(R.id.spotinfo_back_tv);
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        switch (spotId){
            case 1:
                HttpRequest.post("GetSpotInfo", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ZL_Frag35Bean bean = new Gson().fromJson(jsonObject.toString(), ZL_Frag35Bean.class);
                        ZL_Frag35Bean.SpotInfo spotInfo = bean.getROWS_DETAIL().get(0);
                        spotInfoTv.setText(spotInfo.getInfo());
                        telTv.setText(spotInfo.getTel());
                        for (int i = 0;i<spotInfo.getRating();i++ ) {
                            ratingTv = (TextView) findViewById(ratingTvs[i]);
                            ratingTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_cboder_blue));
                        }
                        HttpRequest.bitmap(spotInfo.getImg(), new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap bitmap) {
                                spotImg.setImageBitmap(bitmap);
                            }
                        },null);
                    }
                },null);
                break;
            default:
                break;
        }
    }

}
