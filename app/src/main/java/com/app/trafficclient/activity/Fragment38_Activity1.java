package com.app.trafficclient.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment38_Activity1 extends AppCompatActivity {
    private TextView textView_qishidian,textView_piaojia;
    private ImageView zzj_ditu_Image;

    public static boolean isMobile(String mobile) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_fragment38_1);
        ActivityArray.addActivity(this);

        textView_qishidian = findViewById(R.id.zzj_textView_qishidian);
        textView_piaojia = findViewById(R.id.zzj_textView_piaojia);
        zzj_ditu_Image = findViewById(R.id.zzj_ditu_Image);

        SharedPreferences sharedPreferences2 = getSharedPreferences("sites2",MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = getSharedPreferences("select",MODE_PRIVATE);

        String url = "/" + sharedPreferences2.getString(""+sharedPreferences3.getInt("id",0),"");
        Log.d("图片url", url);
        HttpRequest.bitmap(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                zzj_ditu_Image.setImageBitmap(bitmap);
            }
        },null);

        SharedPreferences sharedPreferences = getSharedPreferences("select",MODE_PRIVATE);
        textView_qishidian.setText(sharedPreferences.getString("qishidian",""));
        textView_piaojia.setText(sharedPreferences.getString("piaojia",""));
        Button button = findViewById(R.id.zzj_f_6_1_Next_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fragment38_Activity1.this, Fragment38_Activity2.class);
                startActivity(intent);
            }
        });

        ImageView imageView = findViewById(R.id.zzj_back_Image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

