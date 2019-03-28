package com.app.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONObject;

public class Zzj_Fragment_45_2 extends Fragment implements View.OnClickListener {

    private TextView textView_1,textView_2,textView_3,textView_4,textView_5,textView_6,textView_7,textView_8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zzj_layout_fragment_45_2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        createView();
        textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
        textView_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
        textView_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
        textView_7.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));

        kongzhiStart(1, "Start");
        kongzhiStart(2, "Start");
        kongzhiStart(3, "Start");
        kongzhiStart(4, "Start");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zzj_f_7_2_1:
                kongzhiStart(1, "Start");
                textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
                textView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_baise));
                break;
            case R.id.zzj_f_7_2_2:
                kongzhiStart(1, "Stop");
                textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_baise));
                textView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
                break;
            case R.id.zzj_f_7_2_3:
                kongzhiStart(2, "Start");
                textView_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
                textView_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_baise));
                break;
            case R.id.zzj_f_7_2_4:
                kongzhiStart(2, "Stop");
                textView_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_baise));
                textView_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
                break;
            case R.id.zzj_f_7_2_5:
                kongzhiStart(3, "Start");
                textView_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
                textView_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_baise));
                break;
            case R.id.zzj_f_7_2_6:
                kongzhiStart(3, "Stop");
                textView_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_baise));
                textView_6.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
                break;
            case R.id.zzj_f_7_2_7:
                kongzhiStart(4, "Start");
                textView_7.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
                textView_8.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_baise));
                break;
            case R.id.zzj_f_7_2_8:
                kongzhiStart(4, "Stop");
                textView_7.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_baise));
                textView_8.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_fragment_45_2_lvse));
                break;
            default:
                break;
        }
    }

    private void kongzhiStart(int i, String dongzuo) {
        HttpRequest.post("SetCarMove", "{\"CarId\":" + i + ", \"CarAction\":\""+dongzuo+"\", \"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        },null);
    }

    private void createView() {
        textView_1 = getActivity().findViewById(R.id.zzj_f_7_2_1);
        textView_2 = getActivity().findViewById(R.id.zzj_f_7_2_2);
        textView_3 = getActivity().findViewById(R.id.zzj_f_7_2_3);
        textView_4 = getActivity().findViewById(R.id.zzj_f_7_2_4);
        textView_5 = getActivity().findViewById(R.id.zzj_f_7_2_5);
        textView_6 = getActivity().findViewById(R.id.zzj_f_7_2_6);
        textView_7 = getActivity().findViewById(R.id.zzj_f_7_2_7);
        textView_8 = getActivity().findViewById(R.id.zzj_f_7_2_8);
        textView_1.setOnClickListener(this);
        textView_2.setOnClickListener(this);
        textView_3.setOnClickListener(this);
        textView_4.setOnClickListener(this);
        textView_5.setOnClickListener(this);
        textView_6.setOnClickListener(this);
        textView_7.setOnClickListener(this);
        textView_8.setOnClickListener(this);
    }
}
