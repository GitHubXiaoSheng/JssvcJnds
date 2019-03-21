package com.app.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_item_26 extends Fragment {

    private String TAG = "TAG";
    private View view;
    private TextView titleTv;
    private LinearLayout layout;
    private int[] layouts = new int[]{R.id.frag26_data_layout1,R.id.frag26_data_layout2};
    private TextView indexTv;
    private int[] indexTvs = new int[]{R.id.frag26_data_index1_tv,R.id.frag26_data_index2_tv};
    private int index=0;

    //饼图
    private PieChart pieChart;
    private List<PieEntry> pieEntryList;
    private PieData pieData;
    private int[] pieSetColors = new int[]{Color.rgb(170,70,68),Color.rgb(68,114,166)};

    //水平条形图
    private HorizontalBarChart horizontalBarChart;
    private List<BarEntry> hbarEntryList;
    private BarData hBarData;
    private int[] hBarSetColors1 = new int[]{Color.rgb(145,210,80),Color.rgb(80,130,200),
            Color.rgb(194,0,0)};
    private int[] hBarSetColors2=new int[]{Color.rgb(153,182,213),Color.rgb(179,164,198),
            Color.rgb(251,174,111),Color.rgb(179,203,139),Color.YELLOW
            ,Color.rgb(98,72,122),Color.rgb(227,108,5),
            Color.rgb(120,146,60),Color.rgb(75,130,186),Color.RED};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout_item_26, container, false);
        initView();
        setLiseter();
        initPie1();
        return view;
    }

    private void initView(){
        titleTv = (TextView) view.findViewById(R.id.frag26_data_title_tv);
        layout = (LinearLayout) view.findViewById(layouts[index]);
        layout.setVisibility(View.VISIBLE);
        indexTv = (TextView) view.findViewById(indexTvs[index]);
        indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_1));

        pieChart = (PieChart) view.findViewById(R.id.frag26_data_piechart);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHighlightPerTapEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);

        horizontalBarChart = (HorizontalBarChart) view.findViewById(R.id.frag26_data_horizontalBarChart);
        horizontalBarChart.setDoubleTapToZoomEnabled(false);
        horizontalBarChart.setScaleEnabled(false);
        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setDragEnabled(false);
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        horizontalBarChart.getXAxis().setDrawGridLines(false);
        horizontalBarChart.getAxisLeft().setEnabled(false);
    }

    private void initPie1(){
        titleTv.setText("平台上有无“重复违章记录的车辆”的占比统计");
        pieEntryList = new ArrayList<>();
        pieEntryList.add(new PieEntry(13.8f));
        pieEntryList.get(0).setLabel("有违章:"+pieEntryList.get(0).getValue()+"%");
        pieEntryList.add(new PieEntry(86.1f,"无违章"));
        pieEntryList.get(1).setLabel("无违章:"+pieEntryList.get(1).getValue()+"%");
        Log.d(TAG, "initPie1: y = "+pieEntryList.get(0).getY());
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");
        pieDataSet.setSelectionShift(15f);
        pieDataSet.setSliceSpace(10f);
        pieDataSet.setColors(pieSetColors);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLineColor(pieSetColors[0]);
        pieDataSet.setValueLinePart1Length(0.8f);
        pieDataSet.setDrawValues(false);
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.highlightValue(0, 0, false);
        pieChart.setRotationAngle(210);
        pieChart.invalidate();
    }

    private void initHBar1(){
        titleTv.setText("年龄群体车辆违章的占比统计");
        //X轴的设置
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setAxisMaximum(3.5f);
        xAxis.setLabelCount(3);
        final String[] xLabel = new String[]{"五条以上违章","1—2条违章","3—5条违章"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                Log.d("测试", "getFormattedValue: v="+v);
                return xLabel[(int)v%xLabel.length];
            }
        });
        //Y轴的设置
        YAxis rightYAxis = horizontalBarChart.getAxisRight();
        rightYAxis.setAxisMinimum(0);
        rightYAxis.setAxisMaximum(100);
        rightYAxis.setLabelCount(10);
//        final String[] yLabel = new String[]{"0.00%","10.00%","20.00%","30.00%","40.00%","50.00%","60.00%","00%"};
        rightYAxis.setValueFormatter(new PercentFormatter());
        hbarEntryList = new ArrayList<>();
        hbarEntryList.add(new BarEntry(1,60.51f));
        hbarEntryList.add(new BarEntry(2,26.28f));
        hbarEntryList.add(new BarEntry(3,13.20f));
        BarDataSet barDataSet = new BarDataSet(hbarEntryList,"");
        barDataSet.setColors(hBarSetColors1);
        hBarData = new BarData(barDataSet);
        hBarData.setBarWidth(0.5f);
        horizontalBarChart.setData(hBarData);
        horizontalBarChart.invalidate();
    }

    private void refesh(){
        layout = (LinearLayout) view.findViewById(layouts[index]);
        indexTv = (TextView) view.findViewById(indexTvs[index]);
        layout.setVisibility(View.VISIBLE);
        indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_1));
        switch (index) {
            case 0:
                initPie1();
                break;
            case 1:
                initHBar1();
                break;
            default:break;
        }
    }

    float start = 0;
    float end = 0;
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                start = motionEvent.getX();
            }
            if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                end = motionEvent.getX();

                if(Math.abs(end-start)>10){
                    index = (++index)%2;
//                    Log.d(TAG, "onTouch: index="+index);
                }

                layout.setVisibility(View.GONE);
                indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                refesh();
            }
            return true;
        }
    };
    private void setLiseter(){
        pieChart.setOnTouchListener(onTouchListener);
        horizontalBarChart.setOnTouchListener(onTouchListener);
    }


}
