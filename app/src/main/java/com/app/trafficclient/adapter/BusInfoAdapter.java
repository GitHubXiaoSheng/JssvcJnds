package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.usebean.BusInfo;

import java.util.List;

public class BusInfoAdapter extends BaseExpandableListAdapter {
    private List<BusInfo.ROWSDETAILBean> busList;
    private Context appContext;

    public BusInfoAdapter(List<BusInfo.ROWSDETAILBean> busList, Context context) {
        this.busList = busList;
        this.appContext = context;
    }

    @Override
    public int getGroupCount() {
        return busList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //获取站台信息
        return busList.get(groupPosition).getSites().size();
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(appContext, R.layout.bus_parent_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BusInfo.ROWSDETAILBean bus = busList.get(groupPosition);
        viewHolder.tv_roadName.setText(bus.getName());
        String line = bus.getSites().get(0) + "------" + bus.getSites().get(bus.getSites().size() - 1);
        viewHolder.tv_line.setText(line);
        viewHolder.tv_ticket.setText(bus.getTicket() + "");
        viewHolder.tv_miler.setText(bus.getMileage() + "km");
        String start = bus.getTime().get(0).getStarttime() + "----" + bus.getTime().get(0).getEndtime();
        String end = bus.getTime().get(1).getStarttime() + "----" + bus.getTime().get(1).getEndtime();
        viewHolder.tv_start_time.setText(start);
        viewHolder.tv_end_time.setText(end);
        if (isExpanded) {
            viewHolder.iv_arrow.setImageResource(R.drawable.down);
        } else {
            viewHolder.iv_arrow.setImageResource(R.drawable.right);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(appContext, R.layout.bus_child_item, null);
        }
        TextView tv_startOrEnd = convertView.findViewById(R.id.tv_startOrEnd);
        TextView tv_station = convertView.findViewById(R.id.tv_station);
        List<String> sites = busList.get(groupPosition).getSites();
        tv_station.setText(sites.get(childPosition));

        if (childPosition == 0 || childPosition == sites.size() - 1) {
            tv_startOrEnd.setVisibility(View.VISIBLE);
            if (childPosition == 0) {
                tv_startOrEnd.setText("起点：");
            } else {
                tv_startOrEnd.setText("终点：");
            }
        } else {
            tv_startOrEnd.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolder {
        View rootView;
        TextView tv_roadName;
        TextView tv_line;
        TextView tv_ticket;
        TextView tv_miler;
        ImageView iv_arrow;
        TextView tv_start_time;
        TextView tv_end_time;

        ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_roadName = rootView.findViewById(R.id.tv_roadName);
            this.tv_line = rootView.findViewById(R.id.tv_line);
            this.tv_ticket = rootView.findViewById(R.id.tv_ticket);
            this.tv_miler = rootView.findViewById(R.id.tv_miler);
            this.iv_arrow = rootView.findViewById(R.id.iv_arrow);
            this.tv_start_time = rootView.findViewById(R.id.tv_start_time);
            this.tv_end_time = rootView.findViewById(R.id.tv_end_time);
        }
    }
}
