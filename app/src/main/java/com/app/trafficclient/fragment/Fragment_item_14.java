package com.app.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_item_14 extends Fragment {

    private ImageView imageView_shuaxin;
    private TextView textView_jinritianqi,textView_jinritianqifanwei;
    private TextView textView_1,textView_2,textView_3,textView_4,textView_5,textView_6,textView_7,textView_8,textView_9,textView_10;

    private Timer timer;

    private LineChart lineChart;
    private List<Entry> entryList = new ArrayList<>();
    private List<Entry> entryList2 = new ArrayList<>();
    private LineDataSet lineDataSet;
    private LineDataSet lineDataSet2;
    private LineData lineData;
    private List<ILineDataSet> iLineDataSetList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_14, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        addData();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                addData2();
            }
        },0,3000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private void addData() {
        HttpRequest.post("GetSenseByName", "{\"SenseName\":\"temperature\", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    textView_jinritianqi.setText(jsonObject.getString("temperature") + "℃");
                    textView_jinritianqifanwei.setText("今天：" + (Integer.valueOf(jsonObject.getString("temperature")) - 7) + " - " + (Integer.valueOf(jsonObject.getString("temperature")) + 5) + "℃");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
    }

    private void addData2() {
        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int guangzhao = jsonObject.getInt("LightIntensity");
                    int wendu = jsonObject.getInt("temperature");
                    int co2 = jsonObject.getInt("co2");
                    int pm25 = jsonObject.getInt("pm2.5");

                    if (guangzhao < 1000) {
                        textView_1.setText("弱(" + guangzhao + ")");
                        textView_2.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                    } else if (1000 <= guangzhao && guangzhao <= 3000) {
                        textView_1.setText("中等(" + guangzhao + ")");
                        textView_2.setText("涂擦SPF大于15、PA+防晒护肤品");
                    } else if (3000 < guangzhao){
                        textView_1.setText("强(" + guangzhao + ")");
                        textView_2.setText("尽量减少外出，需要涂抹高倍数防晒霜");
                    }

                    if (wendu < 8) {
                        textView_3.setText("容易发(" + wendu + ")");
                        textView_4.setText("温度低，风较大，较易发生感冒，注意防护");
                    } else if (8 <= wendu){
                        textView_3.setText("少发(" + wendu + ")");
                        textView_4.setText("无明显降温，感冒机率较低");
                    }

                    if (wendu < 12) {
                        textView_5.setText("冷(" + wendu + ")");
                        textView_6.setText("建议穿长袖衬衫、单裤等服装");
                    } else if (12 <= wendu && wendu <= 21) {
                        textView_5.setText("舒适(" + wendu + ")");
                        textView_6.setText("建议穿短袖衬衫、单裤等服装");
                    } else if (21 < wendu){
                        textView_5.setText("热(" + wendu + ")");
                        textView_6.setText("适合穿T恤、短薄外套等夏季服装");
                    }

                    if (co2 < 3000) {
                        textView_7.setText("适宜(" + co2 + ")");
                        textView_8.setText("气候适宜，推荐您进行户外运动");
                    } else if (3000 <= co2 && co2 <= 6000) {
                        textView_7.setText("中(" + co2 + ")");
                        textView_8.setText("易感人群应适当减少室外活动");
                    } else if (6000 < co2){
                        textView_7.setText("较不宜(" + co2 + ")");
                        textView_8.setText("空气氧气含量低，请在室内进行休闲运动");
                    }

                    if (pm25 < 30) {
                        textView_9.setText("优(" + pm25 + ")");
                        textView_10.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                    } else if (30 <= pm25 && pm25 <= 100) {
                        textView_9.setText("良(" + pm25 + ")");
                        textView_10.setText("易感人群应适当减少室外活动");
                    } else if (100 < pm25){
                        textView_9.setText("污染(" + pm25 + ")");
                        textView_10.setText("空气质量差，不适合户外活动");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
    }

    private String[] x_zhou = {"昨天", "今天", "明天", "周五" , "周六"  ,"周日"};
    private void initView() {
        imageView_shuaxin = getActivity().findViewById(R.id.zzj_imageView_fragment14_shuaxin);
        imageView_shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
        textView_jinritianqi = getActivity().findViewById(R.id.zzj_textView_fragment14_jinriwendu);
        textView_jinritianqifanwei = getActivity().findViewById(R.id.zzj_textView_fragment14_jinriwendu_fanwei);

        textView_1 = getActivity().findViewById(R.id.zzj_textView_fragment14_dengji_1);
        textView_2 = getActivity().findViewById(R.id.zzj_textView_fragment14_xiangqing_1);
        textView_3 = getActivity().findViewById(R.id.zzj_textView_fragment14_dengji_2);
        textView_4 = getActivity().findViewById(R.id.zzj_textView_fragment14_xiangqing_2);
        textView_5 = getActivity().findViewById(R.id.zzj_textView_fragment14_dengji_3);
        textView_6 = getActivity().findViewById(R.id.zzj_textView_fragment14_xiangqing_3);
        textView_7 = getActivity().findViewById(R.id.zzj_textView_fragment14_dengji_4);
        textView_8 = getActivity().findViewById(R.id.zzj_textView_fragment14_xiangqing_4);
        textView_9 = getActivity().findViewById(R.id.zzj_textView_fragment14_dengji_5);
        textView_10 = getActivity().findViewById(R.id.zzj_textView_fragment14_xiangqing_5);

        lineChart = getActivity().findViewById(R.id.zzj_lineChart_fragment_14);

        entryList.add(new Entry(0, (float)Math.random() * 5 + 10));
        entryList.add(new Entry(1, (float)Math.random() * 5 + 10));
        entryList.add(new Entry(2, (float)Math.random() * 5 + 10));
        entryList.add(new Entry(3, (float)Math.random() * 5 + 10));
        entryList.add(new Entry(4, (float)Math.random() * 5 + 10));
        entryList.add(new Entry(5, (float)Math.random() * 5 + 10));

        entryList2.add(new Entry(0, (float)Math.random() * 10 + 25));
        entryList2.add(new Entry(1, (float)Math.random() * 10 + 25));
        entryList2.add(new Entry(2, (float)Math.random() * 10 + 25));
        entryList2.add(new Entry(3, (float)Math.random() * 10 + 25));
        entryList2.add(new Entry(4, (float)Math.random() * 10 + 25));
        entryList2.add(new Entry(5, (float)Math.random() * 10 + 25));

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(5);
        xAxis.setLabelCount(6,true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                Log.d("TAG", "getFormattedValue: v="+v);
                return x_zhou[(int) v];
            }
        });

        YAxis yAxis_left = lineChart.getAxisLeft();
        yAxis_left.setDrawLabels(false);
        yAxis_left.setDrawAxisLine(false);

        YAxis yAxis_right = lineChart.getAxisRight();
        yAxis_right.setEnabled(false);

        lineDataSet = new LineDataSet(entryList,"");
        lineDataSet.setCircleColor(Color.parseColor("#0000ff"));
        lineDataSet.setColor(Color.parseColor("#0000ff"));
        lineDataSet2 = new LineDataSet(entryList2,"");
        lineDataSet2.setCircleColor(Color.parseColor("#ff0000"));
        lineDataSet2.setColor(Color.parseColor("#ff0000"));

        iLineDataSetList.add(lineDataSet);
        iLineDataSetList.add(lineDataSet2);

        lineData = new LineData(iLineDataSetList);
        lineChart.setDescription(null);
        lineChart.setData(lineData);
        lineChart.getLegend().setEnabled(false);
        lineChart.invalidate();
    }

}
