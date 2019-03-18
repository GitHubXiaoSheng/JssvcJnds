package com.app.trafficclient.fragment;

import android.graphics.Color;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_item_17 extends Fragment {
private TextView tv_zwx1,tv_gm1,tv_cy1,tv_yd1,tv_wr1;
private TextView tv_zwx1_miaoshu,tv_gm1_miaoshu,tv_cy1_miaoshu,tv_yd1_miaoshu,tv_wr1_miaoshu;
private LinearLayout hz1,hz2,hz3,hz4,hz5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_17, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initview();
        httpzhishu();
        shaunxin();
    }
    private void initview(){
        tv_zwx1=getActivity().findViewById(R.id.tv_ziwaixian_dengji);
        tv_gm1=getActivity().findViewById(R.id.tv_ganmao_dengji);
        tv_cy1=getActivity().findViewById(R.id.tv_chuaiyi_dengji);
        tv_yd1=getActivity().findViewById(R.id.tv_sport_dengji);
        tv_wr1=getActivity().findViewById(R.id.tv_kongqi_dengji);
        tv_zwx1_miaoshu=getActivity().findViewById(R.id.tv_ziwaixian_miaoshu);
        tv_gm1_miaoshu=getActivity().findViewById(R.id.tv_ganmao_miaoshu);
        tv_cy1_miaoshu=getActivity().findViewById(R.id.tv_chuaiyi_miaoshu);
        tv_yd1_miaoshu=getActivity().findViewById(R.id.tv_sport_miaoshu);
        tv_wr1_miaoshu=getActivity().findViewById(R.id.tv_kongqi_miaoshu);
        hz1=getActivity().findViewById(R.id.yws_hz1);
        hz2=getActivity().findViewById(R.id.yws_hz2);
        hz3=getActivity().findViewById(R.id.yws_hz3);
        hz4=getActivity().findViewById(R.id.yws_hz4);
        hz5=getActivity().findViewById(R.id.yws_hz5);




    }
    private void httpzhishu(){
        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String ziwaixian=jsonObject.getString("LightIntensity");
                    if (Integer.valueOf(ziwaixian)>0&&(Integer.valueOf(ziwaixian)<1000)){
                           tv_zwx1.setText("弱"+"("+ziwaixian+")");
                           tv_zwx1_miaoshu.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                           hz1.setBackgroundColor(Color.RED);
                    }else if (Integer.valueOf(ziwaixian)>=1000&&(Integer.valueOf(ziwaixian)<=3000)){
                        tv_zwx1.setText("中等"+"("+ziwaixian+")");
                        tv_zwx1_miaoshu.setText("涂擦SPF大于15、PA+防晒护肤品");
                        hz1.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }else {
                        tv_zwx1.setText("强"+"("+ziwaixian+")");
                        tv_zwx1_miaoshu.setText("尽量减少外出，需要涂抹高倍数防晒霜");
                        hz1.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }


                    String gaomao=jsonObject.getString("temperature");
                    if (Integer.valueOf(gaomao)>0&&(Integer.valueOf(gaomao)<8)){
                        tv_gm1.setText("较易发"+"("+gaomao+")");
                        tv_gm1_miaoshu.setText("温度低，风较大，较易发生感冒，注意防护");
                        hz2.setBackgroundColor(Color.RED);
                    }else {
                        tv_gm1.setText("少发"+"("+gaomao+")");
                        tv_gm1_miaoshu.setText("无明显降温，感冒机率较低");
                        hz2.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }


                    String chuaiyi=jsonObject.getString("temperature");
                    if (Integer.valueOf(chuaiyi)>0&&(Integer.valueOf(chuaiyi)<12)){
                        tv_cy1.setText("冷"+"("+chuaiyi+")");
                        tv_cy1_miaoshu.setText("建议穿长袖衬衫、单裤等服装");
                        hz3.setBackgroundColor(Color.RED);
                    }else if (Integer.valueOf(chuaiyi)>=12&&(Integer.valueOf(chuaiyi)<=21)){
                        tv_cy1.setText("舒适"+"("+chuaiyi+")");
                        tv_cy1_miaoshu.setText("建议穿短袖衬衫、单裤等服装");
                        hz3.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }else {
                        tv_cy1.setText("热"+"("+chuaiyi+")");
                        tv_cy1_miaoshu.setText("适合穿T恤、短薄外套等夏季服装");
                        hz3.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }



                    String yundong=jsonObject.getString("co2");
                    if (Integer.valueOf(yundong)>0&&(Integer.valueOf(yundong)<3000)){
                        tv_yd1.setText("适宜"+"("+yundong+")");
                        tv_yd1_miaoshu.setText("气候适宜，推荐您进行户外运动");
                        hz4.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }else if (Integer.valueOf(yundong)>=3000&&(Integer.valueOf(yundong)<=6000)){
                        tv_yd1.setText("中"+"("+yundong+")");
                        tv_yd1_miaoshu.setText("易感人群应适当减少室外活动");
                        hz4.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }else {
                        tv_yd1.setText("较不易"+"("+yundong+")");
                        tv_yd1_miaoshu.setText("空气氧气含量低，请在室内进行休闲运动");
                        hz4.setBackgroundColor(Color.RED);
                    }



                    String kongqi=jsonObject.getString("pm2.5");
                    if (Integer.valueOf(kongqi)>0&&(Integer.valueOf(kongqi)<30)){
                        tv_wr1.setText("优"+"("+yundong+")");
                        tv_wr1_miaoshu.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                        hz5.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }else if (Integer.valueOf(kongqi)>=30&&(Integer.valueOf(kongqi)<=100)){
                        tv_wr1.setText("良"+"("+kongqi+")");
                        tv_wr1_miaoshu.setText("易感人群应适当减少室外活动");
                        hz5.setBackgroundColor(Color.parseColor("#DAEDFF"));
                    }else {
                        tv_wr1.setText("污染"+"("+kongqi+")");
                        tv_wr1_miaoshu.setText("空气质量差，不适合户外活动");
                        hz5.setBackgroundColor(Color.RED);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }
    private Timer timer;

    private void shaunxin(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                httpzhishu();
            }
        },1,3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
