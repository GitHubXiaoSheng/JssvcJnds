package com.app.trafficclient.fragment;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_item_5 extends Fragment {

    private LinearLayout linearLayout_1,linearLayout_2,linearLayout_3,linearLayout_4,linearLayout_5,linearLayout_6;
    private TextView textView_1,textView_2,textView_3,textView_4,textView_5,textView_6;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    private int dangqianId_1,dangqianId_2;
    private ContentValues values_1,values_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_5, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(), MySQLiteOpenHelper.DBNAME, null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                addData();
            }
        },0,3000);

    }

    private void addData() {
        Log.d("TAG", "addData: 111");
        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int wendu = jsonObject.getInt("temperature");
                    int shidu = jsonObject.getInt("humidity");
                    int guangzhao = jsonObject.getInt("LightIntensity");
                    int co2 = jsonObject.getInt("co2");
                    int pm25 = jsonObject.getInt("pm2.5");
                    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                    Date date = new Date(System.currentTimeMillis());

                    Cursor cursor = db.rawQuery("select * from huanjing", null);
                    if (cursor.moveToFirst()) {
                        do {
                            dangqianId_1 = cursor.getInt(cursor.getColumnIndex("id"));
                        }while (cursor.moveToNext());
                    }
                    Log.d("当前数据为",+ cursor.getCount() + "条！");
                    values_1 = new ContentValues();
                    if (cursor.getCount() < 20) {
                        values_1.clear();
                        values_1.put("wendu",wendu);
                        values_1.put("shidu",shidu);
                        values_1.put("guangzhao",guangzhao);
                        values_1.put("co2",co2);
                        values_1.put("pm25",pm25);
                        values_1.put("time",format.format(date));
                        db.insert("huanjing", null, values_1);
                    }else {
                        db.delete("huanjing", "id = ?", new String[]{ String.valueOf(dangqianId_1- 19)});
                        values_1.clear();
                        values_1.put("wendu",wendu);
                        values_1.put("shidu",shidu);
                        values_1.put("guangzhao",guangzhao);
                        values_1.put("co2",co2);
                        values_1.put("pm25",pm25);
                        values_1.put("time",format.format(date));
                        db.insert("huanjing", null, values_1);
                    }

                    textView_1.setText(wendu+"");
                    textView_2.setText(shidu+"");
                    textView_3.setText(guangzhao+"");
                    textView_4.setText(co2+"");
                    textView_5.setText(pm25+"");

                    if (wendu > 35) {
                        linearLayout_1.setBackgroundColor(Color.parseColor("#ff0000"));
                    }else {
                        linearLayout_1.setBackgroundColor(Color.parseColor("#00ff00"));
                    }
                    if (shidu > 50) {
                        linearLayout_2.setBackgroundColor(Color.parseColor("#ff0000"));
                    }else {
                        linearLayout_2.setBackgroundColor(Color.parseColor("#00ff00"));
                    }
                    if (guangzhao > 3000) {
                        linearLayout_3.setBackgroundColor(Color.parseColor("#ff0000"));
                    }else {
                        linearLayout_3.setBackgroundColor(Color.parseColor("#00ff00"));
                    }
                    if (co2 > 7500) {
                        linearLayout_4.setBackgroundColor(Color.parseColor("#ff0000"));
                    }else {
                        linearLayout_4.setBackgroundColor(Color.parseColor("#00ff00"));
                    }
                    if (pm25 > 200) {
                        linearLayout_5.setBackgroundColor(Color.parseColor("#ff0000"));
                    }else {
                        linearLayout_5.setBackgroundColor(Color.parseColor("#00ff00"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetRoadStatus", "{\"RoadId\":1,\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int daolu = jsonObject.getInt("Status");

                    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                    Date date = new Date(System.currentTimeMillis());

                    Cursor cursor = db.rawQuery("select * from daolu", null);
                    if (cursor.moveToFirst()) {
                        do {
                            dangqianId_2 = cursor.getInt(cursor.getColumnIndex("id"));
                        }while (cursor.moveToNext());
                    }
                    Log.d("当前数据为",+ cursor.getCount() + "条！");
                    values_2 = new ContentValues();
                    if (cursor.getCount() < 20) {
                        values_2.clear();
                        values_2.put("daolu",daolu);
                        values_2.put("time",format.format(date));
                        db.insert("daolu", null, values_2);
                    }else {
                        db.delete("daolu", "id = ?", new String[]{ String.valueOf(dangqianId_2- 19)});
                        values_2.clear();
                        values_2.put("daolu",daolu);
                        values_2.put("time",format.format(date));
                        db.insert("daolu", null, values_2);
                    }

                    textView_6.setText(daolu+"");
                    if (daolu > 3) {
                        linearLayout_6.setBackgroundColor(Color.parseColor("#ff0000"));
                    }else {
                        linearLayout_6.setBackgroundColor(Color.parseColor("#00ff00"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }

    private void initView() {
        linearLayout_1 = getActivity().findViewById(R.id.zzj_hezi_1);
        linearLayout_2 = getActivity().findViewById(R.id.zzj_hezi_2);
        linearLayout_3 = getActivity().findViewById(R.id.zzj_hezi_3);
        linearLayout_4 = getActivity().findViewById(R.id.zzj_hezi_4);
        linearLayout_5 = getActivity().findViewById(R.id.zzj_hezi_5);
        linearLayout_6 = getActivity().findViewById(R.id.zzj_hezi_6);
        textView_1 = getActivity().findViewById(R.id.zzj_textView_yuan_1);
        textView_2 = getActivity().findViewById(R.id.zzj_textView_yuan_2);
        textView_3 = getActivity().findViewById(R.id.zzj_textView_yuan_3);
        textView_4 = getActivity().findViewById(R.id.zzj_textView_yuan_4);
        textView_5 = getActivity().findViewById(R.id.zzj_textView_yuan_5);
        textView_6 = getActivity().findViewById(R.id.zzj_textView_yuan_6);
    }

}
