package com.app.trafficclient.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.trafficclient.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Yws_fragment_item19_2 extends Fragment {
private BarChart barChart;
    List<BarEntry> barEntryList=new ArrayList<>();
    String[]X={"机动车逆向行驶","违规使用专用车道","违反禁令标志指示","不按规定系安全带","机动车不走机动车道","违反信号灯规定","违反禁止标线指示","违规停车拒绝驶离","违规驶入导向车道","超速行驶"};
    public Yws_fragment_item19_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yws_fragment_item19_2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        barChart=getActivity().findViewById(R.id.chart_horizontalBar);
        for (int i=1;i<=10;i++){
            barEntryList.add(new BarEntry(i,new Random().nextInt(30)*1f));
        }
        BarDataSet barDataSet=new BarDataSet(barEntryList,"");
        barDataSet.setColors(new int[]{Color.rgb(149,178,212),Color.rgb(173,162,194),Color.rgb(255, 173, 99),Color.rgb(185,205, 136),
                Color.rgb(253,193,7),Color.rgb(95,72,133),Color.rgb(224, 112, 6),Color.rgb(118,148, 58),
                Color.rgb(75,128,187),Color.rgb(192,3,1)});

        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.8f);
        barChart.setData(barData);
        barChart.setDrawValueAboveBar(true);
        barChart.getLegend().setFormSize(0f);
        barChart.setDescription(null);
        barChart.invalidate();


        XAxis xAxis=barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(10);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return X[(int)v-1];
            }
        });




        YAxis yAxisleft=barChart.getAxisLeft();
        yAxisleft.setDrawAxisLine(false);
        yAxisleft.setDrawLabels(false);
        yAxisleft.setDrawGridLines(false);
        YAxis yAxisright=barChart.getAxisRight();
        yAxisright.setDrawAxisLine(true);
        yAxisright.setAxisMinimum(0);
        yAxisright.setAxisMaximum(30);
        yAxisright.setLabelCount(6);
//        yAxisright.setValueFormatter(new Baifenbi());




    }

    }

