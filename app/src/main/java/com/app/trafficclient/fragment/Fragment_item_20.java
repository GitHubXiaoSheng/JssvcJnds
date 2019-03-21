package com.app.trafficclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.usebean.GetUserInfo;
import com.app.trafficclient.util.HttpRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment_item_20 extends Fragment {
    private View view;
    private GetUserInfo.ROWSDETAILBean user;

    //个人中心
    private ImageView hearImg;
    private TextView nameTv;
    private TextView genderTv;
    private TextView phoneTv;
    private TextView idcardTv;
    private TextView registraionTv;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_layout_20, container, false);
        initView();
        initPersonal("user1");
        return view;
    }

    private void initView(){

        //个人中心
        hearImg = (ImageView) view.findViewById(R.id.frag20_touxiang_img);
        nameTv = (TextView) view.findViewById(R.id.frag20_name_tv);
        genderTv = (TextView) view.findViewById(R.id.frag20_gender_tv);
        phoneTv = (TextView) view.findViewById(R.id.frag20_phone_tv);
        idcardTv = (TextView) view.findViewById(R.id.frag20_idcard_tv);
        registraionTv = (TextView) view.findViewById(R.id.frag20_registration_tv);
        addItem();
    }

    private void initPersonal(final String username){
        String param = "{\"UserName\":\"user1\"}";
        HttpRequest.post("GetSUserInfo", param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                GetUserInfo userInfo = new Gson().fromJson(jsonObject.toString(), GetUserInfo.class);
                List<GetUserInfo.ROWSDETAILBean> userList = userInfo.getROWS_DETAIL();
                for (int i=0;i<userList.size();i++) {
                    if(userList.get(i).getUsername().equals(username)){
                        user = userList.get(i);
                        Log.d("TAG", "onResponse: user="+userInfo.toString());
                        nameTv.setText("姓名："+user.getPname());
                        if(user.getPsex().equals("男")){
                            hearImg.setImageResource(R.drawable.touxiang_2);
                        }else {
                            hearImg.setImageResource(R.drawable.touxiang_1);
                        }
                        genderTv.setText("性别："+user.getPsex());
                        phoneTv.setText("电话号码："+user.getPtel());
                        String idcar = user.getPcardid();
                        String h = idcar.substring(0, 6);
                        String l = idcar.substring(idcar.length() - 3, idcar.length());
                        idcardTv.setText("身份证号码："+h+"*********"+l);
                        registraionTv.setText("注册时间："+user.getPregisterdate());
                        return;
                    }
                }
            }
        },null);
    }

    private void addItem() {
        int[] carLogo = new int[]{R.drawable.richan,R.drawable.voervo,
                R.drawable.xiandai,R.drawable.xuefulan,R.drawable.zhonghua,
                R.drawable.baoma,R.drawable.benchi,R.drawable.bentian,
                R.drawable.biaozhi,R.drawable.bieke,R.drawable.biyadi,
                R.drawable.dazhong,R.drawable.fengtian,R.drawable.mazhida,
                R.drawable.qirui,R.drawable.sanling,R.drawable.sibalu,};
        String[] plateH = new String[]{"辽A ","辽B ","鲁A ","鲁B "};
        List<String> palteList = new ArrayList<>();
        List<Integer> logoList = new ArrayList<>();

        linearLayout = (LinearLayout) view.findViewById(R.id.frag20_cars_layout);

        for (int i = 0;i<(int)(Math.random()*10+3);i++ ) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.zl_frag20_item_personal2, null);
            ImageView carlogoImg = (ImageView) view.findViewById(R.id.frag20_car_logo_img);
            TextView plateTv = (TextView) view.findViewById(R.id.frag20_carplate_tv);
            TextView balanceTv = (TextView) view.findViewById(R.id.frag20_carbalance_tv);
            int logo = (int) (Math.random() * 16);
            int balance = (int) (Math.random() * 1000);
            String plate = plateH[(int) (Math.random()*10)%4] + String.valueOf((int) (Math.random()*10000+10000));
            if(!logoList.contains(logo) && !palteList.contains(plate)){
                logoList.add(logo);
                palteList.add(plate);
                carlogoImg.setImageResource(carLogo[logo]);
                plateTv.setText(plate);
                balanceTv.setText("余额:"+String.valueOf(balance));
                linearLayout.addView(view);
                Log.d("TAG", "addItem: i="+i);
            }else {
                Log.d("TAG", "continue: i="+i);
                continue;
            }
        }

    }
}
