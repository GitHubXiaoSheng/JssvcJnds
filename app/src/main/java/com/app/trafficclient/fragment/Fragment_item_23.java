package com.app.trafficclient.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;

public class Fragment_item_23 extends Fragment {
    private TextView tv_show_fazhi;
    private EditText edt_config_fazhi;
    private Button btn_config;
    private String fazhi,save_yue;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_item_23, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        tv_show_fazhi=getActivity().findViewById(R.id.tv_show_yue);
        edt_config_fazhi=getActivity().findViewById(R.id.edt_config_fazhi);
        btn_config=getActivity().findViewById(R.id.btn_config);


        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        save_yue=sharedPreferences.getString("yue","");

        btn_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fazhi=edt_config_fazhi.getText().toString().trim();
                editor=sharedPreferences.edit();
                editor.putString("yue",fazhi);
                editor.apply();
                tv_show_fazhi.setText(fazhi+"元");
            }
        });
    }

}
