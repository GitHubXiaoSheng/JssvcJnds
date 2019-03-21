package com.app.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_item_27 extends Fragment {
    private TextView tv_show_pm2,tv_show_wendu,tv_show_shidu;
private TextView tv_wenzi,tv_wenzi1,tv_xinxi,tv_xinxi1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_27, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        tv_show_pm2=getActivity().findViewById(R.id.tv_show_pm2);
        tv_show_wendu=getActivity().findViewById(R.id.tv_show_wendu);
        tv_show_shidu=getActivity().findViewById(R.id.tv_show_shidu);
        tv_wenzi=getActivity().findViewById(R.id.tv_wenzi);
        tv_wenzi1=getActivity().findViewById(R.id.tv_wenzi1);
        tv_xinxi=getActivity().findViewById(R.id.tv_xinxi);
        tv_xinxi1=getActivity().findViewById(R.id.tv_xinxi1);
        httpstate();
        shaunxin();

    }
    private void httpstate(){
        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String pm2=jsonObject.getString("pm2.5");
                    tv_show_pm2.setText(pm2);
                    String wendu=jsonObject.getString("temperature");
                    tv_show_wendu.setText(wendu);
                    String shidu=jsonObject.getString("humidity");
                    tv_show_shidu.setText(shidu);
                    if (Integer.valueOf(pm2)>=0&&Integer.valueOf(pm2)<=100){
                        tv_wenzi1.setText("良好");
                        tv_xinxi1.setText("气象条件会对晨练影响不大");
                        tv_wenzi1.setTextColor(Color.BLACK);
                    }else  if(Integer.valueOf(pm2)>=100&&Integer.valueOf(pm2)<200){
                        tv_wenzi1.setText("轻度");
                        tv_wenzi1.setTextColor(Color.RED);
                        tv_xinxi1.setText("受天气影响，较不宜晨练");
                    }else  if(Integer.valueOf(pm2)>=200&&Integer.valueOf(pm2)<300){
                        tv_wenzi1.setText("重度");
                        tv_wenzi1.setTextColor(Color.RED);
                        tv_xinxi1.setText("减少外出，出行注意戴口罩");
                    }else {
                        tv_wenzi1.setText("爆表");
                        tv_xinxi1.setText("停止一切外出活动");
                    }


                    if (Integer.valueOf(wendu)>=0&&Integer.valueOf(wendu)<=100){
                        tv_wenzi.setText("非常弱");
                        tv_xinxi.setText("您无需担心紫外线");
                        tv_wenzi.setTextColor(Color.BLACK);
                    }else if (Integer.valueOf(wendu)>=100&&Integer.valueOf(wendu)<=200){
                        tv_wenzi.setText("弱");
                        tv_xinxi.setText("外出适当涂抹低倍数防晒霜");
                        tv_wenzi.setTextColor(Color.BLACK);
                    }else {
                        tv_wenzi.setText("强");
                        tv_wenzi.setTextColor(Color.RED);
                        tv_xinxi.setText("外出需要涂抹中倍数防晒霜");
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
                httpstate();
            }
        },1,3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
