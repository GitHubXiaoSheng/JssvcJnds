package com.app.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.trafficclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_40 extends Fragment implements View.OnClickListener {
    private View view;
    private TextView nameTv;
    private TextView cardIdTv;
    private TextView balanceTv;
    private EditText amountEt;
    private Button rechargeBtn;
    private Button cancelBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_layout_40, container, false);
        initView();
        return view;
    }

    private void initView(){
        nameTv = (TextView) view.findViewById(R.id.frag40_username_tv);
        cardIdTv = (TextView) view.findViewById(R.id.frag40_cardnum_tv);
        balanceTv = (TextView) view.findViewById(R.id.frag40_balance_tv);
        amountEt = (EditText) view.findViewById(R.id.frag40_amount_et);
        rechargeBtn = (Button) view.findViewById(R.id.frag40_recharge_btn);
        cancelBtn = (Button) view.findViewById(R.id.frag40_cancel_btn);
        rechargeBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frag40_recharge_btn:
                break;
            case R.id.frag40_cancel_btn:
                break;
            default:
                break;
        }
    }
}
