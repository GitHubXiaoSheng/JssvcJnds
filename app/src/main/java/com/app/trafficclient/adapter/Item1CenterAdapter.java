package com.app.trafficclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.usebean.MyCarInfo;

import java.util.List;

public class Item1CenterAdapter extends BaseAdapter {

    private  Context mContext;
    private List<MyCarInfo> mCarInfos;
    public Item1CenterAdapter(Context context, List<MyCarInfo> carInfo) {
        this.mContext = context;
        this.mCarInfos = carInfo;
    }

    @Override
    public int getCount() {
        return mCarInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null){
            convertView = View.inflate(mContext,R.layout.item_center,null);
            holder = new ViewHolder( convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_balance.setText("余额：100元");
        holder.tv_carNumber.setText(mCarInfos.get(position).getCarNumber());
        holder.tv_image.setBackgroundResource(mContext.getResources().getIdentifier(mCarInfos.get(position).getType(),"drawable",mContext.getPackageName()));

        return convertView;
    }

    public static class ViewHolder {
        View rootView;
        TextView tv_image;
        TextView tv_carNumber;
        TextView tv_balance;
        ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_image =  rootView.findViewById(R.id.tv_image);
            this.tv_carNumber = rootView.findViewById(R.id.tv_carNumber);
            this.tv_balance = rootView.findViewById(R.id.tv_balance);
        }
    }
}
