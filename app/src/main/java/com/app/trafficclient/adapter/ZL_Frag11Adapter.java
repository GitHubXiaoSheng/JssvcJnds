package com.app.trafficclient.adapter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.R;
import com.app.trafficclient.entry.ZL_Frag11_TrafficMangEntry;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ZL_Frag11Adapter extends RecyclerView.Adapter<ZL_Frag11Adapter.ViewHolder> {

    private String TAG = "ZL_Frag11Adapter";
    private Context context;
    private List<ZL_Frag11_TrafficMangEntry> entryList;

    public ZL_Frag11Adapter(Context context, List<ZL_Frag11_TrafficMangEntry> entryList) {
        this.context = context;
        this.entryList = entryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zl_frag11_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ZL_Frag11_TrafficMangEntry entry = entryList.get(position);
        holder.roadTv.setText(String.valueOf(entry.getRoad()));
        holder.redTv.setText(String.valueOf(entry.getRedTime()));
        holder.yellowTv.setText(String.valueOf(entry.getYellowTime()));
        holder.greenTv.setText(String.valueOf(entry.getGreenTime()));
        holder.chooseCb.setChecked(entry.isChoose());
        holder.setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view1 = LayoutInflater.from(context).inflate(R.layout.zl_frag11_dialog, null);
                final EditText redEt = (EditText) view1.findViewById(R.id.frag11_dialog_red_et);
                final EditText yellowEt = (EditText) view1.findViewById(R.id.frag11_dialog_yellow_et);
                final EditText greenEt = (EditText) view1.findViewById(R.id.frag11_dialog_green_et);
                Button okBtn = (Button) view1.findViewById(R.id.frag11_dialog_ok_btn);
                Button cancelBtn = (Button) view1.findViewById(R.id.frag11_dialog_cancel_btn);
                builder.setCancelable(false).setView(view1);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String red = redEt.getText().toString().trim();
                        String yellow = yellowEt.getText().toString().trim();
                        String green = greenEt.getText().toString().trim();
                        if(!red.equals("") && !yellow.equals("") && !green.equals("")){
                            setConfig(entry.getRoad(),red,yellow,green);
                            alertDialog.cancel();
                        }
                    }
                });
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });
        holder.chooseCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                entryList.get(position).setChoose(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView roadTv;
        private TextView redTv;
        private TextView yellowTv;
        private TextView greenTv;
        private CheckBox chooseCb;
        private Button setBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            roadTv = (TextView) itemView.findViewById(R.id.frag11item_road_tv);
            redTv = (TextView) itemView.findViewById(R.id.frag11item_red_tv);
            yellowTv = (TextView) itemView.findViewById(R.id.frag11item_yellow_tv);
            greenTv = (TextView) itemView.findViewById(R.id.frag11item_green_tv);
            chooseCb = (CheckBox) itemView.findViewById(R.id.frag11item_choose_cb);
            setBtn = (Button) itemView.findViewById(R.id.frag11item_set_btn);
        }
    }


    private void setConfig(int road,String red,String yellow,String green){
        String param = "{\"TrafficLightId\":"+road+",\"RedTime\":"+red+",\"GreenTime\":"+green+",\"YellowTime\":"+yellow+",\"UserName\":\"user1\"}";
        HttpRequest.post("SetTrafficLightConfig", param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    if(jsonObject.getString("RESULT").equals("S")){
                        Toast.makeText(MyApplication.appContext,"设置成功！",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MyApplication.appContext,"设置失败！",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }

    public List<ZL_Frag11_TrafficMangEntry> getEntryList() {
        return entryList;
    }
}


