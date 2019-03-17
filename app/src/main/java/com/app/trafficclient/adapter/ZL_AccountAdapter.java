package com.app.trafficclient.adapter;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.trafficclient.MyApplication;
import com.app.trafficclient.R;
import com.app.trafficclient.entry.ZL_AccountEntry;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ZL_AccountAdapter extends RecyclerView.Adapter<ZL_AccountAdapter.ViewHolder> {

    private List<ZL_AccountEntry> entryList;
    private Context mContext;
    private List<ZL_AccountEntry> plateList = new ArrayList<>();

    public ZL_AccountAdapter(Context context, List<ZL_AccountEntry> entryList) {
        mContext =context;
        this.entryList = entryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApplication.appContext)
                .inflate(R.layout.zl_frag9_item_account_mang,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ZL_AccountEntry entry = entryList.get(position);
        int min = mContext.getSharedPreferences("account",mContext.MODE_PRIVATE).getInt("accountmin", 50);
        if(entry.getBalance()< min){
          holder.view.setBackgroundColor(Color.rgb(255,204,0));
        }else {
            holder.view.setBackgroundColor(Color.WHITE);
        }
        holder.idTv.setText(String.valueOf(entry.getId()));
        holder.carLogoImg.setImageResource(entry.getImgId());
        holder.plateTv.setText(String.valueOf(entry.getPlate()));
        holder.ownerTv.setText("车主："+String.valueOf(entry.getOwner()));
        holder.balanceTv.setText("余额："+String.valueOf(entry.getBalance())+"元");
        holder.chooseCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    plateList.add(entry);
                }else {
                    for(int i=0;i<plateList.size();i++){
                        if(plateList.get(i).getPlate().equals(entry.getPlate())){
                            plateList.remove(i);
                        }
                    }
                }
                Log.d("TAG", "onCheckedChanged: "+plateList.toString());
            }
        });
        holder.rechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(entry);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    private void showDialog(final ZL_AccountEntry entry){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.zl_frag9_dialog,null);
        builder.setView(view).setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        TextView platesTv = (TextView) view.findViewById(R.id.frag9_dialog_plates_tv);
        final EditText amountEt = (EditText) view.findViewById(R.id.frag9_dialog_amount_et);
        Button rechargeBtn = (Button) view.findViewById(R.id.frag9_dialog_recharge_btn);
        Button cancelBtn = (Button) view.findViewById(R.id.frag9_dialog_cancel_btn);
        String plates = "车牌号：";
        if(plateList.size() == 0){
            plates +=entry.getPlate();
        }else {
            for(int i=0;i<plateList.size();i++){
                plates += " "+plateList.get(i).getPlate();
            }
        }
        platesTv.setText(plates);
        alertDialog.show();
        rechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = amountEt.getText().toString().trim();
                if(!amount.equals("")){
                    if(Integer.valueOf(amount)>0 && Integer.valueOf(amount)<1000){
                        if(plateList.size() == 0){
                            String param = "{\"CarId\":"+String.valueOf(entry.getId())+",\"Money\":"+String.valueOf(amount)+", \"UserName\":\"user1\"}";
                            HttpRequest.post("SetCarAccountRecharge", param, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    alertDialog.cancel();
                                }
                            },null);
                        }else {
                            recharge(0,Integer.valueOf(amount));
                            alertDialog.cancel();
                        }
                    }
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

    private void recharge(final int carId, final int amount){
        if(plateList.size()>0 && carId<plateList.size()){
            int car = plateList.get(carId).getId();
            String param = "{\"CarId\":"+String.valueOf(car)+",\"Money\":"+String.valueOf(amount)+", \"UserName\":\"user1\"}";
            HttpRequest.post("SetCarAccountRecharge", param, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    try {
                        if(jsonObject.getString("RESULT").equals("S")){
                            if(carId>=plateList.size()){
                                String info = "";
                                for(int i=0;i<plateList.size();i++){
                                    info += " "+plateList.get(i).getPlate();
                                }
                                Toast.makeText(MyApplication.appContext, info+"充值成功！", Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                recharge(carId+1,amount);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(MyApplication.appContext, "充值错误！", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView idTv;
        private ImageView carLogoImg;
        private TextView plateTv;
        private TextView ownerTv;
        private TextView balanceTv;
        private CheckBox chooseCheck;
        private Button rechargeBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            idTv = (TextView) itemView.findViewById(R.id.frag9item_id_tv);
            carLogoImg = (ImageView) itemView.findViewById(R.id.frag9item_logo_img);
            plateTv = (TextView) itemView.findViewById(R.id.frag9item_plate_tv);
            ownerTv = (TextView) itemView.findViewById(R.id.frag9item_owner_tv);
            balanceTv = (TextView) itemView.findViewById(R.id.frag9item_balance_tv);
            chooseCheck = (CheckBox) itemView.findViewById(R.id.frag9item_checkbox);
            rechargeBtn = (Button) itemView.findViewById(R.id.frag9item_recharge_btn);
        }
    }


}
