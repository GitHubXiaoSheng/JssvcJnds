package com.app.trafficclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.entry.Frag16_entry;

import java.util.List;


public class Frag16_Adapter extends RecyclerView.Adapter<Frag16_Adapter.ViewHolder> {
    private Context context;
    private List<Frag16_entry> entryList;

    public Frag16_Adapter(Context context, List<Frag16_entry> entryList) {
        this.context = context;
        this.entryList = entryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frag16_item_personal, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Frag16_entry entry = entryList.get(position);
        String time = entry.getTime().substring(0,10);
        holder.time1Tv.setText(time);
        holder.time2Tv.setText(entry.getTime());
        holder.dataTv.setText("星期五");
        holder.rPersonal.setText("充值人："+entry.getrPersonal());
        holder.plateTv.setText("车牌号：鲁B1000"+entry.getPlate());
        holder.balanceTv.setText("余额："+"1340");
        holder.rechargeTv.setText("充值："+entry.getRecharge());
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView time1Tv;
        private TextView dataTv;
        private TextView rPersonal;
        private TextView plateTv;
        private TextView rechargeTv;
        private TextView balanceTv;
        private TextView time2Tv;
        public ViewHolder(View itemView) {
            super(itemView);
            time1Tv = (TextView) itemView.findViewById(R.id.frag16_item_time1_tv);
            dataTv = (TextView) itemView.findViewById(R.id.frag16_item_data_tv);
            rPersonal = (TextView) itemView.findViewById(R.id.frag16_item_rpersonal_tv);
            plateTv = (TextView) itemView.findViewById(R.id.frag16_item_plate_tv);
            rechargeTv = (TextView) itemView.findViewById(R.id.frag16_item_recharge_tv);
            balanceTv = (TextView) itemView.findViewById(R.id.frag16_item_balance_tv);
            time2Tv = (TextView) itemView.findViewById(R.id.frag16_item_time2_tv);
        }
    }
}
