package com.app.trafficclient.fragment;

import android.graphics.Typeface;
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

public class Fragment_item_45 extends Fragment implements View.OnClickListener {

    private TextView textView_1,textView_2,textView_3,textView_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_45, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView_user = getActivity().findViewById(R.id.zzj_User_textView);
        textView_1 = getActivity().findViewById(R.id.zzj_wodeyu_e_textView_f7);
        textView_2 = getActivity().findViewById(R.id.zzj_yuanchengkongzhi_textView_f7);
        textView_3 = getActivity().findViewById(R.id.zzj_chongzhijilu_textView_f7);
        textView_1.setOnClickListener(this);
        textView_2.setOnClickListener(this);
        textView_3.setOnClickListener(this);
        textView_1.setTypeface(textView_1.getTypeface(),Typeface.BOLD);
        textView_2.setTypeface(textView_2.getTypeface(),Typeface.ITALIC);
        textView_3.setTypeface(textView_3.getTypeface(),Typeface.ITALIC);
        replaceFragment(new Zzj_Fragment_45_1());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zzj_wodeyu_e_textView_f7:
                textView_1.setTypeface(textView_1.getTypeface(),Typeface.BOLD);
                textView_2.setTypeface(textView_2.getTypeface(), Typeface.ITALIC);
                textView_3.setTypeface(textView_3.getTypeface(),Typeface.ITALIC);
                replaceFragment(new Zzj_Fragment_45_1());
                break;
            case R.id.zzj_yuanchengkongzhi_textView_f7:
                textView_1.setTypeface(textView_1.getTypeface(),Typeface.ITALIC);
                textView_2.setTypeface(textView_2.getTypeface(),Typeface.BOLD);
                textView_3.setTypeface(textView_3.getTypeface(),Typeface.ITALIC);
                replaceFragment(new Zzj_Fragment_45_2());
                break;
            case R.id.zzj_chongzhijilu_textView_f7:
                textView_1.setTypeface(textView_1.getTypeface(),Typeface.ITALIC);
                textView_2.setTypeface(textView_2.getTypeface(),Typeface.ITALIC);
                textView_3.setTypeface(textView_3.getTypeface(),Typeface.BOLD);
                replaceFragment(new Zzj_Fragment_45_3());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment7_Fragment, fragment);
        transaction.commit();
    }

}
