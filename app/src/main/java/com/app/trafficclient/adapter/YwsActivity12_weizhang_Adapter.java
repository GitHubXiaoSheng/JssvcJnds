package com.app.trafficclient.adapter;

        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.app.trafficclient.R;

        import java.util.ArrayList;
        import java.util.List;

public class YwsActivity12_weizhang_Adapter extends RecyclerView.Adapter<YwsActivity12_weizhang_Adapter.ViewHolder> {
    private List<Weizhangcheliang>myweizhangcheliang ;
    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.tv_return_chepaihao);
        }
    }
    public  YwsActivity12_weizhang_Adapter(List<Weizhangcheliang>weizhangcheliang){
        myweizhangcheliang=weizhangcheliang;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.yws_recyclerview_child_item,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Weizhangcheliang weizhangcheliang=myweizhangcheliang.get(position);
        holder.textView.setText(weizhangcheliang.getText());
    }

    @Override
    public int getItemCount() {
        return myweizhangcheliang.size();
    }
}

