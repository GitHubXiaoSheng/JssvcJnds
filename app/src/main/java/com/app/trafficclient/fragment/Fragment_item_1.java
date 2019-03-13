package com.app.trafficclient.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_item_1 extends Fragment {

    private TextView tv_accountbalance;
    private EditText edt_rechargemoney;
    private Spinner spinner_carnumber;
    private Button btn_query,btn_rechanrge;
    private List<String> carlist;
    private ArrayAdapter carnumberadapter;
    private String chehao;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        http("1");
        mySQLiteOpenHelper=new MySQLiteOpenHelper(getActivity(),MySQLiteOpenHelper.DBNAME,null,1);
        database=mySQLiteOpenHelper.getWritableDatabase();
    }

    void initView(){
        tv_accountbalance=getActivity().findViewById(R.id.tv_account_balance);
        edt_rechargemoney=getActivity().findViewById(R.id.tv_recharge_money);
        spinner_carnumber=getActivity().findViewById(R.id.spinner_rechanrge);
        btn_query=getActivity().findViewById(R.id.btn_query);
        btn_rechanrge=getActivity().findViewById(R.id.btn_recharge);
        carlist=new ArrayList<>();
        for (int i=1;i<=4;i++){
            carlist.add(i+"");
        }
        carnumberadapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,carlist);
        carnumberadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_carnumber.setAdapter(carnumberadapter);
        spinner_carnumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chehao=carlist.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                http(String.valueOf(spinner_carnumber.getSelectedItemPosition()+1));
            }
        });
        btn_rechanrge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String money = edt_rechargemoney.getText().toString().trim();
                recharge(String.valueOf(spinner_carnumber.getSelectedItemPosition()+1),money);
            }
        });
    }

    void http(String str){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("CarId",str);
            jsonObject.put("UserName","user1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpRequest.post("GetCarAccountBalance", jsonObject.toString(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.d("返回信息", jsonObject.getString("Balance"));
                    tv_accountbalance.setText(jsonObject.getString("Balance"));
                    edt_rechargemoney.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }
    void recharge(final String chehao,final String money){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("CarId",chehao);
            jsonObject.put("Money",money);
            jsonObject.put("UserName","user1");
            HttpRequest.post("SetCarAccountRecharge", jsonObject.toString(), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {

                    try {
                        jsonObject.put("CarId",chehao);
                        jsonObject.put("Money",money);
                        jsonObject.put("UserName","user1");
                        HttpRequest.post("SetCarAccountRecharge", jsonObject.toString(), new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                ContentValues values=new ContentValues();
                                values.put("chehao",chehao);
                                values.put("jine",money);
                                values.put("caozuoren","yws");
                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                Date date=new Date(System.currentTimeMillis());
                                values.put("time",simpleDateFormat.format(date));
                                database.insert("chongzhi",null,values);
                                http(String.valueOf(spinner_carnumber.getSelectedItemPosition()+1));
                            }
                        },null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    http(String.valueOf(spinner_carnumber.getSelectedItemPosition()+1));
                }
            },null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
