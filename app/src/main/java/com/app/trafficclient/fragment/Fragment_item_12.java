package com.app.trafficclient.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.trafficclient.R;
import com.app.trafficclient.activity.YWS_weizhangchaxun;
import com.app.trafficclient.util.HttpRequest;
import com.app.trafficclient.util.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_12 extends Fragment {
    private EditText edt_chepaihao;
    private Button btn_chaxun;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_12, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        edt_chepaihao=getActivity().findViewById(R.id.edt_chepaihao);
        btn_chaxun=getActivity().findViewById(R.id.btn_chaxun);
        btn_chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                http();
            }
        });

    }
    boolean is;
    private  void http(){
        HttpRequest.post("GetAllCarPeccancy", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                    String chepaihao="鲁"+edt_chepaihao.getText().toString().trim();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        Log.d("车辆",jsonObject1.toString());
                        is = false;
                        if (chepaihao.equals(jsonObject1.getString("carnumber"))) {
                            Intent intent = new Intent(getContext(), YWS_weizhangchaxun.class);
                            intent.putExtra("chepaihao",chepaihao);
                            startActivity(intent);
                            is = true;
                            break;
                        }
                    }
                    if (!is) {
                        Toast.makeText(getContext(),"没有这个车牌号",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);
    }
}
