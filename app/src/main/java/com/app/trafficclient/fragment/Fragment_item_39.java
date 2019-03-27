package com.app.trafficclient.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.trafficclient.R;

public class Fragment_item_39 extends Fragment implements View.OnClickListener {
private TextView scienceTv,educationTv,sportTv;
private TextView color1Tv,color2TV,color3TV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layou_item_39, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        scienceTv=getActivity().findViewById(R.id.science_tv);
        educationTv=getActivity().findViewById(R.id.eduction_tv);
        sportTv=getActivity().findViewById(R.id.sport_tv);
        educationTv.setOnClickListener(this);
        sportTv.setOnClickListener(this);
        scienceTv.setOnClickListener(this);
        color1Tv=getActivity().findViewById(R.id.color1_tv);
        color2TV=getActivity().findViewById(R.id.color2_tv);
        color3TV=getActivity().findViewById(R.id.color3_tv);
        color1Tv.setBackgroundColor(Color.YELLOW);
        replace(new Yws_Science_Fragment());

    }
    private void replace(Fragment fragment){
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.replace_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.science_tv:
                replace(new Yws_Science_Fragment());
                color1Tv.setBackgroundColor(Color.YELLOW);
                color2TV.setBackgroundColor(Color.WHITE);
                color3TV.setBackgroundColor(Color.WHITE);
                break;
            case R.id.eduction_tv:
                replace(new Yws_edu_Fragment());
                color2TV.setBackgroundColor(Color.YELLOW);
                color1Tv.setBackgroundColor(Color.WHITE);
                color3TV.setBackgroundColor(Color.WHITE);
                break;
            case R.id.sport_tv:
                replace(new Yws_Sport_Fragment());
                color3TV.setBackgroundColor(Color.YELLOW);
                color2TV.setBackgroundColor(Color.WHITE);
                color1Tv.setBackgroundColor(Color.WHITE);
                break;
                default:
                    break;

        }
    }
}
