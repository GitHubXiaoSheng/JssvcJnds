package com.app.trafficclient.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Yws_Fragment_item19_1 extends Fragment {
    private List<PieEntry>piedatalist=new ArrayList<>();
    private PieDataSet pieDataSet;
    private PieChart pieChart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yws__fragment_item19_1, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
//        httpdata();
        pieChart=getActivity().findViewById(R.id.chart_pie);
//        piedatalist.get(0).setLabel("有违章"+piedatalist.get(0).getValue()+"%");
//        piedatalist.get(1).setLabel("无违章"+piedatalist.get(1).getValue()+"%");
        piedatalist.add(new PieEntry(28.7f,""));
        piedatalist.add(new PieEntry(71.3f,""));
        piedatalist.get(0).setLabel("有违章"+piedatalist.get(0).getValue()+"%");
        piedatalist.get(1).setLabel("无违章"+piedatalist.get(1).getValue()+"%");
        pieDataSet=new PieDataSet(piedatalist,"");
        pieDataSet.setValueLinePart1Length(1f);


        ArrayList<Integer>colors=new ArrayList<Integer>();
        colors.add(Color.rgb(69,115,167));
        colors.add(Color.rgb(170,70,68));
        pieDataSet.setColors(colors);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);   //设置y轴的值写在外面

        pieChart.setEntryLabelColor(Color.rgb(106,106,106));

        //设置X轴文字颜色


        PieData pieData=new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setDrawValues(false);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setDescription(null);
        pieChart.setData(pieData);
        pieChart.getLegend().setFormSize(0f);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();







    }

    protected void httpdata(){
        HttpRequest.post("GetAllCarPeccancy", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.d("数据",jsonObject.toString());
                    int index = jsonObject.getJSONArray("ROWS_DETAIL").length();
//                    piedatalist.add(new PieEntry((float)index/(float)(index+71),""));
//                    piedatalist.add(new PieEntry(71.3f/(float)(index+71),""));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }
}
