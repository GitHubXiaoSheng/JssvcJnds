package com.app.trafficclient.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.trafficclient.R;

public class Fragment_item_7 extends Fragment {

    private Switch aSwitch;

    private TextView textView_isOFF;
    private EditText editText_1,editText_2,editText_3,editText_4,editText_5,editText_6;

    private Button button;

    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_7, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    editor = getActivity().getSharedPreferences("yuzhi_isOFF", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("yuzhi", true);
                    editor.apply();
                    button.setEnabled(true);
                    textView_isOFF.setText("开");
                }else {
                    editor = getActivity().getSharedPreferences("yuzhi_isOFF", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("yuzhi", false);
                    editor.apply();
                    button.setEnabled(false);
                    textView_isOFF.setText("关");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText_1.getText().toString().isEmpty() || editText_2.getText().toString().isEmpty() || editText_3.getText().toString().isEmpty() || editText_4.getText().toString().isEmpty() || editText_5.getText().toString().isEmpty() || editText_6.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"阈值不能设为空",Toast.LENGTH_SHORT).show();
                }else {
                    addData_SharedPreferences();
                }
            }
        });

    }

    private void addData_SharedPreferences() {
        editor = getActivity().getSharedPreferences("yuzhi",Context.MODE_PRIVATE).edit();
        editor.putInt("wendu", Integer.valueOf(editText_1.getText().toString()));
        editor.putInt("shidu", Integer.valueOf(editText_2.getText().toString()));
        editor.putInt("guangzhao", Integer.valueOf(editText_3.getText().toString()));
        editor.putInt("co2", Integer.valueOf(editText_4.getText().toString()));
        editor.putInt("pm25", Integer.valueOf(editText_5.getText().toString()));
        editor.putInt("daolu", Integer.valueOf(editText_6.getText().toString()));
        editor.apply();
    }

    private void initView(){
        aSwitch = getActivity().findViewById(R.id.zzj_switch_yuzhi);
        textView_isOFF = getActivity().findViewById(R.id.zzj_textView_yuzhi_xianshi);
        editText_1 = getActivity().findViewById(R.id.zzj_editView_wendu_yuzhi);
        editText_2 = getActivity().findViewById(R.id.zzj_editView_shidu_yuzhi);
        editText_3 = getActivity().findViewById(R.id.zzj_editView_guangzhao_yuzhi);
        editText_4 = getActivity().findViewById(R.id.zzj_editView_co2_yuzhi);
        editText_5 = getActivity().findViewById(R.id.zzj_editView_pm25_yuzhi);
        editText_6 = getActivity().findViewById(R.id.zzj_editView_daolu_yuzhi);
        button = getActivity().findViewById(R.id.zzj_button_yuzhibaocun);
    }
}
