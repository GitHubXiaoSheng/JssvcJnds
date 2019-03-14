package com.app.trafficclient.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.trafficclient.R;

public class Yws_Fragment_activity4_picture extends Fragment implements View.OnClickListener {
    private ImageView img1,img2,img3,img4;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yws__fragment_activity4_picture, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        img1=getActivity().findViewById(R.id.img1);
        img2=getActivity().findViewById(R.id.img2);
        img3=getActivity().findViewById(R.id.img3);
        img4=getActivity().findViewById(R.id.img4);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img1:
                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.GONE);
                img3.setVisibility(View.GONE);
                img4.setVisibility(View.GONE);
                break;
            case R.id.img2:
                break;
            case R.id.img3:
                break;
            case R.id.img4:
                break;
                default:
                    break;

        }

    }
}
