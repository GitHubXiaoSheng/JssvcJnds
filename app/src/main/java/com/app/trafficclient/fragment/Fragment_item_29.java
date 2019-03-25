package com.app.trafficclient.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_item_29 extends Fragment {
    private TextView show_zhishu;
    private LinearLayout huadongbuju;

    private LineChart lineChart;
    private List<Entry> entryList = new ArrayList<>();
    private LineDataSet lineDataSet;
    private LineData lineData;

    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_29, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addData(1);
            }
        },0,3000);
    }
    private int i = 0;
    private void addData(int in){
        if (in == 1) {
            HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        if (i < 5) {
                            entryList.add(new Entry(i++,jsonObject.getInt("pm2.5")));
                            lineDataSet = new LineDataSet(entryList,"");
                            lineData = new LineData(lineDataSet);
                            lineChart.setData(lineData);
                            lineChart.invalidate();
                        }else {

                            i = -1;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },null);

        }
    }


    private void initView() {
        show_zhishu = getActivity().findViewById(R.id.tv_show_zhishu);
        huadongbuju = getActivity().findViewById(R.id.huadongbuju);

        lineChart = getActivity().findViewById(R.id.yws_line_chart);

    }
}
