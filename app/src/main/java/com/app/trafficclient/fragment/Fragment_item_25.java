package com.app.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_item_25 extends Fragment {

    private Timer timer;
    private TextView textView_pm25,textView_wendu,textView_shidu;
    private TextView textView_1,textView_2,textView_3;
    private TextView textView_1_yanse,textView_2_yanse,textView_3_yanse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_25, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addData();
            }
        },1,3000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private void addData() {
        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    textView_pm25.setText(jsonObject.getString("pm2.5"));
                    textView_wendu.setText(jsonObject.getString("temperature"));
                    textView_shidu.setText(jsonObject.getString("humidity"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetRoadStatus", "{\"RoadId\":1,\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int daolu = jsonObject.getInt("Status");
                    if (daolu == 1) {
                        textView_1.setText("通畅");
                        textView_1_yanse.setBackgroundColor(Color.parseColor("#0ebd12"));
                    } else if (daolu == 2) {
                        textView_1.setText("较通畅");
                        textView_1_yanse.setBackgroundColor(Color.parseColor("#98ed1f"));
                    } else if (daolu == 3) {
                        textView_1.setText("拥挤");
                        textView_1_yanse.setBackgroundColor(Color.parseColor("#ffff01"));
                    } else if (daolu == 4) {
                        textView_1.setText("堵塞   ");
                        textView_1_yanse.setBackgroundColor(Color.parseColor("#ff0103"));
                    } else if (daolu == 5) {
                        textView_1.setText("爆表   ");
                        textView_1_yanse.setBackgroundColor(Color.parseColor("#4c060e"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetRoadStatus", "{\"RoadId\":2,\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int daolu = jsonObject.getInt("Status");
                    if (daolu == 1) {
                        textView_2.setText("通畅");
                        textView_2_yanse.setBackgroundColor(Color.parseColor("#0ebd12"));
                    } else if (daolu == 2) {
                        textView_2.setText("较通畅");
                        textView_2_yanse.setBackgroundColor(Color.parseColor("#98ed1f"));
                    } else if (daolu == 3) {
                        textView_2.setText("拥挤");
                        textView_2_yanse.setBackgroundColor(Color.parseColor("#ffff01"));
                    } else if (daolu == 4) {
                        textView_2.setText("堵塞");
                        textView_2_yanse.setBackgroundColor(Color.parseColor("#ff0103"));
                    } else if (daolu == 5) {
                        textView_2.setText("爆表");
                        textView_2_yanse.setBackgroundColor(Color.parseColor("#4c060e"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetRoadStatus", "{\"RoadId\":3,\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int daolu = jsonObject.getInt("Status");
                    if (daolu == 1) {
                        textView_3.setText("通畅");
                        textView_3_yanse.setBackgroundColor(Color.parseColor("#0ebd12"));
                    } else if (daolu == 2) {
                        textView_3.setText("较通畅");
                        textView_3_yanse.setBackgroundColor(Color.parseColor("#98ed1f"));
                    } else if (daolu == 3) {
                        textView_3.setText("拥挤");
                        textView_3_yanse.setBackgroundColor(Color.parseColor("#ffff01"));
                    } else if (daolu == 4) {
                        textView_3.setText("堵塞");
                        textView_3_yanse.setBackgroundColor(Color.parseColor("#ff0103"));
                    } else if (daolu == 5) {
                        textView_3.setText("爆表");
                        textView_3_yanse.setBackgroundColor(Color.parseColor("#4c060e"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }

    private void initView() {
        textView_pm25 = getActivity().findViewById(R.id.zzj_fragment25_textView_pm25);
        textView_wendu = getActivity().findViewById(R.id.zzj_fragment25_textView_wendu);
        textView_shidu = getActivity().findViewById(R.id.zzj_fragment25_textView_shidu);

        textView_1 = getActivity().findViewById(R.id.zzj_fragment25_textView_1_hao);
        textView_2 = getActivity().findViewById(R.id.zzj_fragment25_textView_2_hao);
        textView_3 = getActivity().findViewById(R.id.zzj_fragment25_textView_3_hao);

        textView_1_yanse = getActivity().findViewById(R.id.zzj_fragment25_textView_1_hao_yanse);
        textView_2_yanse = getActivity().findViewById(R.id.zzj_fragment25_textView_2_hao_yanse);
        textView_3_yanse = getActivity().findViewById(R.id.zzj_fragment25_textView_3_hao_yanse);
    }
}
