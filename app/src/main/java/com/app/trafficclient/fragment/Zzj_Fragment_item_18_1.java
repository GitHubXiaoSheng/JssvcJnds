package com.app.trafficclient.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZzjXiaoxi;
import com.app.trafficclient.adapter.ZzjXiaoxiArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Zzj_Fragment_item_18_1 extends Fragment {

    private Spinner spinner;
    private List<String> stringList = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    private List<ZzjXiaoxi> zzjXiaoxiList = new ArrayList<>();
    private ZzjXiaoxi zzjXiaoxi;
    private ZzjXiaoxiArrayAdapter zzjXiaoxiArrayAdapter;
    private ListView listView;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zzj_fragment_item_layout_18_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), MySQLiteOpenHelper.DBNAME, null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();
        initView();
        addData(0);

    }

    private void initView(){
        listView = getActivity().findViewById(R.id.zzj_fragment_18_1_listView);
        textView = getActivity().findViewById(R.id.zzj_fragment_18_1_NoData);
        spinner = getActivity().findViewById(R.id.zzj_fragment18_1_spinner);
        stringList.add("全部");
        stringList.add("温度");
        stringList.add("湿度");
        stringList.add("光照强度");
        stringList.add("CO2");
        stringList.add("PM2.5");
        stringList.add("道路状况");
        arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (stringList.get(i).equals("全部")) {
                    addData(0);
                }else if (stringList.get(i).equals("温度")) {
                    addData(1);
                }else if (stringList.get(i).equals("湿度")) {
                    addData(2);
                }else if (stringList.get(i).equals("光照强度")) {
                    addData(3);
                }else if (stringList.get(i).equals("CO2")) {
                    addData(4);
                }else if (stringList.get(i).equals("PM2.5")) {
                    addData(5);
                }else if (stringList.get(i).equals("道路状况")) {
                    addData(6);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void addData(int i) {
        zzjXiaoxiList.clear();
        if (i == 0) {
            Cursor cursor = db.rawQuery("select * from tongzhi_quanbu", null);
            if (cursor.moveToFirst()) {
                do {
                    zzjXiaoxi = new ZzjXiaoxi(null,cursor.getString(cursor.getColumnIndex("leixing")),cursor.getString(cursor.getColumnIndex("zhi")),cursor.getString(cursor.getColumnIndex("dq_zhi")));
                    zzjXiaoxiList.add(zzjXiaoxi);
                } while (cursor.moveToNext());
            }
            for (int j = 0; j < zzjXiaoxiList.size(); j++) {
                zzjXiaoxiList.get(j).setXuhao((j+1)+"");
            }
            zzjXiaoxiArrayAdapter = new ZzjXiaoxiArrayAdapter(getActivity(), R.layout.zzj_fragment_18_1_item, zzjXiaoxiList);
            listView.setAdapter(zzjXiaoxiArrayAdapter);
        } else if (i == 1) {
            Cursor cursor = db.rawQuery("select * from tongzhi_wendu", null);
            if (cursor.moveToFirst()) {
                do {
                    zzjXiaoxi = new ZzjXiaoxi(null,cursor.getString(cursor.getColumnIndex("leixing")),cursor.getString(cursor.getColumnIndex("zhi")),cursor.getString(cursor.getColumnIndex("dq_zhi")));
                    zzjXiaoxiList.add(zzjXiaoxi);
                } while (cursor.moveToNext());
            }
            for (int j = 0; j < zzjXiaoxiList.size(); j++) {
                zzjXiaoxiList.get(j).setXuhao((j+1)+"");
            }
            zzjXiaoxiArrayAdapter = new ZzjXiaoxiArrayAdapter(getActivity(), R.layout.zzj_fragment_18_1_item, zzjXiaoxiList);
            listView.setAdapter(zzjXiaoxiArrayAdapter);
        } else if (i == 2) {
            Cursor cursor = db.rawQuery("select * from tongzhi_shidu", null);
            if (cursor.moveToFirst()) {
                do {
                    zzjXiaoxi = new ZzjXiaoxi(null,cursor.getString(cursor.getColumnIndex("leixing")),cursor.getString(cursor.getColumnIndex("zhi")),cursor.getString(cursor.getColumnIndex("dq_zhi")));
                    zzjXiaoxiList.add(zzjXiaoxi);
                } while (cursor.moveToNext());
            }
            for (int j = 0; j < zzjXiaoxiList.size(); j++) {
                zzjXiaoxiList.get(j).setXuhao((j+1)+"");
            }
            zzjXiaoxiArrayAdapter = new ZzjXiaoxiArrayAdapter(getActivity(), R.layout.zzj_fragment_18_1_item, zzjXiaoxiList);
            listView.setAdapter(zzjXiaoxiArrayAdapter);
        } else if (i == 3) {
            Cursor cursor = db.rawQuery("select * from tongzhi_guangzhao", null);
            if (cursor.moveToFirst()) {
                do {
                    zzjXiaoxi = new ZzjXiaoxi(null,cursor.getString(cursor.getColumnIndex("leixing")),cursor.getString(cursor.getColumnIndex("zhi")),cursor.getString(cursor.getColumnIndex("dq_zhi")));
                    zzjXiaoxiList.add(zzjXiaoxi);
                } while (cursor.moveToNext());
            }
            for (int j = 0; j < zzjXiaoxiList.size(); j++) {
                zzjXiaoxiList.get(j).setXuhao((j+1)+"");
            }
            zzjXiaoxiArrayAdapter = new ZzjXiaoxiArrayAdapter(getActivity(), R.layout.zzj_fragment_18_1_item, zzjXiaoxiList);
            listView.setAdapter(zzjXiaoxiArrayAdapter);
        } else if (i == 4) {
            Cursor cursor = db.rawQuery("select * from tongzhi_co2", null);
            if (cursor.moveToFirst()) {
                do {
                    zzjXiaoxi = new ZzjXiaoxi(null,cursor.getString(cursor.getColumnIndex("leixing")),cursor.getString(cursor.getColumnIndex("zhi")),cursor.getString(cursor.getColumnIndex("dq_zhi")));
                    zzjXiaoxiList.add(zzjXiaoxi);
                } while (cursor.moveToNext());
            }
            for (int j = 0; j < zzjXiaoxiList.size(); j++) {
                zzjXiaoxiList.get(j).setXuhao((j+1)+"");
            }
            zzjXiaoxiArrayAdapter = new ZzjXiaoxiArrayAdapter(getActivity(), R.layout.zzj_fragment_18_1_item, zzjXiaoxiList);
            listView.setAdapter(zzjXiaoxiArrayAdapter);
        } else if (i == 5) {
            Cursor cursor = db.rawQuery("select * from tongzhi_pm25", null);
            if (cursor.moveToFirst()) {
                do {
                    zzjXiaoxi = new ZzjXiaoxi(null,cursor.getString(cursor.getColumnIndex("leixing")),cursor.getString(cursor.getColumnIndex("zhi")),cursor.getString(cursor.getColumnIndex("dq_zhi")));
                    zzjXiaoxiList.add(zzjXiaoxi);
                } while (cursor.moveToNext());
            }
            for (int j = 0; j < zzjXiaoxiList.size(); j++) {
                zzjXiaoxiList.get(j).setXuhao((j+1)+"");
            }
            zzjXiaoxiArrayAdapter = new ZzjXiaoxiArrayAdapter(getActivity(), R.layout.zzj_fragment_18_1_item, zzjXiaoxiList);
            listView.setAdapter(zzjXiaoxiArrayAdapter);
        } else if (i == 6) {
            Cursor cursor = db.rawQuery("select * from tongzhi_daolu", null);
            if (cursor.moveToFirst()) {
                do {
                    zzjXiaoxi = new ZzjXiaoxi(null,cursor.getString(cursor.getColumnIndex("leixing")),cursor.getString(cursor.getColumnIndex("zhi")),cursor.getString(cursor.getColumnIndex("dq_zhi")));
                    zzjXiaoxiList.add(zzjXiaoxi);
                } while (cursor.moveToNext());
            }
            for (int j = 0; j < zzjXiaoxiList.size(); j++) {
                zzjXiaoxiList.get(j).setXuhao((j+1)+"");
            }
            zzjXiaoxiArrayAdapter = new ZzjXiaoxiArrayAdapter(getActivity(), R.layout.zzj_fragment_18_1_item, zzjXiaoxiList);
            listView.setAdapter(zzjXiaoxiArrayAdapter);
        }
        if (zzjXiaoxiList.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else {
            textView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

}
