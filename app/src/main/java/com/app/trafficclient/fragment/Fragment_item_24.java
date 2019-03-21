package com.app.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_item_24 extends Fragment {
    private String TAG = "TAG";
    private View view;

    private TextView nowTempTv;
    private TextView allDayTemTv;
    private ImageView refreshImg;
    private LineChart lineChart;
    private List<Entry> maxList;
    private List<Entry> minList;
    private LineData lineData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_item_24, container, false);
        initView();
        initData();
        return view;
    }

    private void initView(){
        nowTempTv = (TextView) view.findViewById(R.id.frag24_life_nowtemp_tv);
        allDayTemTv = (TextView) view.findViewById(R.id.frag24_life_allday_tv);
        lineChart = (LineChart) view.findViewById(R.id.frag24_life_linchart);
        refreshImg = (ImageView) view.findViewById(R.id.frag24_life_refresh_img);
        refreshImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
            }
        });
    }

    private void initData(){
        HttpRequest.post("GetSenseByName", "{\"SenseName\":\"temperature\", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    nowTempTv.setText(jsonObject.getString("temperature")+"°");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);

        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setDrawAxisLine(false);
        lineChart.getAxisLeft().setDrawLabels(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setAxisMaximum(6.5f);
        xAxis.setLabelCount(6);
        xAxis.setTextColor(Color.parseColor("#4f5fBB"));
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                Log.d(TAG, "getFormattedValue: v="+v);
                return getDate().get((int)v-1%6);
            }
        });

        maxList = new ArrayList<>();
        minList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            maxList.add(new Entry(i,(float) Math.random()*10+20));
            minList.add(new Entry(i,(float) Math.random()*10+10));
        }
        LineDataSet maxSet = new LineDataSet(maxList, "");
        LineDataSet minSet = new LineDataSet(minList, "");
        maxSet.setCircleColor(Color.RED);
        maxSet.setDrawCircleHole(false);
        maxSet.setCircleRadius(2f);
        maxSet.setColor(Color.RED);
        maxSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return String.valueOf((int)v);
            }
        });

        minSet.setCircleColor(Color.BLUE);
        minSet.setDrawCircleHole(false);
        minSet.setCircleRadius(2f);
        minSet.setColor(Color.BLUE);
        minSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return String.valueOf((int)v);
            }
        });
        List<ILineDataSet> setList = new ArrayList<>();
        setList.add(maxSet);
        setList.add(minSet);
        lineData = new LineData(setList);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }


    private List<String> getDate(){
        SimpleDateFormat format = new SimpleDateFormat("E");
        List<String> xValue  = new ArrayList<>();
        switch (format.format(new Date(System.currentTimeMillis()))) {
            case "周一":
                xValue.add("\"昨天\",\"今天\",\"明天\",\"周三\",\"周四\",\"周五\"");
                break;
            case "周二":
                xValue.add("\"昨天\",\"今天\",\"明天\",\"周三\",\"周四\",\"周五\"");
                break;
            case "周三":
                xValue.add("\"昨天\",\"今天\",\"明天\",\"周三\",\"周四\",\"周五\"");
                break;
            case "周四":
                xValue.add("\"昨天\",\"今天\",\"明天\",\"周三\",\"周四\",\"周五\"");
                break;
            case "周五":
                xValue.add("\"昨天\",\"今天\",\"明天\",\"周三\",\"周四\",\"周五\"");
                break;
            case "周六":
                xValue.add("\"昨天\",\"今天\",\"明天\",\"周三\",\"周四\",\"周五\"");
                break;
            case "周日":
                xValue.add("\"昨天\",\"今天\",\"明天\",\"周三\",\"周四\",\"周五\"");
                break;
            default:
                break;
        }
        return xValue;
    }

}
