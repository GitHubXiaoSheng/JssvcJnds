package com.app.trafficclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.activity.ZL_SpotInfoActivity;
import com.app.trafficclient.entry.ZL_Frag35Entry;

import java.util.List;

public class ZL_Frag35Adapter extends RecyclerView.Adapter<ZL_Frag35Adapter.ViewHolder> {
    private Context context;
    private List<ZL_Frag35Entry> entryList;

    public ZL_Frag35Adapter(Context context, List<ZL_Frag35Entry> entryList) {
        this.context = context;
        this.entryList = entryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zl_frag35_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ZL_Frag35Entry entry = entryList.get(position);
        holder.imageView.setImageBitmap(entry.getImgBitmap());
        holder.spotTv.setText(entry.getSpotName());
        holder.ticketTv.setText("票价￥"+entry.getTicket());
        Log.d("TAG", "onBindViewHolder: e="+entry.toString());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ZL_SpotInfoActivity.class);
                intent.putExtra("SpotId",entry.getId() );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private ImageView imageView;
        private TextView spotTv;
        private TextView ticketTv;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.frag35_item_img);
            spotTv = (TextView) itemView.findViewById(R.id.frag35_item_Scenicspot_tv);
            ticketTv = (TextView) itemView.findViewById(R.id.frag35_item_ticket_tv);
        }
    }
}
