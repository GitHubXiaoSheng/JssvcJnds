package com.app.trafficclient.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
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
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;
import java.util.List;



public class Fragment_item_15 extends Fragment {
    private String TAG = "TAG";
    private View view;
    private TextView titleTv;
    private LinearLayout layout;
    private int[] layouts = new int[]{R.id.frag15_data_layout1,R.id.frag15_data_layout1,R.id.frag15_data_layout2,
            R.id.frag15_data_layout3,R.id.frag15_data_layout3,R.id.frag15_data_layout3,R.id.frag15_data_layout2};
    private TextView indexTv;
    private int[] indexTvs = new int[]{R.id.frag15_data_index1_tv,R.id.frag15_data_index2_tv,R.id.frag15_data_index3_tv,
            R.id.frag15_data_index4_tv,R.id.frag15_data_index5_tv,R.id.frag15_data_index6_tv,R.id.frag15_data_index7_tv};
    private int index=6;

    //饼图
    private PieChart pieChart;
    private List<PieEntry> pieEntryList;
    private PieData pieData;
    private int[] pieSetColors = new int[]{Color.rgb(68,114,166),Color.rgb(170,70,68)};

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

    //条形图
    private BarChart barChart;
    private List<BarEntry> barEntryList;
    private BarData barData;
    private int[] barSetColors1 = new int[]{Color.rgb(106,152,0),Color.rgb(235,114,8)};
    private int[] barSetColors2 = new int[]{Color.rgb(77,110,169),Color.rgb(47,17,25),Color.rgb(3,2,126),Color.rgb(79,92,40),
            Color.rgb(184,175,222),Color.rgb(122,149,67),Color.rgb(144,53,49),Color.YELLOW,
            Color.rgb(78,128,190),Color.rgb(233,103,13),Color.rgb(140,57,117),Color.rgb(104,34,44)};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_layout_15, container, false);
        initView();
        refesh();
        setLiseter();
        return view;
    }

    private void initView(){
        titleTv = (TextView) view.findViewById(R.id.frag15_data_title_tv);
        layout = (LinearLayout) view.findViewById(layouts[index]);
        layout.setVisibility(View.VISIBLE);
        indexTv = (TextView) view.findViewById(indexTvs[index]);
        indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_1));

        pieChart = (PieChart) view.findViewById(R.id.frag15_data_piechart);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHighlightPerTapEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);

        horizontalBarChart = (HorizontalBarChart) view.findViewById(R.id.frag15_data_horizontalBarChart);
        horizontalBarChart.setDoubleTapToZoomEnabled(false);
        horizontalBarChart.setScaleEnabled(false);
        horizontalBarChart.setPinchZoom(false);
        horizontalBarChart.setDragEnabled(false);
        horizontalBarChart.getDescription().setEnabled(false);
        horizontalBarChart.getLegend().setEnabled(false);
        horizontalBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        horizontalBarChart.getXAxis().setDrawGridLines(false);
        horizontalBarChart.getAxisLeft().setEnabled(false);

        barChart = (BarChart) view.findViewById(R.id.frag15_data_BarChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getAxisLeft().setAxisMinimum(0);
    }

    private void initPie1(){
        titleTv.setText("平台上有违章车辆和没有违章车辆的占比统计");
        TextView leftTv = (TextView) view.findViewById(R.id.frag15data_left_legend);
        TextView rightTv = (TextView) view.findViewById(R.id.frag15data_righ_legend);
        leftTv.setText("有违章");
        rightTv.setText("无违章");
        pieEntryList = new ArrayList<>();
        pieEntryList.add(new PieEntry(28.6f));
        pieEntryList.get(0).setLabel("有违章:"+pieEntryList.get(0).getValue()+"%");
        pieEntryList.add(new PieEntry(71.3f,"无违章"));
        pieEntryList.get(1).setLabel("有违章:"+pieEntryList.get(1).getValue()+"%");
        Log.d(TAG, "initPie1: y = "+pieEntryList.get(0).getY());
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");
        pieDataSet.setColors(pieSetColors);
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLineColor(pieSetColors[0]);
        pieDataSet.setValueLinePart1Length(0.8f);
        pieDataSet.setDrawValues(false);
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void initPie2(){
        titleTv.setText("有无“重复违章记录的车辆”的占比统计");
        TextView leftTv = (TextView) view.findViewById(R.id.frag15data_left_legend);
        TextView rightTv = (TextView) view.findViewById(R.id.frag15data_righ_legend);
        leftTv.setText("无重复违章");
        rightTv.setText("有重复违章");
        pieEntryList = new ArrayList<>();
        pieEntryList.add(new PieEntry(86.1f));
        pieEntryList.get(0).setLabel("有违章:"+pieEntryList.get(0).getValue()+"%");
        pieEntryList.add(new PieEntry(13.8f,"无违章"));
        pieEntryList.get(1).setLabel("有违章:"+pieEntryList.get(1).getValue()+"%");
        Log.d(TAG, "initPie1: y = "+pieEntryList.get(0).getY());
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");
        pieDataSet.setColors(pieSetColors);
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLineColor(pieSetColors[0]);
        pieDataSet.setSelectionShift(10);
        pieDataSet.setValueLinePart1Length(0.6f);
        pieDataSet.setDrawValues(false);
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
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

    private void initBar1(){
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        barChart.getLegend().setYOffset(0);
        titleTv.setText("年龄群体车辆违章的占比统计");
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelRotationAngle(0);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setLabelCount(5);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            String[] xLabel = new String[]{"90后","80后","70后","60后","50后"};
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                Log.d("测试", "getFormattedValue: "+v);
                return xLabel[(int)v%xLabel.length];
            }
        });
        barEntryList = new ArrayList<>();
        for (int i=1;i<6;i++){
            barEntryList.add(new BarEntry(i,new float[]{(int)( Math.random()*300+300),(int) (Math.random()*300+300)},"节点"+String.valueOf(i)));
        }
        BarDataSet barDataSet = new BarDataSet(barEntryList,"");
        barDataSet.setColors(barSetColors1);
        barDataSet.setStackLabels(new String[]{"无违章","有违章"});
        barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        barChart.setData(barData);
        barChart.invalidate();
    }

    private void initBar2(){
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);
        barChart.getLegend().setYOffset((barChart.getHeight()/4));
        titleTv.setText("平台上男性和女性有无车辆违章的占比统计");
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelRotationAngle(0);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setLabelCount(2);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            String[] xvalue = new String[]{"女性","男性"};
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                Log.d("测试", "getFormattedValue: v"+v);
                return xvalue[(int)(v%xvalue.length)];
            }
        });

        barEntryList = new ArrayList<>();
        for (int i=1;i<3;i++){
            barEntryList.add(new BarEntry(i,new float[]{(int)( Math.random()*1000+15000),(int) (Math.random()*300+3000)},"节点"+String.valueOf(i)));
        }
        BarDataSet barDataSet = new BarDataSet(barEntryList,"");
        barDataSet.setColors(barSetColors1);
        barDataSet.setStackLabels(new String[]{"无违章","有违章"});
        barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        barChart.setData(barData);
        barChart.invalidate();
    }

    private void initBar3(){
        barChart.getLegend().setEnabled(false);
        titleTv.setText("每日时段内车辆违章的占比统计");
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextColor(Color.BLACK);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setLabelCount(12);
        final String[] xvalue = new String[]{"0:00:01-2:00:00","2:00:01-4:00:00","4:00:01-6:00:00","6:00:01-8:00:00"
                ,"8:00:01-10:00:00","10:00:01-12:00:00","12:00:01-14:00:00","14:00:01-16:00:00"
                ,"16:00:01-18:00:00","18:00:01-20:00:00","20:00:01-22:00:00","22:00:01-24:00:00"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
//                Log.d("测试", "getFormattedValue: v"+v);
                return xvalue[(int)(v%xvalue.length)];
            }
        });
        xAxis.setLabelRotationAngle(-60);
        barChart.getAxisLeft().setValueFormatter(new PercentFormatter());

        barEntryList = new ArrayList<>();
        for (int i=0;i<12;i++){
            barEntryList.add(new BarEntry(i,(int)(Math.random()*25)));
        }
        BarDataSet barDataSet = new BarDataSet(barEntryList,"");
        barDataSet.setColors(barSetColors2);
        barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        barChart.setData(barData);
        barChart.invalidate();
    }

    private void initHBar2(){
        titleTv.setText("排名前十位的交通违法行为的占比统计");
        //X轴的设置
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setAxisMaximum(10.5f);
        xAxis.setLabelCount(10);
        final String[] yLabel = new String[]{"机动车逆向行驶","违规使用专用车道","违反禁令标示指示","不按规定系安全带","机动车不走机动车道",
                "违反信号灯规定","违反禁止标线指示","违规停车拒绝驶离","违规驶入导向车道","超速行驶"};
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                Log.d(TAG, "getFormattedValuess: v="+v);
                return yLabel[(int)v%yLabel.length];
            }
        });
        //Y轴的设置
        YAxis rightYAxis = horizontalBarChart.getAxisRight();
//        rightYAxis.setAxisMinimum(0);
//        rightYAxis.setAxisMaximum(100);
//        rightYAxis.setLabelCount(10);
        rightYAxis.setAxisMinimum(0);
        rightYAxis.setAxisMaximum(30);
        rightYAxis.setLabelCount(7);
        rightYAxis.setValueFormatter(new PercentFormatter());

        hbarEntryList = new ArrayList<>();
        for (int i=1;i<11;i++){
            hbarEntryList.add(new BarEntry(i,((float) Math.random()*30)));
        }
        BarDataSet barDataSet = new BarDataSet(hbarEntryList,"");
        barDataSet.setColors(hBarSetColors2);
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
                initPie2();
                break;
            case 2:
                initHBar1();
                break;
            case 3:
                initBar1();
                break;
            case 4:
                initBar2();
                break;
            case 5:
                initBar3();
                break;
            case 6:
                initHBar2();
                break;
            default:break;
        }
    }

    float start = 0;
    float end = 0;
    private void setLiseter(){
        pieChart.setOnTouchListener(new ChartTouchListener(pieChart) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    start = motionEvent.getX();
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    end = motionEvent.getX();
                    if(end-start > 10 && index>0){
                        index--;
                        Log.d(TAG, "onTouch: index="+index);
                    }
                    if(start-end > 10 && index<6){
                        index++;
                        Log.d(TAG, "onTouch: index="+index);
                    }
                    layout.setVisibility(View.GONE);
                    indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                    refesh();
                }
                return true;
            }
        });
        horizontalBarChart.setOnChartGestureListener(listener);
        barChart.setOnChartGestureListener(listener);
    }

    private OnChartGestureListener listener = new OnChartGestureListener() {
        @Override
        public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            if(v > 0 && index>0){
                index--;
            }
            if(v < 0 && index<6){
                index++;
            }
            layout.setVisibility(View.GONE);
            indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
            refesh();
        }

        @Override
        public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

        }

        @Override
        public void onChartGestureEnd(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {

        }

        @Override
        public void onChartLongPressed(MotionEvent motionEvent) {

        }

        @Override
        public void onChartDoubleTapped(MotionEvent motionEvent) {

        }

        @Override
        public void onChartSingleTapped(MotionEvent motionEvent) {

        }

        @Override
        public void onChartScale(MotionEvent motionEvent, float v, float v1) {

        }

        @Override
        public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {

        }
    };
}
