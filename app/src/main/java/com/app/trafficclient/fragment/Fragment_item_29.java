package com.app.trafficclient.fragment;


import android.os.Bundle;
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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_29 extends Fragment {
    private TextView show_zhishu;
    private LineChart pm2lineChart;
    private LinearLayout huadongbuju;
    private LineDataSet lineDataSet;
    private LineData lineData;
    private List<Entry> pm2Entry;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_29, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        show_zhishu=getActivity().findViewById(R.id.tv_show_zhishu);
        pm2lineChart=getActivity().findViewById(R.id.line_chart);
        huadongbuju=getActivity().findViewById(R.id.huadongbuju);
        pm2lineChart=getActivity().findViewById(R.id.line_chart);
        http();
        lineDataSet=new LineDataSet(pm2Entry,"");
        lineData=new LineData(lineDataSet);
        pm2lineChart.setData(lineData);
        pm2lineChart.invalidate();



        XAxis xAxis=pm2lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return null;
            }
        });

    }

    int i = 0;
    private void  http(){
        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    if (i < 5){
                        pm2Entry.add(new Entry(i,Float.valueOf(jsonObject.getString("pm2.5"))));
                    }else {
                        i = -1;
                    }
                    i ++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }
}
