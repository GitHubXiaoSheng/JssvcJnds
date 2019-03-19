package com.app.trafficclient.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Zzj_Fragment_item_18_2 extends Fragment {

    private PieChart pieChart;
    private List<PieEntry> pieEntryList = new ArrayList<>();
    private PieDataSet pieDataSet;
    private PieData pieData;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zzj_fragment_item_layout_18_2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), MySQLiteOpenHelper.DBNAME, null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();
        pieChart = getActivity().findViewById(R.id.zzj_fragment_18_2_barChart);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        },0,3000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    addData();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private void addData() {
        pieEntryList.clear();
        Cursor cursor_0 = db.rawQuery("select * from tongzhi_quanbu", null);
        Cursor cursor_1 = db.rawQuery("select * from tongzhi_wendu", null);
        Cursor cursor_2 = db.rawQuery("select * from tongzhi_shidu", null);
        Cursor cursor_3 = db.rawQuery("select * from tongzhi_guangzhao", null);
        Cursor cursor_4 = db.rawQuery("select * from tongzhi_co2", null);
        Cursor cursor_5 = db.rawQuery("select * from tongzhi_pm25", null);
        Cursor cursor_6 = db.rawQuery("select * from tongzhi_daolu", null);

        int quanbu = cursor_0.getCount();
        int wendu = cursor_1.getCount();
        int shidu = cursor_2.getCount();
        int guangzhao = cursor_3.getCount();
        int co2 = cursor_4.getCount();
        int pm25 = cursor_5.getCount();
        int daolu = cursor_6.getCount();

        pieEntryList.add(new PieEntry((float)wendu/(float)quanbu,"温度 " + wendu));
        pieEntryList.add(new PieEntry((float)shidu/(float)quanbu,"湿度 " + shidu));
        pieEntryList.add(new PieEntry((float)guangzhao/(float)quanbu,"光照强度 " + guangzhao));
        pieEntryList.add(new PieEntry((float)co2/(float)quanbu,"CO2 " + co2));
        pieEntryList.add(new PieEntry((float)pm25/(float)quanbu,"PM2.5 " + pm25));
        pieEntryList.add(new PieEntry((float)daolu/(float)quanbu,"道路状况 " + daolu));

        pieDataSet = new PieDataSet(pieEntryList, "");

        List<Integer> colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#BFDD7B"));
        colorList.add(Color.parseColor("#E3DCA1"));
        colorList.add(Color.parseColor("#78EA5A"));
        colorList.add(Color.parseColor("#EE80B6"));
        colorList.add(Color.parseColor("#FF23B4"));
        colorList.add(Color.parseColor("#00BFFF"));

        pieDataSet.setValueTextSize(0f);
        pieDataSet.setDrawValues(false);
        pieDataSet.setColors(colorList);
        pieData = new PieData(pieDataSet);

        pieChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        pieChart.setDescription(null);
        pieChart.setDrawEntryLabels(false);
        pieChart.setData(pieData);
        pieChart.setHoleRadius(1f);
        pieChart.setTransparentCircleRadius(1f);
        pieChart.setRotationEnabled(false);
        pieChart.invalidate();
    }

}
