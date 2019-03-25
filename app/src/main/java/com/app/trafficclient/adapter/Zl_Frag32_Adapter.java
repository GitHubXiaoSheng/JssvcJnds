package com.app.trafficclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.trafficclient.R;
import com.app.trafficclient.activity.ZL_MetroInfoActivity;
import com.app.trafficclient.entry.ZL_MetroEntry;
import com.app.trafficclient.usebean.ZL_Metro;

import java.util.List;

public class Zl_Frag32_Adapter extends RecyclerView.Adapter<Zl_Frag32_Adapter.ViewHolder> {
    private Context context;
    private List<ZL_MetroEntry> entryList;

    public Zl_Frag32_Adapter(Context context, List<ZL_MetroEntry> entryList) {
        this.context = context;
        this.entryList = entryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zl_frag32_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ZL_MetroEntry entry = entryList.get(position);
        holder.metroName.setText(entry.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ZL_MetroInfoActivity.class);
                intent.putExtra("MetroId", entry.getId());
                Log.d("TAG", "onClick: id="+entry.getId());
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
        private TextView metroName;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            metroName = (TextView) itemView.findViewById(R.id.frag32_item_metroname_tv);
        }
    }
}
