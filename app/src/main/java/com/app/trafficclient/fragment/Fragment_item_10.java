package com.app.trafficclient.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.adapter.ZzjGongjiaochaxun;
import com.app.trafficclient.adapter.ZzjGongjiaochaxun_ArrayAdapter;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment_item_10 extends Fragment implements View.OnClickListener {

    private TextView textView_shou_mo,textView_cheng_zai,textView_xiangqing;
    private LinearLayout linearLayout_1,linearLayout_2;
    private ImageView imageView_1,imageView_2;

    private ListView listView_1,listView_2;
    private ZzjGongjiaochaxun_ArrayAdapter arrayAdapter;
    private List<ZzjGongjiaochaxun> zzjGongjiaochaxunList,zzjGongjiaochaxunList_2;

    private int i,j;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout_10, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        i = 0;
        j = 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zzj_LinearLayout_liebiao_1:
                i ++;
                if (i % 2 == 1) {
                    imageView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.jiantou_left));
                    imageView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.jiantou_bottom));
                    linearLayout_1();
                }else {
                    imageView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.jiantou_left));
                    imageView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.jiantou_left));
                    zzjGongjiaochaxunList.clear();
                    arrayAdapter = new ZzjGongjiaochaxun_ArrayAdapter(getContext(), R.layout.zzj_gongjiaochexun_item, zzjGongjiaochaxunList);
                    listView_1.setAdapter(arrayAdapter);
                }
                break;
            case R.id.zzj_LinearLayout_liebiao_2:
                j ++;
                if (j % 2 == 1) {
                    imageView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.jiantou_left));
                    imageView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.jiantou_bottom));
                    linearLayout_2();
                }else {
                    imageView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.jiantou_left));
                    imageView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.jiantou_left));
                    zzjGongjiaochaxunList_2.clear();
                    arrayAdapter = new ZzjGongjiaochaxun_ArrayAdapter(getContext(), R.layout.zzj_gongjiaochexun_item, zzjGongjiaochaxunList_2);
                    listView_2.setAdapter(arrayAdapter);
                }
                break;
            case R.id.zzj_textView_901_xiangqing:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final AlertDialog dialog = builder.create();
                View view1 = View.inflate(getContext(), R.layout.zzj_901_gongjiao_dialog, null);
                dialog.setView(view1);
                dialog.show();
                LinearLayout linearLayout = view1.findViewById(R.id.zzj_901_dialog_LinearLayout);

                for (int i = 0; i < 15; i++) {
                    View view2 = LayoutInflater.from(getContext()).inflate(R.layout.zzj_901_gongjiao_dialog_item, linearLayout, false);
                    TextView textView_1 = view2.findViewById(R.id.zzj_901_dialog_item_textView_1);
                    TextView textView_2 = view2.findViewById(R.id.zzj_901_dialog_item_textView_2);
                    TextView textView_3 = view2.findViewById(R.id.zzj_901_dialog_item_textView_3);
                    textView_1.setText((i+1)+"");
                    textView_2.setText((i+1)+"");
                    textView_3.setText((i+1)+"");
                    linearLayout.addView(view2);
                }

                Button button = view1.findViewById(R.id.zzj_901_dialog_button_back);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }

    private void linearLayout_1() {
        zzjGongjiaochaxunList.clear();
        HttpRequest.post("GetBusStationInfo", "{\"BusStationId\":1,\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.d("测试", jsonObject.getJSONArray("ROWS_DETAIL") + "");
                    JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String item1 = jsonObject1.getString("BusId") + "（101人）";
                        String item3 = "距离站牌 "+jsonObject1.getInt("Distance") + "米";
                        String item2 = (jsonObject1.getInt("Distance")/333) +"分钟到达";
                        Log.d("测试", item1 + item2 + item3);
                        ZzjGongjiaochaxun gongjiaochaxun = new ZzjGongjiaochaxun(item1, item2, item3);
                        zzjGongjiaochaxunList.add(gongjiaochaxun);
                    }
                    arrayAdapter = new ZzjGongjiaochaxun_ArrayAdapter(getContext(), R.layout.zzj_gongjiaochexun_item, zzjGongjiaochaxunList);
                    listView_1.setAdapter(arrayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);

        zzjGongjiaochaxunList_2.clear();
        arrayAdapter = new ZzjGongjiaochaxun_ArrayAdapter(getContext(), R.layout.zzj_gongjiaochexun_item, zzjGongjiaochaxunList_2);
        listView_2.setAdapter(arrayAdapter);
    }

    private void linearLayout_2() {
        zzjGongjiaochaxunList_2.clear();
        HttpRequest.post("GetBusStationInfo", "{\"BusStationId\":2,\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String item1 = jsonObject1.getString("BusId") + "（101人）";
                        String item3 = "距离站牌 "+jsonObject1.getInt("Distance") + "米";
                        String item2 = (jsonObject1.getInt("Distance")/3600) +"分钟到达";
                        Log.d("测试", item1 + item2 + item3);
                        ZzjGongjiaochaxun gongjiaochaxun = new ZzjGongjiaochaxun(item1, item2, item3);
                        zzjGongjiaochaxunList_2.add(gongjiaochaxun);
                    }
                    arrayAdapter = new ZzjGongjiaochaxun_ArrayAdapter(getContext(), R.layout.zzj_gongjiaochexun_item, zzjGongjiaochaxunList_2);
                    listView_2.setAdapter(arrayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);

        zzjGongjiaochaxunList.clear();
        arrayAdapter = new ZzjGongjiaochaxun_ArrayAdapter(getContext(), R.layout.zzj_gongjiaochexun_item, zzjGongjiaochaxunList);
        listView_1.setAdapter(arrayAdapter);
    }

    private void initView() {
        textView_shou_mo = getActivity().findViewById(R.id.zzj_textView_901_shoumobanche);
        textView_cheng_zai = getActivity().findViewById(R.id.zzj_textView_901_chengzairenshu);
        textView_xiangqing = getActivity().findViewById(R.id.zzj_textView_901_xiangqing);
        textView_xiangqing.setOnClickListener(this);

        linearLayout_1 = getActivity().findViewById(R.id.zzj_LinearLayout_liebiao_1);
        linearLayout_2 = getActivity().findViewById(R.id.zzj_LinearLayout_liebiao_2);
        linearLayout_1.setOnClickListener(this);
        linearLayout_2.setOnClickListener(this);

        imageView_1 = getActivity().findViewById(R.id.zzj_image_jiantou_1);
        imageView_2 = getActivity().findViewById(R.id.zzj_image_jiantou_2);

        listView_1 = getActivity().findViewById(R.id.zzj_listView_zhongyiyuan);
        listView_2 = getActivity().findViewById(R.id.zzj_listView_lianxiang);
        zzjGongjiaochaxunList = new ArrayList<>();
        zzjGongjiaochaxunList_2 = new ArrayList<>();
    }
}
