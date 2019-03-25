package com.app.trafficclient.fragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.activity.Zzj_yijianActivity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Fragment_item_31 extends Fragment implements View.OnClickListener {

    private EditText editText_title,editText_content,editText_tel;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_31, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(),"sqldb.db",null,1);
        db = mySQLiteOpenHelper.getWritableDatabase();

        editText_title = getActivity().findViewById(R.id.title_editView);
        editText_content = getActivity().findViewById(R.id.content_editView);
        editText_tel = getActivity().findViewById(R.id.tel_editView);

        Button button = getActivity().findViewById(R.id.tijiao_button);
        button.setOnClickListener(this);
        TextView textView = getActivity().findViewById(R.id.textView_yijian);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_yijian:
                Intent intent = new Intent(getActivity(), Zzj_yijianActivity.class);
                startActivity(intent);
                break;
            case R.id.tijiao_button:
                String title = editText_title.getText().toString().trim();
                String content = editText_content.getText().toString().trim();
                String tel = editText_tel.getText().toString().trim();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String time = formatter.format(curDate);
                if (!title.isEmpty() && !content.isEmpty() && !tel.isEmpty()) {
                    db = mySQLiteOpenHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("title",title);
                    values.put("content",content);
                    values.put("tel",tel);
                    values.put("time",time);
                    db.insert("yijianfankui",null,values);
                    values.clear();
                    db.close();

                    editText_title.setText("");
                    editText_content.setText("");
                    editText_tel.setText("");
                }else {
                    Toast.makeText(getContext(),"反馈信息不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

}
