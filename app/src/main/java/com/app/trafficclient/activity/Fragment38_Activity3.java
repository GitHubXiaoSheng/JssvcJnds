package com.app.trafficclient.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.trafficclient.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Fragment38_Activity3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private Spinner myPinner;
    private ArrayAdapter adapter;
    private List<String> stringList = new ArrayList<>();
    private String dangqianSelect;

    private EditText editText_name,editText_tel;
    private TextView textView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_fragment38_3);
        ActivityArray.addActivity(this);

        textView_title = findViewById(R.id.zzj_title_textView);
        editText_name = findViewById(R.id.zzj_editText_name);
        editText_tel = findViewById(R.id.zzj_editText_tel);
        SharedPreferences sharedPreferences = getSharedPreferences("select",MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("sites",MODE_PRIVATE);

        String list = sharedPreferences1.getString(sharedPreferences.getInt("id", 0)+"","" );
        try {
            JSONArray jsonArray = new JSONArray(list);
            for (int i = 0; i < jsonArray.length(); i++) {
                stringList.add(jsonArray.get(i)+"");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textView_title.setText(sharedPreferences.getString("qishidian",""));

        myPinner = findViewById(R.id.zzj_spinner_didian);
        adapter = new ArrayAdapter(Fragment38_Activity3.this,android.R.layout.simple_list_item_1,stringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myPinner.setAdapter(adapter);
        myPinner.setOnItemSelectedListener(this);

        ImageView imageView = findViewById(R.id.zzj_back3_Image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button button = findViewById(R.id.zzj_f_6_3_Next_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText_name.getText().toString().trim();
                String tel = editText_tel.getText().toString().trim();
                SharedPreferences.Editor editor = getSharedPreferences("NameTel",MODE_PRIVATE).edit();
                editor.putString("name", name);
                editor.putString("tel", tel);
                editor.putString("didian", dangqianSelect);
                editor.apply();
                if (name.equals("") || tel.equals("")) {
                    Toast.makeText(Fragment38_Activity3.this,"姓名或电话号码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Fragment38_Activity3.this, Fragment38_Activity4.class);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        dangqianSelect = stringList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
