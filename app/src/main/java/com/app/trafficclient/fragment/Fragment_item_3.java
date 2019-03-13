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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZzjChongzhi;
import com.app.trafficclient.adapter.ZzjChongzhiAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragment_item_3 extends Fragment  implements AdapterView.OnItemSelectedListener{


    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private List<String> stringList = new ArrayList<>();
    private String dangqianSelect;

    private ListView listView;
    private ZzjChongzhiAdapter chongzhiAdapter;
    private List<ZzjChongzhi> chongzhiList = new ArrayList<>();

    private TextView textView;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_3, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getActivity(), MySQLiteOpenHelper.DBNAME, null, 1);

        textView = getActivity().findViewById(R.id.zzj_textView_NoDatatishi);

        spinner = getActivity().findViewById(R.id.zzj_spinner_zhangdanpaixu_activity3);
        stringList.add("降序排序");
        stringList.add("升序排序");
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        listView = getActivity().findViewById(R.id.zzj_listView_chongzhijilu);

        jiangxuPaixu();

        Button button = getActivity().findViewById(R.id.zzj_button_chaxun_activity3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dangqianSelect.equals("降序排序")) {
                    jiangxuPaixu();
                }else {
                    shengxuPaixu();
                }
            }
        });

    }

    private void shengxuPaixu() {
        chongzhiList.clear();
        db = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from chongzhi", null);
        if (cursor.moveToFirst()) {
            do {
                ZzjChongzhi zzjChongzhi = new ZzjChongzhi("", cursor.getString(cursor.getColumnIndex("chehao")), cursor.getString(cursor.getColumnIndex("jine")), cursor.getString(cursor.getColumnIndex("caozuoren")), cursor.getString(cursor.getColumnIndex("time")));
                chongzhiList.add(zzjChongzhi);
            } while (cursor.moveToNext());
        }
        db.close();
        Collections.sort(chongzhiList, new Comparator<ZzjChongzhi>() {
            @Override
            public int compare(ZzjChongzhi t0, ZzjChongzhi t1) {
                return t0.getChongzhishijian().compareTo(t1.getChongzhishijian());
            }
        });
        for (int i = 0; i < chongzhiList.size(); i++) {
            chongzhiList.get(i).setXuhao((i+1)+"");
        }
        if (chongzhiList.isEmpty()) {
            listView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }else {
            listView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        chongzhiAdapter = new ZzjChongzhiAdapter(getActivity(), R.layout.zzj_chongzhi_item,chongzhiList);
        listView.setAdapter(chongzhiAdapter);
    }

    private void jiangxuPaixu() {
        chongzhiList.clear();
        db = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from chongzhi", null);
        if (cursor.moveToFirst()) {
            do {
                ZzjChongzhi zzjChongzhi = new ZzjChongzhi("", cursor.getString(cursor.getColumnIndex("chehao")), cursor.getString(cursor.getColumnIndex("jine")), cursor.getString(cursor.getColumnIndex("caozuoren")), cursor.getString(cursor.getColumnIndex("time")));
                chongzhiList.add(zzjChongzhi);
            } while (cursor.moveToNext());
        }
        db.close();
        Collections.sort(chongzhiList, new Comparator<ZzjChongzhi>() {
            @Override
            public int compare(ZzjChongzhi t0, ZzjChongzhi t1) {
                return t1.getChongzhishijian().compareTo(t0.getChongzhishijian());
            }
        });
        for (int i = 0; i < chongzhiList.size(); i++) {
            chongzhiList.get(i).setXuhao((i+1)+"");
        }
        if (chongzhiList.isEmpty()) {
            listView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }else {
            listView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        chongzhiAdapter = new ZzjChongzhiAdapter(getActivity(), R.layout.zzj_chongzhi_item,chongzhiList);
        listView.setAdapter(chongzhiAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        dangqianSelect = stringList.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
