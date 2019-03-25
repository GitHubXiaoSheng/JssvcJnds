package com.app.trafficclient.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.Fankui;
import com.app.trafficclient.adapter.FankuiAdapter;

import java.util.ArrayList;
import java.util.List;

public class Zzj_yijianActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView_back;

    private List<Fankui> fankuiList = new ArrayList<>();
    private FankuiAdapter fankuiAdapter;
    private ListView listView;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzj_yijian);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(Zzj_yijianActivity.this,"sqldb.db",null,1);
        db = mySQLiteOpenHelper.getWritableDatabase();

        textView_back = findViewById(R.id.textView_back_fk);
        textView_back.setOnClickListener(this);

        addData();
        fankuiAdapter = new FankuiAdapter(Zzj_yijianActivity.this, R.layout.zzj_item_fankui, fankuiList);
        listView = findViewById(R.id.yijian_ListView);
        listView.setAdapter(fankuiAdapter);

    }


    private void addData() {
        Cursor cursor = db.rawQuery("select * from yijianfankui", null);
        if (cursor.moveToFirst()) {
            do {
                Fankui fankui = new Fankui("标题："+cursor.getString(cursor.getColumnIndex("title")), "提交时间："+cursor.getString(cursor.getColumnIndex("time")));
                fankuiList.add(fankui);
            } while (cursor.moveToNext());
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_back_fk:
                finish();
                break;
            default:
                break;
        }
    }
}
