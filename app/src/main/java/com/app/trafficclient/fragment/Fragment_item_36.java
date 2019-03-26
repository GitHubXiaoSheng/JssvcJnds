package com.app.trafficclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.trafficclient.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fragment_item_36 extends Fragment {

private TextView showtime,tv_today,tv_today1,tv_today2,tv_today3,tv_today4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layou_item_36, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
      showtime=getActivity().findViewById(R.id.tv_time);
      tv_today=getActivity().findViewById(R.id.tv_today);
      tv_today1=getActivity().findViewById(R.id.tv_tody1);
        tv_today2=getActivity().findViewById(R.id.tv_tody2);
        tv_today3=getActivity().findViewById(R.id.tv_tody3);
        tv_today4=getActivity().findViewById(R.id.tv_tody4);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date(System.currentTimeMillis());
        simpleDateFormat.format(date);
        Calendar calendar=Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
        showtime.setText(simpleDateFormat.format(date)+   "周"+week);
        tv_today.setText(day+"日"+"（今天）");
        tv_today1.setText(day+1+"日"+"(明天）");
        tv_today2.setText(day+2+"日"+"(后天）");
        tv_today3.setText(day+3+"日"+"(周日）");
        tv_today4.setText(day+4+"日"+"(周一）");



    }
}
