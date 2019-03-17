package com.app.trafficclient.adapter;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.app.trafficclient.MySQLiteOpenHelper;
        import com.app.trafficclient.R;
        import com.app.trafficclient.activity.YWS_weizhangchaxun;

        import java.util.ArrayList;
        import java.util.List;

public class YwsActivity12_weizhang_Adapter extends RecyclerView.Adapter<YwsActivity12_weizhang_Adapter.ViewHolder> {
    private List<Weizhangcheliang>myweizhangcheliang ;
    private ImageView img_plus;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

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
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.yws_recyclerview_child_item,parent,false);
        ViewHolder holder=new ViewHolder(view);

         img_plus=view.findViewById(R.id.image_plus);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Weizhangcheliang weizhangcheliang=myweizhangcheliang.get(position);
        holder.textView.setText(weizhangcheliang.getText());
        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySQLiteOpenHelper=new MySQLiteOpenHelper(view.getContext(),MySQLiteOpenHelper.DBNAME,null,1);
                db=mySQLiteOpenHelper.getWritableDatabase();
                db.delete("weizhang","id = ?",new String[]{String.valueOf(position+1)});

            }
        });
    }

    @Override
    public int getItemCount() {
        return myweizhangcheliang.size();
    }
}

