package com.app.trafficclient.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.trafficclient.R;

public class Fragment38_Activity4 extends AppCompatActivity {

    private TextView textView_title,textView_name,textView_tel,textView_didian,textView_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_fragment38_4);
        ActivityArray.addActivity(this);

        textView_title = findViewById(R.id.zzj_title_textView_f_6_4);
        textView_name = findViewById(R.id.zzj_f_6_4_name);
        textView_tel = findViewById(R.id.zzj_f_6_4_tel);
        textView_didian = findViewById(R.id.zzj_f_6_4_didian);
        textView_time = findViewById(R.id.zzj_f_6_4_time);

        SharedPreferences sharedPreferences1 = getSharedPreferences("select",MODE_PRIVATE);
        textView_title.setText(sharedPreferences1.getString("qishidian",""));

        SharedPreferences sharedPreferences2 = getSharedPreferences("NameTel",MODE_PRIVATE);
        textView_name.setText(sharedPreferences2.getString("name",""));
        textView_tel.setText(sharedPreferences2.getString("tel",""));
        textView_didian.setText(sharedPreferences2.getString("didian",""));
        SharedPreferences sharedPreferences3 = getSharedPreferences("riqi",MODE_PRIVATE);
        textView_time.setText(sharedPreferences3.getString("riqi",""));

        ImageView imageView = findViewById(R.id.zzj_back4_Image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button = findViewById(R.id.zzj_f_6_4_tijiao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityArray.deleteActivity();
            }
        });

    }
}
