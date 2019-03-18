package com.app.trafficclient.fragment;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_item_13 extends Fragment {
private TextView show_shijian,show_xingqi,show_wendu,show_shidu,show_pm2;
private TextView text1,text2,text3,text4,text5;
private TextView color1,color2,color3,color4,color5;
private ImageView jiaojinhboy,jiaojinggirl;
private AnimationDrawable animationDrawable;
private AnimationDrawable animationDrawable1;
private ImageView shuanxin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_13, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initview();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-M-d");
        Date date=new Date(System.currentTimeMillis());
       show_shijian.setText( simpleDateFormat.format(date));
        Calendar calendar=Calendar.getInstance();
        String[] str1=new String[]{"Error","日","一","二","三","四","五","六"};
       show_xingqi.setText("\t星期" + str1[calendar.DAY_OF_WEEK]);
        httpGetAllSense();
        shuanxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               httpGetAllSense();
            }
        });
    }
    private  void initview(){
        show_shijian=getActivity().findViewById(R.id.xianshi_data);
        show_xingqi=getActivity().findViewById(R.id.xianshi_xingqi);
        show_wendu=getActivity().findViewById(R.id.show_wendu);
        show_shidu=getActivity().findViewById(R.id.show_shidu);
        show_pm2=getActivity().findViewById(R.id.show_pm2_5);
        text1=getActivity().findViewById(R.id.show_text1);
        text2=getActivity().findViewById(R.id.show_text2);
        text3=getActivity().findViewById(R.id.show_text3);
        text4=getActivity().findViewById(R.id.show_text4);
        text5=getActivity().findViewById(R.id.show_text5);
        color1=getActivity().findViewById(R.id.show_color1);
        color2=getActivity().findViewById(R.id.show_color2);
        color3=getActivity().findViewById(R.id.show_color3);
        color4=getActivity().findViewById(R.id.show_color4);
        color5=getActivity().findViewById(R.id.show_color5);
        shuanxin=getActivity().findViewById(R.id.shuanxin);
        jiaojinhboy=getActivity().findViewById(R.id.jiaojingboy);
        jiaojinggirl=getActivity().findViewById(R.id.jiaojinggirl);
        animationDrawable=(AnimationDrawable)jiaojinhboy.getBackground();
        animationDrawable.start();
        animationDrawable1=(AnimationDrawable)jiaojinggirl.getBackground();
        animationDrawable1.start();
    }
    private void  httpGetAllSense(){
        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("信息",jsonObject.toString());
                try {
                    show_wendu.setText(jsonObject.getString("temperature"));
                    show_shidu.setText(jsonObject.getString("humidity"));
                    show_pm2.setText(jsonObject.getString("pm2.5")+"μg/m3");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }
//   private void reflesh(){
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//               httpGetAllSense();
//            }
//        },1000);
//        httpGetAllSense();
//   }
}
