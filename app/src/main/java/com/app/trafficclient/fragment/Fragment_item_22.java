package com.app.trafficclient.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment_item_22 extends Fragment implements View.OnClickListener {

    private TextView textView_piliang,textView_jilu,textView_1,textView_2,textView_3,textView_4;
    private CheckBox checkBox_1,checkBox_2,checkBox_3,checkBox_4;
    private Button button_1,button_2,button_3,button_4;

    private ProgressDialog progressDialog;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private View view;

    private String q1 = "",q2 = "",q3 = "",q4 = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_22, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("提示");
        progressDialog.setMessage("获取数据中...");
        progressDialog.show();
        chaxunData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zzj_fragment22_textView_piliang_chongzhi:
                builder = new AlertDialog.Builder(getContext());
                dialog = builder.create();
                view = View.inflate(getContext(), R.layout.zzj_fragment22_dialog, null);
                dialog.setView(view);
                dialog.show();
                TextView textView5 = view.findViewById(R.id.zzj_fragment22_dialog_text_chehao);
                textView5.setText("车牌号：   " + q1 + "，" + q2 + "，" + q3 + "，" + q4);
                final EditText editText5 = view.findViewById(R.id.zzj_fragment22_dialog_edit_jine);
                Button button_1_5 = view.findViewById(R.id.zzj_fragment22_dialog_btn_chongzhi);
                Button button_2_5 = view.findViewById(R.id.zzj_fragment22_dialog_btn_quxiao);
                button_1_5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!(editText5.getText().toString().equals(""))) {
                            progressDialog.show();
                            if (!(q1.equals(""))) {
                                addData(Integer.valueOf(q1), Integer.valueOf(editText5.getText().toString()));
                            }
                            if (!(q2.equals(""))) {
                                addData(Integer.valueOf(q2), Integer.valueOf(editText5.getText().toString()));
                            }
                            if (!(q3.equals(""))) {
                                addData(Integer.valueOf(q3), Integer.valueOf(editText5.getText().toString()));
                            }
                            if (!(q4.equals(""))) {
                                addData(Integer.valueOf(q4), Integer.valueOf(editText5.getText().toString()));
                            }
                            dialog.dismiss();
                        }
                    }
                });
                button_2_5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.zzj_fragment22_textView_chongzhi_jilu:
                break;
            case R.id.zzj_fragment22_Button_1:
                builder = new AlertDialog.Builder(getContext());
                dialog = builder.create();
                view = View.inflate(getContext(), R.layout.zzj_fragment22_dialog, null);
                dialog.setView(view);
                dialog.show();
                TextView textView = view.findViewById(R.id.zzj_fragment22_dialog_text_chehao);
                textView.setText("车牌号：   1");
                final EditText editText = view.findViewById(R.id.zzj_fragment22_dialog_edit_jine);
                Button button_1 = view.findViewById(R.id.zzj_fragment22_dialog_btn_chongzhi);
                Button button_2 = view.findViewById(R.id.zzj_fragment22_dialog_btn_quxiao);
                button_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!(editText.getText().toString().equals(""))) {
                            progressDialog.show();
                            addData(1, Integer.valueOf(editText.getText().toString()));
                            dialog.dismiss();
                        }
                    }
                });
                button_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.zzj_fragment22_Button_2:
                builder = new AlertDialog.Builder(getContext());
                dialog = builder.create();
                view = View.inflate(getContext(), R.layout.zzj_fragment22_dialog, null);
                dialog.setView(view);
                dialog.show();
                TextView textView2 = view.findViewById(R.id.zzj_fragment22_dialog_text_chehao);
                textView2.setText("车牌号：   2");
                final EditText editText2 = view.findViewById(R.id.zzj_fragment22_dialog_edit_jine);
                Button button_1_2 = view.findViewById(R.id.zzj_fragment22_dialog_btn_chongzhi);
                Button button_2_2 = view.findViewById(R.id.zzj_fragment22_dialog_btn_quxiao);
                button_1_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!(editText2.getText().toString().equals(""))) {
                            progressDialog.show();
                            addData(2, Integer.valueOf(editText2.getText().toString()));
                            dialog.dismiss();
                        }
                    }
                });
                button_2_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.zzj_fragment22_Button_3:
                builder = new AlertDialog.Builder(getContext());
                dialog = builder.create();
                view = View.inflate(getContext(), R.layout.zzj_fragment22_dialog, null);
                dialog.setView(view);
                dialog.show();
                TextView textView3 = view.findViewById(R.id.zzj_fragment22_dialog_text_chehao);
                textView3.setText("车牌号：   3");
                final EditText editText3 = view.findViewById(R.id.zzj_fragment22_dialog_edit_jine);
                Button button_1_3 = view.findViewById(R.id.zzj_fragment22_dialog_btn_chongzhi);
                Button button_2_3 = view.findViewById(R.id.zzj_fragment22_dialog_btn_quxiao);
                button_1_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!(editText3.getText().toString().equals(""))) {
                            progressDialog.show();
                            addData(3, Integer.valueOf(editText3.getText().toString()));
                            dialog.dismiss();
                        }
                    }
                });
                button_2_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.zzj_fragment22_Button_4:
                builder = new AlertDialog.Builder(getContext());
                dialog = builder.create();
                view = View.inflate(getContext(), R.layout.zzj_fragment22_dialog, null);
                dialog.setView(view);
                dialog.show();
                TextView textView4 = view.findViewById(R.id.zzj_fragment22_dialog_text_chehao);
                textView4.setText("车牌号：   4");
                final EditText editText4 = view.findViewById(R.id.zzj_fragment22_dialog_edit_jine);
                Button button_1_4 = view.findViewById(R.id.zzj_fragment22_dialog_btn_chongzhi);
                Button button_2_4 = view.findViewById(R.id.zzj_fragment22_dialog_btn_quxiao);
                button_1_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!(editText4.getText().toString().equals(""))) {
                            progressDialog.show();
                            addData(4, Integer.valueOf(editText4.getText().toString()));
                            dialog.dismiss();
                        }
                    }
                });
                button_2_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }

    private int addData(int i,int money) {
        HttpRequest.post("SetCarAccountRecharge", "{\"CarId\":"+i+",\"Money\":"+money+", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(MyApplication.appContext.getApplicationContext(),"充值成功",Toast.LENGTH_SHORT).show();
                chaxunData();
            }
        },null);
        return 0;
    }


    private void chaxunData() {
        HttpRequest.post("GetCarAccountBalance", "{\"CarId\":1, \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    textView_1.setText(jsonObject.getInt("Balance")+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetCarAccountBalance", "{\"CarId\":2, \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    textView_2.setText(jsonObject.getInt("Balance")+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetCarAccountBalance", "{\"CarId\":3, \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    textView_3.setText(jsonObject.getInt("Balance")+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        HttpRequest.post("GetCarAccountBalance", "{\"CarId\":4, \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    textView_4.setText(jsonObject.getInt("Balance")+"");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
        progressDialog.dismiss();
    }

    private void initView() {

        textView_piliang = getActivity().findViewById(R.id.zzj_fragment22_textView_piliang_chongzhi);
        textView_jilu = getActivity().findViewById(R.id.zzj_fragment22_textView_chongzhi_jilu);
        textView_piliang.setOnClickListener(this);
        textView_jilu.setOnClickListener(this);

        textView_1 = getActivity().findViewById(R.id.zzj_fragment22_textView_yu_e_1);
        textView_2 = getActivity().findViewById(R.id.zzj_fragment22_textView_yu_e_2);
        textView_3 = getActivity().findViewById(R.id.zzj_fragment22_textView_yu_e_3);
        textView_4 = getActivity().findViewById(R.id.zzj_fragment22_textView_yu_e_4);

        checkBox_1 = getActivity().findViewById(R.id.zzj_fragment22_CheckBox_1);
        checkBox_2 = getActivity().findViewById(R.id.zzj_fragment22_CheckBox_2);
        checkBox_3 = getActivity().findViewById(R.id.zzj_fragment22_CheckBox_3);
        checkBox_4 = getActivity().findViewById(R.id.zzj_fragment22_CheckBox_4);

        button_1 = getActivity().findViewById(R.id.zzj_fragment22_Button_1);
        button_2 = getActivity().findViewById(R.id.zzj_fragment22_Button_2);
        button_3 = getActivity().findViewById(R.id.zzj_fragment22_Button_3);
        button_4 = getActivity().findViewById(R.id.zzj_fragment22_Button_4);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);

        checkBox_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean is) {
                if (is) {
                    q1 = "1";
                }else {
                    q1 = "";
                }
            }
        });
        checkBox_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean is) {
                if (is) {
                    q2 = "2";
                }else {
                    q2 = "";
                }
            }
        });
        checkBox_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean is) {
                if (is) {
                    q3 = "3";
                }else {
                    q3 = "";
                }
            }
        });
        checkBox_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean is) {
                if (is) {
                    q4 = "4";
                }else {
                    q4 = "";
                }
            }
        });

    }

}
