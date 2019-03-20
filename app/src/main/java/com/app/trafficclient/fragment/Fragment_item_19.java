package com.app.trafficclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.trafficclient.R;

public class Fragment_item_19 extends Fragment implements View.OnClickListener {
    private FrameLayout yws_replace_layout;
    TextView tv_yuan1,tv_yuan2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_19, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        yws_replace_layout=getActivity().findViewById(R.id.yws_relpace_item19);
        tv_yuan1=getActivity().findViewById(R.id.yws_yuan1);
        tv_yuan2=getActivity().findViewById(R.id.yws_yuan2);
        tv_yuan1.setOnClickListener(this);
        tv_yuan2.setOnClickListener(this);
        replace(new Yws_Fragment_item19_1());
        tv_yuan1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_2));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yws_yuan1:
                tv_yuan1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_2));
                tv_yuan2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                replace(new Yws_Fragment_item19_1());
                break;
            case  R.id.yws_yuan2:
                tv_yuan2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_2));
                tv_yuan1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zl_frag6_cboder_2));
                replace(new Yws_fragment_item19_2());
                break;
                default:
                    break;
        }
    }
    private void replace(Fragment fragment){
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.yws_relpace_item19,fragment);
        fragmentTransaction.commit();

    }
}
