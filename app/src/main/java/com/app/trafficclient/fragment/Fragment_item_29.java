package com.app.trafficclient.fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_29 extends Fragment implements View.OnClickListener {
    private TextView show_zhishu;
    private LineChart pm2lineChart;
    private LinearLayout huadongbuju;
    private LineDataSet lineDataSet;
    private LineData lineData;
    private List<Entry> pm2Entry;
    private TextView yuan1,yuan2,yuan3;
    List<Integer>colors=new ArrayList<>();
    private NotificationManager notificationManager;
    private Notification notification;


    private int index = 0;


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
        yuan1=getActivity().findViewById(R.id.yuan_pm2);
        yuan2=getActivity().findViewById(R.id.yuan_wendu);
        yuan3=getActivity().findViewById(R.id.yuan_guangzhao);
        yuan1.setOnClickListener(this);
        yuan2.setOnClickListener(this);
        yuan3.setOnClickListener(this);

        pm2Entry=new ArrayList<>();
        for (int i=0;i<5;i++){
            pm2Entry.add(new Entry(i,new Random().nextInt(200)));
        }

         fangfa();
        yuan1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_2));

        colors.add(Color.RED);
        colors.add(Color.RED);
        colors.add(Color.RED);
        colors.add(Color.RED);
        colors.add(Color.RED);
        setcolor();
        shuanxin();

//        for (int i = 0; i < pm2Entry.size(); i++) {
//            if (pm2Entry.get(i).getY() > 150){
//                index = i;
//            }
//        }
//
//        colors.set(index,Color.RED);
//        lineDataSet.setColors(colors);

        XAxis xAxis=pm2lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return null;
            }
        });


        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        xAxis.setLabelCount(6,true);
        YAxis yAxis_left=pm2lineChart.getAxisLeft();
        YAxis yAxis_right=pm2lineChart.getAxisRight();
        yAxis_left.setDrawAxisLine(true);
        yAxis_right.setDrawAxisLine(false);
        yAxis_right.setDrawLabels(false);
        yAxis_left.setDrawLabels(true);
        yAxis_right.setDrawGridLines(false);
        yAxis_left.setDrawGridLines(false);





        notificationManager= (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notification=new Notification.Builder(getContext())
                .setContentTitle("")
                .setContentText("")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notificationManager.notify(1,notification);






    }
    private void setcolor(){

        for (int i = 0; i < pm2Entry.size(); i++) {
            if (pm2Entry.get(i).getY() > 100){
                index = i;
            }
        }

        colors.set(index,Color.RED);
        lineDataSet.setCircleColors(colors);

    }
    private void fangfa(){
        lineDataSet=new LineDataSet(pm2Entry,"");
        lineDataSet.setCircleColor(Color.GREEN);
        lineDataSet.setDrawCircleHole(false);

        lineData=new LineData(lineDataSet);
        lineData.setDrawValues(false);
        pm2lineChart.setData(lineData);
        pm2lineChart.setDescription(null);
        pm2lineChart.getLegend().setFormSize(0);
        pm2lineChart.invalidate();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yuan_pm2:
                show_zhishu.setText("pm2.5指数");
                yuan1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_2));
                yuan2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                yuan3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                pm2Entry.clear();
                shuanxin();
                for (int i=0;i<5;i++){
                    pm2Entry.add(new Entry(i,new Random().nextInt(200)));
                }
            setcolor();
                fangfa();

                break;
            case R.id.yuan_wendu:
                show_zhishu.setText("光照强度指数");
                yuan2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_2));
                yuan1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                yuan3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                pm2Entry.clear();
                shuanxin();
                for (int i=0;i<5;i++){
                    pm2Entry.add(new Entry(i,new Random().nextInt(100*2)));
                }
                setcolor();
                fangfa();

                break;
            case R.id.yuan_guangzhao:
                show_zhishu.setText("空气温度指数");
                yuan3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_2));
                yuan2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                yuan1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                pm2Entry.clear();
                for (int i=0;i<5;i++){
                    pm2Entry.add(new Entry(i,new Random().nextInt(300*3+5)));
                }
                fangfa();
               setcolor();
                shuanxin();
                break;
                default:
                    break;
        }
    }

//    int i = 0;
//    private void  http(){
//        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                try {
//                    if (i < 5){
//                        pm2Entry.add(new Entry(i,Float.valueOf(jsonObject.getString("pm2.5"))));
//                    }else {
//                        i = -1;
//                    }
//                    i ++;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        },null);
//    }
    private Timer timer;
    private void shuanxin(){
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pm2Entry.clear();
                for (int i=0;i<5;i++){
                    pm2Entry.add(new Entry(i,new Random().nextInt(100*3)));
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        },1,3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    fangfa();
                    break;
                default:
                    break;
            }
        }
    };
}
