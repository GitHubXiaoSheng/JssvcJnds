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

public class Fragment_item_18 extends Fragment {

    private TextView textView_1,textView_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_18, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        replaceFragment(new Zzj_Fragment_item_18_1());
        textView_1 = getActivity().findViewById(R.id.zzj_fragment18_MyXiaoxi);
        textView_2 = getActivity().findViewById(R.id.zzj_fragment18_XiaoxiFenxi);
        textView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Zzj_Fragment_item_18_1());
                textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_3));
                textView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_1));
            }
        });
        textView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new Zzj_Fragment_item_18_2());
                textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_1));
                textView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_background_3));
            }
        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.zzj_fragment18_fragment, fragment);
        transaction.commit();
    }

}
