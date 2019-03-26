package com.app.trafficclient.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.MySQLiteOpenHelper;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Fragment_item_34 extends Fragment implements View.OnClickListener {

    private LinearLayout linearLayout_1,linearLayout_2,linearLayout_3;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layou_item_34, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(), MySQLiteOpenHelper.DBNAME, null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zzj_fragment34_linearLayout_1:
                chongzhi();
                break;
            case R.id.zzj_fragment34_linearLayout_2:
                chaxun();
                break;
            case R.id.zzj_fragment34_linearLayout_3:
                jilu();
                break;
            default:
                break;
        }
    }

    private void chongzhi() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getContext(), R.layout.zzj_fragment34_dialog_1, null);
        dialog.setView(view);
        dialog.show();
        ImageView imageView = view.findViewById(R.id.zzj_fragment34_dialog1_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        final EditText editText_chehao = view.findViewById(R.id.zzj_fragment34_dialog1_chehaoEditView);
        final EditText editText_jin_e = view.findViewById(R.id.zzj_fragment34_dialog1_chongzhiEditView);
        Button button = view.findViewById(R.id.zzj_fragment34_dialog1_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("我没", editText_chehao.getText().toString() + " ---- " + editText_jin_e.getText().toString());
                if (editText_chehao.getText().toString().isEmpty() || editText_jin_e.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(),"充值的车号或金额不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    final int chehao = Integer.valueOf(editText_chehao.getText().toString());
                    final int jin_e = Integer.valueOf(editText_jin_e.getText().toString());
                    HttpRequest.post("SetCarAccountRecharge", "{\"CarId\":" + chehao + ",\"Money\":" + jin_e + ", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                if (jsonObject.getString("ERRMSG").equals("成功")) {
                                    Toast.makeText(MyApplication.appContext.getApplicationContext(), "充值成功", Toast.LENGTH_SHORT).show();
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date date = new Date(System.currentTimeMillis());
                                    String time = format.format(date);
                                    ContentValues values = new ContentValues();
                                    values.put("chehao", chehao);
                                    values.put("chepai", "辽A1000" + chehao);
                                    values.put("jine", jin_e);
                                    values.put("time", time);
                                    db.insert("chongzhijilu", null, values);
                                    dialog.dismiss();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(MyApplication.appContext.getApplicationContext(), "充值失败，没有该车牌号", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void chaxun() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getContext(), R.layout.zzj_fragment34_dialog_2, null);
        dialog.setView(view);
        dialog.show();
        ImageView imageView = view.findViewById(R.id.zzj_fragment34_dialog2_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        final EditText editText = view.findViewById(R.id.zzj_fragment34_dialog2_chehaoEditView);
        final TextView textView = view.findViewById(R.id.zzj_fragment34_dialog2_yu_e);
        Button button = view.findViewById(R.id.zzj_fragment34_dialog2_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(),"查询内容不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    int jin_e = Integer.valueOf(editText.getText().toString());
                    HttpRequest.post("GetCarAccountBalance", "{\"CarId\":" + jin_e + ", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            Toast.makeText(MyApplication.appContext.getApplicationContext(), "查询成功", Toast.LENGTH_SHORT).show();
                            try {
                                textView.setText(jsonObject.getString("Balance"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },null);
                }
            }
        });
    }

    private void jilu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getContext(), R.layout.zzj_fragment34_dialog_3, null);
        dialog.setView(view);
        dialog.show();
        ImageView imageView = view.findViewById(R.id.zzj_fragment34_dialog3_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView textView_heji = view.findViewById(R.id.zzj_fragment34_dialog_3_chongzhiheji);
        LinearLayout linearLayout = view.findViewById(R.id.zzj_fragment34_dialog_3_LinearLayout);
        int count = 0;
        Cursor cursor = db.rawQuery("select * from chongzhijilu", null);
        if (cursor.moveToFirst()) {
            do {
                View view_jilu = LayoutInflater.from(getContext()).inflate(R.layout.zzj_fragment34_dialog_3_item, null);
                TextView textView_1 = view_jilu.findViewById(R.id.zzj_fragment34_dialog_3_item_1),
                        textView_2 = view_jilu.findViewById(R.id.zzj_fragment34_dialog_3_item_2),
                        textView_3 = view_jilu.findViewById(R.id.zzj_fragment34_dialog_3_item_3),
                        textView_4 = view_jilu.findViewById(R.id.zzj_fragment34_dialog_3_item_4);
                textView_1.setText(cursor.getString(cursor.getColumnIndex("chehao")));
                textView_2.setText(cursor.getString(cursor.getColumnIndex("chepai")));
                textView_3.setText(cursor.getString(cursor.getColumnIndex("jine")));
                textView_4.setText(cursor.getString(cursor.getColumnIndex("time")));
                linearLayout.addView(view_jilu);
                count += cursor.getInt(cursor.getColumnIndex("jine"));
            } while (cursor.moveToNext());
        }
        textView_heji.setText("充值合计：" + count);
    }

    private void init() {
        linearLayout_1 = getActivity().findViewById(R.id.zzj_fragment34_linearLayout_1);
        linearLayout_2 = getActivity().findViewById(R.id.zzj_fragment34_linearLayout_2);
        linearLayout_3 = getActivity().findViewById(R.id.zzj_fragment34_linearLayout_3);
        linearLayout_1.setOnClickListener(this);
        linearLayout_2.setOnClickListener(this);
        linearLayout_3.setOnClickListener(this);
    }
}
