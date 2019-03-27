package com.app.trafficclient.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.trafficclient.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment38_Activity2 extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    private CalendarView myCalendarView;
    private TextView textView_riqi;
    private Button button_Next;

    private List<String> list = new ArrayList<>();
    private String aaa = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_fragment38_2);
        ActivityArray.addActivity(this);

        myCalendarView = findViewById(R.id.zzj_f_6_calendarView);
        myCalendarView.setOnDateChangeListener(this);
        textView_riqi = findViewById(R.id.zzj_f_6_selectRiqi_TextView);
        button_Next = findViewById(R.id.zzj_f_6_2_Next_Button);
        button_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView_riqi.getText().toString().isEmpty()) {
                    Toast.makeText(Fragment38_Activity2.this, "请选择日期", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Fragment38_Activity2.this, Fragment38_Activity3.class);
                    startActivity(intent);
                }
            }
        });

        ImageView imageView = findViewById(R.id.zzj_back2_Image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        aaa = "";
        if (list.size() == 0) {
            list.add(year + "-" + (month + 1) + "-" + dayOfMonth + "，");
        } else {
            test(year, month, dayOfMonth);
        }

        for (int i = 0; i < list.size(); i++) {
            Log.d("数据呢" + i, list.get(i));
            aaa += list.get(i);
        }

        SharedPreferences.Editor editor = getSharedPreferences("riqi", MODE_PRIVATE).edit();
        editor.putString("riqi", aaa);
        editor.apply();
        textView_riqi.setText(aaa);
    }

    private void test(int s1, int s2, int s3) {
        int is = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i).equals(s1 + "-" + (s2 + 1) + "-" + s3 + "，"))) {

            } else {
                Log.d("选择", "1");
                is++;
                Toast.makeText(Fragment38_Activity2.this, "已选择该日期", Toast.LENGTH_SHORT).show();
            }
        }
        if (is == 0) {
            list.add(s1 + "-" + (s2 + 1) + "-" + s3 + "，");
        }
    }
}