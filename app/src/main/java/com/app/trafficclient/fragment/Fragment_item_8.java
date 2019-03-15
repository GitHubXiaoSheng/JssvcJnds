package com.app.trafficclient.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.app.trafficclient.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_8 extends Fragment {
    private TextView tv_data,tv_chuxing;
    private Switch switch_car_1,switch_car_2,switch_car_3;
    private NumberPicker np1,np2,np3;
    private Calendar c;
    private int maxday;
    private Context context;
    private int day;
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_layout_8, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_data=getActivity().findViewById(R.id.tv_data);
        tv_chuxing=getActivity().findViewById(R.id.tv_chuxingche);
        switch_car_1=getActivity().findViewById(R.id.tc_car1);
        switch_car_2=getActivity().findViewById(R.id.tc_car2);
        switch_car_3=getActivity().findViewById(R.id.tc_car3);
        imageView=getActivity().findViewById(R.id.anim_imageview);
        animationDrawable=(AnimationDrawable)imageView.getBackground();
        animationDrawable.start();




        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date(System.currentTimeMillis());
        tv_data.setText( simpleDateFormat.format(date));
        c=Calendar.getInstance();
        if (day%2==1){
            tv_chuxing.setText("单号出行车辆：1，3");
            switch_car_1.setChecked(true);
            switch_car_1.setEnabled(true);
            switch_car_2.setEnabled(false);
            switch_car_2.setChecked(false);
            switch_car_3.setChecked(true);
            switch_car_3.setEnabled(true);
            switch_car_1.setText("开");
            switch_car_2.setText("关");
            switch_car_3.setText("开");


        }else {
            tv_chuxing.setText("双号出行车辆：2");
            switch_car_1.setChecked(false);
            switch_car_1.setEnabled(false);
            switch_car_2.setEnabled(true);
            switch_car_2.setChecked(true);
            switch_car_3.setChecked(false);
            switch_car_3.setEnabled(false);
            switch_car_1.setText("关");
            switch_car_2.setText("开");
            switch_car_3.setText("关");


        }


        tv_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
        switch_car_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch_car_1.setText("开");
                }else {
                    switch_car_1.setText("关");
                }
            }
        });
        switch_car_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch_car_2.setText("开");
                }else {
                    switch_car_2.setText("关");
                }
            }
        });
        switch_car_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    switch_car_3.setText("开");
                }else {
                    switch_car_3.setText("关");
                }
            }
        });
    }

    private void showdialog(){
        View view=getLayoutInflater().inflate(R.layout.data_numpicker,null);
        np1=view.findViewById(R.id.numberpicker_1);
        np2=view.findViewById(R.id.numberpicker_2);
        np3=view.findViewById(R.id.numberpicker_3);
        c=Calendar.getInstance();


        int year=c.get(Calendar.YEAR);
        int mouth=c.get(Calendar.MONTH)+1;
        day=c.get(Calendar.DAY_OF_MONTH);


        np1.setMaxValue(2999);
        np1.setMinValue(1900);
        np1.setValue(year);


        np2.setValue(mouth);
        np2.setMinValue(1);
        np2.setMaxValue(12);


        np3.setValue(day);
        np3.setMaxValue(31);
        np3.setMinValue(1);


        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                if(i1%4==0){
                    maxday=29;
                }else {
                    maxday=28;
                }
            }
        });

        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                switch (i1) {
                    case 2:
                        if (np1.getValue() % 4 == 0) {
                            maxday=29;
                        }else {
                            maxday=28;
                        }
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        maxday=31;
                        break;
                    default:
                        maxday=30;
                        break;
                }
                np3.setMaxValue(maxday);
            }
        });
        new AlertDialog.Builder(getContext()).setTitle("请选择日期")
                .setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int years=np1.getValue();
                int mouth=np2.getValue();
                int day=np3.getValue();
                if (day%2==1){
                    tv_data.setText(years+"年"+mouth+"月"+day);
                    tv_chuxing.setText("单号出行车辆：1，3");
                    switch_car_1.setChecked(true);
                    switch_car_1.setEnabled(true);
                    switch_car_2.setEnabled(false);
                    switch_car_2.setChecked(false);
                    switch_car_3.setChecked(true);
                    switch_car_3.setEnabled(true);


                }else {
                    tv_data.setText(years+"年"+mouth+"月"+day);
                    tv_chuxing.setText("双号出行车辆：2");
                    switch_car_1.setChecked(false);
                    switch_car_1.setEnabled(false);
                    switch_car_2.setEnabled(true);
                    switch_car_2.setChecked(true);
                    switch_car_3.setChecked(false);
                    switch_car_3.setEnabled(false);


                }


                dialogInterface.dismiss();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();

    }

}
