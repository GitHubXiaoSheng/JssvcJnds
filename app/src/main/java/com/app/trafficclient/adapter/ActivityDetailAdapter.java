package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.app.trafficclient.R;
import com.app.trafficclient.usebean.UserBusLine;

import java.util.List;

public class ActivityDetailAdapter extends BaseExpandableListAdapter {
    private Context appContext;
    private List<UserBusLine.ROWSDETAILBean>busLines;
    public ActivityDetailAdapter(Context detail, List<UserBusLine.ROWSDETAILBean> beans) {
        this.appContext = detail;
        this.busLines = beans;
    }

    @Override
    public int getGroupCount() {
        return busLines.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return busLines.get(groupPosition).getBusDate().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view==null){
            view= LayoutInflater.from(appContext).inflate(R.layout.activity_date_view,null);
        }
        TextView tv_station=view.findViewById(R.id.tv_station);
        TextView tv_money=view.findViewById(R.id.tv_money);
        TextView tv_id=view.findViewById(R.id.tv_id);
        tv_station.setText(busLines.get(groupPosition).getStartSite()+"—"+busLines.get(groupPosition).getEndSite());
        tv_money.setText("票价：￥"+busLines.get(groupPosition).getTicket());
        tv_id.setText("订单编号："+busLines.get(groupPosition).getId());
        TextView tv_image=view.findViewById(R.id.tv_image);
        if (isExpanded){
            tv_image.setBackgroundResource(R.drawable.down);
        }else {
            tv_image.setBackgroundResource(R.drawable.right);

        }


        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        if (view==null){
            view=LayoutInflater.from(appContext).inflate(R.layout.item_data_detail_,null);
        }
        TextView tv_image=view.findViewById(R.id.tv_image);
        TextView tv_date=view.findViewById(R.id.tv_date);
        tv_date.setText(busLines.get(groupPosition).getBusDate().get(childPosition));

        if (childPosition==0){
            tv_image.setVisibility(View.VISIBLE);
        }else {
            tv_image.setVisibility(View.INVISIBLE);

        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
