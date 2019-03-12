package com.app.trafficclient.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZzjChongzhi;
import com.app.trafficclient.adapter.ZzjChongzhiAdapter;

import java.util.ArrayList;
import java.util.List;

public class Activity_Item_3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private List<String> stringList = new ArrayList<>();
    private String dangqianSelect;

    private ListView listView;
    private ZzjChongzhiAdapter chongzhiAdapter;
    private List<ZzjChongzhi> chongzhiList = new ArrayList<>();

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_layout_3);

        textView = findViewById(R.id.zzj_textView_NoDatatishi);

        spinner = findViewById(R.id.zzj_spinner_zhangdanpaixu_activity3);
        stringList.add("降序排序");
        stringList.add("升序排序");
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        listView = findViewById(R.id.zzj_listView_chongzhijilu);

        jiangxuPaixu();

        Button button = findViewById(R.id.zzj_button_chaxun_activity3);
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

    private void jiangxuPaixu() {

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
        chongzhiAdapter = new ZzjChongzhiAdapter(this, R.layout.zzj_chongzhi_item,chongzhiList);
        listView.setAdapter(chongzhiAdapter);
    }

    private void shengxuPaixu() {

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
        chongzhiAdapter = new ZzjChongzhiAdapter(this, R.layout.zzj_chongzhi_item,chongzhiList);
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
