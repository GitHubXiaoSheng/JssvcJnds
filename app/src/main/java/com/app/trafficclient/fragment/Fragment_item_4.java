package com.app.trafficclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.trafficclient.R;

public class Fragment_item_4 extends Fragment  implements View.OnClickListener{

    private TextView tv_video,tv_picture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_video=getActivity().findViewById(R.id.tv_video);
        tv_picture=getActivity().findViewById(R.id.tv_picture);
        tv_video.setOnClickListener(this);
        tv_picture.setOnClickListener(this);
        tv_video.setBackgroundDrawable(getResources().getDrawable(R.drawable.yws_blackground_1));
        replace(new Yws_Fragment_activity4_video());
    }

    public void replace(Fragment fragment){
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.replace_linearlayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_picture:
                replace(new Yws_Fragment_activity4_picture());
                tv_picture.setBackgroundDrawable(getResources().getDrawable(R.drawable.yws_blackground_1));
                tv_video.setBackgroundDrawable(getResources().getDrawable(R.drawable.biankuang));
                break;
            case R.id.tv_video:
                replace(new Yws_Fragment_activity4_video());
                tv_picture.setBackgroundDrawable(getResources().getDrawable(R.drawable.biankuang));
                tv_video.setBackgroundDrawable(getResources().getDrawable(R.drawable.yws_blackground_1));
                break;
            default:
                break;
        }
    }
}
