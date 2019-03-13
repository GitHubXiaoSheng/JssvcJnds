package com.app.trafficclient.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.trafficclient.MyApplication;
import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_6 extends Fragment {
    private View view;
    private String[] titles = new String[]{"温度","湿度","PM2.5","CO2","光照","道路状态"};
    private TextView titleTv;
    private TextView indexTv;
    private int[] indexTvs = new int[]{R.id.frag6_index1_tv,R.id.frag6_index2_tv,R.id.frag6_index3_tv,
            R.id.frag6_index4_tv,R.id.frag6_index5_tv,R.id.frag6_index6_tv};
    private LineChart lineChart;
    private List<Entry> entryList;
    private LineDataSet lineDataSet;
    private LineData lineData;
    private OnChartGestureListener onChartListener;

    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;

    private int index = 0;
    private List<String> xValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_layout_6, container, false);
        initView();
        if(isAdded()){
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendMessage(new Message());
                }
            },0,3*1000);
        }
        return view;
    }

    private void initView(){
        titleTv = (TextView) view.findViewById(R.id.frag6_contenttitle_tv);
        titleTv.setText(titles[index]);
        indexTv = (TextView) view.findViewById(indexTvs[index]);
        indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_1));
        lineChart = (LineChart) view.findViewById(R.id.frag6_linechart);
        lineChart.setScaleEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setOnChartGestureListener(getListener());
        //y轴
        lineChart.getAxisRight().setEnabled(false);
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        //x轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setAxisMaximum(20f);
        xAxis.setLabelCount(20);
        xAxis.setLabelRotationAngle(90);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    private void initData(){
        entryList = new ArrayList<>();
        helper = new MySQLiteOpenHelper(MyApplication.appContext, MySQLiteOpenHelper.DBNAME, null, 1);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from huanjing", null);
        switch (index){
            case 0:
                if(cursor.moveToFirst()){
                    int i=1;
                    do {
                        entryList.add(new Entry(i,cursor.getInt(cursor.getColumnIndex("wendu"))));
                        i++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case 1:
                if(cursor.moveToFirst()){
                    int i=1;
                    do {
                        entryList.add(new Entry(i,cursor.getInt(cursor.getColumnIndex("shidu"))));
                        i++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case 2:
                if(cursor.moveToFirst()){
                    int i=1;
                    do {
                        entryList.add(new Entry(i,cursor.getInt(cursor.getColumnIndex("guangzhao"))));
                        i++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case 3:
                if(cursor.moveToFirst()){
                    int i=1;
                    do {
                        entryList.add(new Entry(i,cursor.getInt(cursor.getColumnIndex("co2"))));
                        i++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case 4:
                if(cursor.moveToFirst()){
                    int i=1;
                    do {
                        entryList.add(new Entry(i,cursor.getInt(cursor.getColumnIndex("pm25"))));
                        i++;
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case 5:
                Cursor cursor1 = db.rawQuery("select * from daolu", null);
                if(cursor1.moveToFirst()){
                    int i=1;
                    do {
                        entryList.add(new Entry(i,cursor1.getInt(cursor1.getColumnIndex("daolu"))));
                        i++;
                    } while (cursor1.moveToNext());
                }
                cursor1.close();
                break;
        }
        db.close();
        lineDataSet = new LineDataSet(entryList, "");
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setColor(Color.rgb(170, 170, 170));
        lineDataSet.setCircleColor(Color.rgb(170, 170, 170));
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private void refreshIndex(){
        titleTv.setText(titles[index]);
        indexTv = (TextView) view.findViewById(indexTvs[index]);
        indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_1));
        xValue = new ArrayList<>();
        helper = new MySQLiteOpenHelper(MyApplication.appContext, MySQLiteOpenHelper.DBNAME, null, 1);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from huanjing", null);
        if(cursor.moveToFirst()){
            int i=1;
            do {
                xValue.add(cursor.getString(cursor.getColumnIndex("time")));
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                if(v<=xValue.size()){
                    return xValue.get((int) v-1);
                }else {
                    return "";
                }
            }
        });
        lineChart.invalidate();
    }

    private OnChartGestureListener getListener(){
        onChartListener = new OnChartGestureListener() {
            @Override
            public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                indexTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                if(v>0 && index<5){
                    index++;
                    initData();
                    refreshIndex();
                }
                if(v<0 && index>0){
                    index--;
                    initData();
                    refreshIndex();
                }
//                Log.d("TAG", "onChartFling: index="+index+"     v="+v);
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
        return onChartListener;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(isAdded()){
                initData();
                refreshIndex();
//                Log.d("TAG", "handleMessage: m=");
            }
        }
    };
}
