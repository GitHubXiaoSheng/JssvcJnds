package com.app.trafficclient.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Zzj_Fragment_45_1 extends Fragment implements View.OnClickListener {

    private TextView textView_1,textView_2,textView_3,textView_4;
    private TextView textView_yuan_1,textView_yuan_2,textView_yuan_3,textView_yuan_4;
    private LinearLayout linearLayout_1,linearLayout_2,linearLayout_3,linearLayout_4;

    private int chongzhi;
    private int jin_e;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zzj_layout_fragment_45_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(), MySQLiteOpenHelper.DBNAME, null, 1);

        textView_1 = getActivity().findViewById(R.id.zzj_1_hao_che);
        textView_2 = getActivity().findViewById(R.id.zzj_2_hao_che);
        textView_3 = getActivity().findViewById(R.id.zzj_3_hao_che);
        textView_4 = getActivity().findViewById(R.id.zzj_4_hao_che);
        textView_yuan_1 = getActivity().findViewById(R.id.zzj_1_hao_che_yuan);
        textView_yuan_2 = getActivity().findViewById(R.id.zzj_2_hao_che_yuan);
        textView_yuan_3 = getActivity().findViewById(R.id.zzj_3_hao_che_yuan);
        textView_yuan_4 = getActivity().findViewById(R.id.zzj_4_hao_che_yuan);
        linearLayout_1 = getActivity().findViewById(R.id.zzj_LinearLayout_1_f_7_1);
        linearLayout_2 = getActivity().findViewById(R.id.zzj_LinearLayout_2_f_7_1);
        linearLayout_3 = getActivity().findViewById(R.id.zzj_LinearLayout_3_f_7_1);
        linearLayout_4 = getActivity().findViewById(R.id.zzj_LinearLayout_4_f_7_1);
        linearLayout_1.setOnClickListener(this);
        linearLayout_2.setOnClickListener(this);
        linearLayout_3.setOnClickListener(this);
        linearLayout_4.setOnClickListener(this);
        xianshi();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zzj_LinearLayout_1_f_7_1:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final AlertDialog dialog = builder.create();
                View view = View.inflate(getContext(), R.layout.zzj_fragment_45_alertdialog, null);
                dialog.setView(view);
                dialog.show();
                final EditText editText = view.findViewById(R.id.zzj_dialog_editView);
                Button button_ok = view.findViewById(R.id.zzj_dialog_ok);
                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String jin_E = editText.getText().toString().trim();
                        if (jin_E.equals("")) {
                            Toast.makeText(getContext(),"充值金额不能为空",Toast.LENGTH_SHORT).show();
                        }else {
                            if (0 <= Integer.valueOf(jin_E) && Integer.valueOf(jin_E) <= 50) {
                                chongzhi = 1;
                                jin_e = Integer.valueOf(jin_E);
                                HttpRequest.post("SetCarAccountRecharge", "{\"CarId\":1,\"Money\":" + jin_E + ", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        db = mySQLiteOpenHelper.getWritableDatabase();
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                        Date date = new Date(System.currentTimeMillis());
                                        ContentValues values = new ContentValues();
                                        values.put("chehao",1);
                                        values.put("jine",jin_e);
                                        values.put("shijian",format.format(date));
                                        db.insert("chongzhidejilu", null, values);
                                        values.clear();
                                        db.close();
                                        xianshi();
                                        dialog.dismiss();
                                    }
                                },null);
                            }else {
                                Toast.makeText(getContext(),"充值金额在 0-50 之间",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                Button button_close = view.findViewById(R.id.zzj_dialog_close);
                button_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.zzj_LinearLayout_2_f_7_1:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                final AlertDialog dialog2 = builder2.create();
                View view2 = View.inflate(getContext(), R.layout.zzj_fragment_45_alertdialog, null);
                dialog2.setView(view2);
                dialog2.show();
                final EditText editText2 = view2.findViewById(R.id.zzj_dialog_editView);
                Button button_ok2 = view2.findViewById(R.id.zzj_dialog_ok);
                button_ok2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String jin_E = editText2.getText().toString().trim();
                        if (jin_E.equals("")) {
                            Toast.makeText(getContext(),"充值金额不能为空",Toast.LENGTH_SHORT).show();
                        }else {
                            if (0 <= Integer.valueOf(jin_E) && Integer.valueOf(jin_E) <= 50) {
                                chongzhi = 2;
                                jin_e = Integer.valueOf(jin_E);
                                HttpRequest.post("SetCarAccountRecharge", "{\"CarId\":2,\"Money\":" + jin_E + ", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        db = mySQLiteOpenHelper.getWritableDatabase();
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                        Date date = new Date(System.currentTimeMillis());
                                        ContentValues values = new ContentValues();
                                        values.put("chehao",2);
                                        values.put("jine",jin_e);
                                        values.put("shijian",format.format(date));
                                        db.insert("chongzhidejilu", null, values);
                                        values.clear();
                                        db.close();
                                        xianshi();
                                        dialog2.dismiss();
                                    }
                                },null);
                            }else {
                                Toast.makeText(getContext(),"充值金额在 0-50 之间",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                Button button_close2 = view2.findViewById(R.id.zzj_dialog_close);
                button_close2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                break;
            case R.id.zzj_LinearLayout_3_f_7_1:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(getContext());
                final AlertDialog dialog3 = builder3.create();
                View view3 = View.inflate(getContext(), R.layout.zzj_fragment_45_alertdialog, null);
                dialog3.setView(view3);
                dialog3.show();
                final EditText editText3 = view3.findViewById(R.id.zzj_dialog_editView);
                Button button_ok3 = view3.findViewById(R.id.zzj_dialog_ok);
                button_ok3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String jin_E = editText3.getText().toString().trim();
                        if (jin_E.equals("")) {
                            Toast.makeText(getContext(),"充值金额不能为空",Toast.LENGTH_SHORT).show();
                        }else {
                            if (0 <= Integer.valueOf(jin_E) && Integer.valueOf(jin_E) <= 50) {
                                chongzhi = 3;
                                jin_e = Integer.valueOf(jin_E);
                                HttpRequest.post("SetCarAccountRecharge", "{\"CarId\":3,\"Money\":" + jin_E + ", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        db = mySQLiteOpenHelper.getWritableDatabase();
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                        Date date = new Date(System.currentTimeMillis());
                                        ContentValues values = new ContentValues();
                                        values.put("chehao",3);
                                        values.put("jine",jin_e);
                                        values.put("shijian",format.format(date));
                                        db.insert("chongzhidejilu", null, values);
                                        values.clear();
                                        db.close();
                                        xianshi();
                                        dialog3.dismiss();
                                    }
                                },null);
                            }else {
                                Toast.makeText(getContext(),"充值金额在 0-50 之间",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                Button button_close3 = view3.findViewById(R.id.zzj_dialog_close);
                button_close3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });
                break;
            case R.id.zzj_LinearLayout_4_f_7_1:
                AlertDialog.Builder builder4 = new AlertDialog.Builder(getContext());
                final AlertDialog dialog4 = builder4.create();
                View view4 = View.inflate(getContext(), R.layout.zzj_fragment_45_alertdialog, null);
                dialog4.setView(view4);
                dialog4.show();
                final EditText editText4 = view4.findViewById(R.id.zzj_dialog_editView);
                Button button_ok4 = view4.findViewById(R.id.zzj_dialog_ok);
                button_ok4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String jin_E = editText4.getText().toString().trim();
                        if (jin_E.equals("")) {
                            Toast.makeText(getContext(),"充值金额不能为空",Toast.LENGTH_SHORT).show();
                        }else {
                            if (0 <= Integer.valueOf(jin_E) && Integer.valueOf(jin_E) <= 50) {
                                chongzhi = 4;
                                jin_e = Integer.valueOf(jin_E);
                                HttpRequest.post("SetCarAccountRecharge", "{\"CarId\":4,\"Money\":" + jin_E + ", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        db = mySQLiteOpenHelper.getWritableDatabase();
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                                        Date date = new Date(System.currentTimeMillis());
                                        ContentValues values = new ContentValues();
                                        values.put("chehao",4);
                                        values.put("jine",jin_e);
                                        values.put("shijian",format.format(date));
                                        db.insert("chongzhidejilu", null, values);
                                        values.clear();
                                        db.close();
                                        xianshi();
                                        dialog4.dismiss();
                                    }
                                },null);
                            }else {
                                Toast.makeText(getContext(),"充值金额在 0-50 之间",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                Button button_close4 = view4.findViewById(R.id.zzj_dialog_close);
                button_close4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog4.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }

    private void xianshi() {
        HttpRequest.post("GetCarAccountBalance", "{\"CarId\":1, \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                int yu_e_1 = 0;
                try {
                    yu_e_1 = jsonObject.getInt("Balance");
                    textView_yuan_1.setText(yu_e_1+"");
                    if (yu_e_1 >= 100) {
                        textView_1.setText("正常");
                        linearLayout_1.setBackgroundColor(Color.parseColor("#00ff00"));
                    }else {
                        textView_1.setText("警告");
                        linearLayout_1.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetCarAccountBalance", "{\"CarId\":2, \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                int yu_e_1 = 0;
                try {
                    yu_e_1 = jsonObject.getInt("Balance");
                    textView_yuan_2.setText(yu_e_1+"");
                    if (yu_e_1 >= 100) {
                        textView_2.setText("正常");
                        linearLayout_2.setBackgroundColor(Color.parseColor("#00ff00"));
                    }else {
                        textView_2.setText("警告");
                        linearLayout_2.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetCarAccountBalance", "{\"CarId\":3, \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                int yu_e_1 = 0;
                try {
                    yu_e_1 = jsonObject.getInt("Balance");
                    textView_yuan_3.setText(yu_e_1+"");
                    if (yu_e_1 >= 100) {
                        textView_3.setText("正常");
                        linearLayout_3.setBackgroundColor(Color.parseColor("#00ff00"));
                    }else {
                        textView_3.setText("警告");
                        linearLayout_3.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetCarAccountBalance", "{\"CarId\":4, \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                int yu_e_1 = 0;
                try {
                    yu_e_1 = jsonObject.getInt("Balance");
                    textView_yuan_4.setText(yu_e_1+"");
                    if (yu_e_1 >= 100) {
                        textView_4.setText("正常");
                        linearLayout_4.setBackgroundColor(Color.parseColor("#00ff00"));
                    }else {
                        textView_4.setText("警告");
                        linearLayout_4.setBackgroundColor(Color.parseColor("#ff0000"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }

}
