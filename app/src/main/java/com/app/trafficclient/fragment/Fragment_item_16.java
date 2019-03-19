package com.app.trafficclient.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.trafficclient.MainActivity;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.Frag16_Adapter;
import com.app.trafficclient.entry.Frag16_entry;
import com.app.trafficclient.usebean.GetUserInfo;
import com.app.trafficclient.usebean.ZL_Balance;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_item_16 extends Fragment {
    private View view;
    private ImageView hearImg;
    private TextView personalTv;
    private LinearLayout personalLayout;
    private TextView recordTv;
    private LinearLayout recordLayout;
    private TextView thresholdTv;
    private LinearLayout thresholdLayout;

    private GetUserInfo.ROWSDETAILBean user;

    //个人中心
    private TextView nameTv;
    private TextView genderTv;
    private TextView phoneTv;
    private TextView idcardTv;
    private TextView registraionTv;

    //充值记录
    private ImageView rHeartImg;
    private TextView rnameTv;
    private TextView sumTv;
    private TextView tipsTv;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private List<Frag16_entry> entryList;
    private Frag16_Adapter adapter;
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;

    //阈值设置
    private TextView currentThresholdTv;
    private EditText nowThresholdEt;
    private Button setBtn;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private String current;
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_layout_16, container, false);
        initView();
        initPersonal("user1");
        setListener();
        return view;
    }

    private void initView(){
        personalTv = (TextView) view.findViewById(R.id.frag16_personal_tv);
        personalLayout = (LinearLayout) view.findViewById(R.id.frag16_personal_layout);
        recordTv = (TextView) view.findViewById(R.id.frag16_record_tv);
        recordLayout = (LinearLayout) view.findViewById(R.id.frag16_record_layout);
        thresholdTv = (TextView) view.findViewById(R.id.frag16_threshold_tv);
        thresholdLayout = (LinearLayout) view.findViewById(R.id.frag16_threshold_layout);

        //个人中心
        hearImg = (ImageView) view.findViewById(R.id.frag16_touxiang_img);
        nameTv = (TextView) view.findViewById(R.id.frag16_name_tv);
        genderTv = (TextView) view.findViewById(R.id.frag16_gender_tv);
        phoneTv = (TextView) view.findViewById(R.id.frag16_phone_tv);
        idcardTv = (TextView) view.findViewById(R.id.frag16_idcard_tv);
        registraionTv = (TextView) view.findViewById(R.id.frag16_registration_tv);

        //充值记录
        rHeartImg = (ImageView) view.findViewById(R.id.frag16_record_touxiang_img);
        rnameTv = (TextView) view.findViewById(R.id.frag16_pname_tv);
        sumTv = (TextView) view.findViewById(R.id.frag16_expenditure_amount_tv);
        tipsTv = (TextView) view.findViewById(R.id.frag16_listtishi_tv);
        recyclerView = (RecyclerView) view.findViewById(R.id.frag16_recycler);
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        entryList = new ArrayList<>();
        adapter = new Frag16_Adapter(getContext(),entryList);
        recyclerView.setAdapter(adapter);

        //阀值设置
        currentThresholdTv = (TextView) view.findViewById(R.id.frag16_threshold_real_tv);
        nowThresholdEt = (EditText) view.findViewById(R.id.frag16_threshold_amount_tv);
        setBtn = (Button) view.findViewById(R.id.frag16_threshold_set_btn);
        preferences = getContext().getSharedPreferences("fazhi", Context.MODE_PRIVATE);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(isAdded()){
//                    Log.d("TAG", "onResponse: 阈值警告！");
                    current = preferences.getString("current", "50");
                    jiance(1);
                }else {
                    timer.cancel();
                }
            }
        },0,3000);
    }

    private void initPersonal(final String username){
        personalTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_underline));
        personalLayout.setVisibility(View.VISIBLE);
        recordTv.setBackgroundDrawable(null);
        recordLayout.setVisibility(View.GONE);
        thresholdTv.setBackgroundDrawable(null);
        thresholdLayout.setVisibility(View.GONE);
        String param = "{\"UserName\":\"user2\"}";
        HttpRequest.post("GetSUserInfo", param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                GetUserInfo userInfo = new Gson().fromJson(jsonObject.toString(), GetUserInfo.class);
                List<GetUserInfo.ROWSDETAILBean> userList = userInfo.getROWS_DETAIL();
                for (int i=0;i<userList.size();i++) {
                    if(userList.get(i).getUsername().equals(username)){
                        user = userList.get(i);
                        Log.d("TAG", "onResponse: user="+userInfo.toString());
                        nameTv.setText("姓名："+user.getPname());
                        if(user.getPsex().equals("男")){
                            hearImg.setImageResource(R.drawable.touxiang_2);
                        }else {
                            hearImg.setImageResource(R.drawable.touxiang_1);
                        }
                        genderTv.setText("性别："+user.getPsex());
                        phoneTv.setText("电话号码："+user.getPtel());
                        idcardTv.setText("身份证号码："+user.getPcardid());
                        registraionTv.setText("注册时间："+user.getPregisterdate());
                        return;
                    }
                }
            }
        },null);
    }

    private void initRecord(){
        entryList.clear();
        personalTv.setBackgroundDrawable(null);
        personalLayout.setVisibility(View.GONE);
        recordTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_underline));
        recordLayout.setVisibility(View.VISIBLE);
        thresholdTv.setBackgroundDrawable(null);
        thresholdLayout.setVisibility(View.GONE);

        if(user.getPsex().equals("男")){
            rHeartImg.setImageResource(R.drawable.touxiang_2);
        }else {
            rHeartImg.setImageResource(R.drawable.touxiang_1);
        }
        rnameTv.setText(user.getPname());

        helper = new MySQLiteOpenHelper(getContext(), MySQLiteOpenHelper.DBNAME, null, 1);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from chongzhi", null);
        int sunAmount = 0;
        if(cursor.getCount()==0){
            tipsTv.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            tipsTv.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            if(cursor.moveToFirst()){
                do {
                    sunAmount += cursor.getInt(cursor.getColumnIndex("jine"));
                    entryList.add(new Frag16_entry(cursor.getString(cursor.getColumnIndex("time")),
                            cursor.getString(cursor.getColumnIndex("caozuoren")),
                            String.valueOf(cursor.getInt(cursor.getColumnIndex("chehao"))),
                            String.valueOf(cursor.getInt(cursor.getColumnIndex("jine"))),
                            "2420"));
                } while (cursor.moveToNext());
            }
            Collections.sort(entryList, new Comparator<Frag16_entry>() {
                @Override
                public int compare(Frag16_entry max, Frag16_entry min) {
                    return min.getTime().compareTo(max.getTime());
                }
            });
        }
        cursor.close();
        DecimalFormat format = new DecimalFormat(",##0.00");
        sumTv.setText(format.format(sunAmount));
        adapter.notifyDataSetChanged();
    }

    private void initThrshold(){
        personalTv.setBackgroundDrawable(null);
        personalLayout.setVisibility(View.GONE);
        recordTv.setBackgroundDrawable(null);
        recordLayout.setVisibility(View.GONE);
        thresholdTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_underline));
        thresholdLayout.setVisibility(View.VISIBLE);
        preferences = getContext().getSharedPreferences("fazhi", Context.MODE_PRIVATE);
        String current = preferences.getString("current", "null");
        if(current.equals("null")){
            currentThresholdTv.setText("当前1~4号小车账户余额告警阈值未设置！");
        }else {
            currentThresholdTv.setText("当前1~4号小车账号余额告警阈值为"+current+"元");
        }
    }

    private void setListener(){
        personalTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPersonal("user1");
            }
        });
        recordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRecord();
            }
        });
        thresholdTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initThrshold();
            }
        });
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nowThreshold = nowThresholdEt.getText().toString().trim();
                if(!nowThreshold.equals("")){
                    editor = preferences.edit();
                    editor.putString("current", nowThreshold);
                    editor.apply();
                    String current = preferences.getString("current", "50");
                    currentThresholdTv.setText("当前1-4号小车账号余额告警阈值为"+current+"元");
                    Toast.makeText(getContext(), "阈值设置成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    //对余额进行检测
    private void  jiance(final int carid){
        String url = "GetCarAccountBalance";
        String parma = "{\"CarId\":"+String.valueOf(carid)+", \"UserName\":\"user1\"}";
        HttpRequest.post(url, parma, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ZL_Balance balance = new Gson().fromJson(jsonObject.toString(), ZL_Balance.class);
                if(balance.getRESULT().equals("S")){
                    if(Integer.valueOf(current)>balance.getBalance()){
                        if(isAdded()){
                            NotificationManager manager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                            Intent inten = new Intent(getContext(), MainActivity.class);
                            PendingIntent intent1 = PendingIntent.getActivity(getContext(),0,inten,0);
                            Notification notification = new NotificationCompat.Builder(getContext())
                                    .setContentTitle("警告！")
                                    .setContentText(String.valueOf(carid)+"号车的当前余额为："+balance.getBalance()+"当前阈值为："+current)
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentIntent(intent1)
                                    .setAutoCancel(true)
                                    .build();
                            manager.notify(1,notification);
                        }
                    }
                    jiance(carid+1);
                }else {
                    return;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyApplication.appContext, "查询错误！", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
