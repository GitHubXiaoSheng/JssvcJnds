package com.app.trafficclient.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.Zzj_chongzhi;
import com.app.trafficclient.adapter.Zzj_chongzhi_ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Zzj_Fragment_45_3 extends Fragment {

    private Zzj_chongzhi_ArrayAdapter zzj_chongzhi_arrayAdapter;
    private List<Zzj_chongzhi> zzj_chongzhiList = new ArrayList<>();

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zzj_layout_fragment_45_3, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(), MySQLiteOpenHelper.DBNAME,null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();

        addData();

        zzj_chongzhi_arrayAdapter = new Zzj_chongzhi_ArrayAdapter(getContext(), R.layout.zzj_fragment_45_3_item, zzj_chongzhiList);
        ListView listView = getActivity().findViewById(R.id.zzj_f_7_3_listView);
        listView.setAdapter(zzj_chongzhi_arrayAdapter);

    }

    private void addData() {
        Cursor cursor = db.rawQuery("select * from chongzhidejilu", null);
        if (cursor.moveToFirst()) {
            do {
                Zzj_chongzhi zzj_chongzhi = new Zzj_chongzhi(null,cursor.getString(cursor.getColumnIndex("chehao")), cursor.getString(cursor.getColumnIndex("jine")), cursor.getString(cursor.getColumnIndex("shijian")));
                zzj_chongzhiList.add(zzj_chongzhi);
            } while (cursor.moveToNext());
        }
        for (int i = 0; i < zzj_chongzhiList.size(); i++) {
            zzj_chongzhiList.get(i).setXuhao((i+1)+"");
        }
        db.close();
    }


}
