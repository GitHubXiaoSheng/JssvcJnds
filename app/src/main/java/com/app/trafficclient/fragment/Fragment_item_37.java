package com.app.trafficclient.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.activity.ZL_Frag37QrCodeActivity;
import com.app.trafficclient.util.HttpRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_item_37 extends Fragment {

    private View view;
    private TextView titleTv;
    private TextView qrCodetitleTv;
    private Spinner spinner;
    private EditText amountEt;
    private EditText timeEt;
    private Button generateBtn;
    private ImageView qrImg;
    private LinearLayout generateLayout;
    private LinearLayout qrCodeLayout;

    private String payInfo = "";
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layou_item_37, container, false);
        initView();
        setListener();
        return view;
    }

    private void initView() {
        titleTv = (TextView) view.findViewById(R.id.frag37_title_tv);
        qrCodetitleTv = (TextView) view.findViewById(R.id.frag37_qrcode_title_tv);
        spinner = (Spinner) view.findViewById(R.id.frag37_spinner);
        amountEt = (EditText) view.findViewById(R.id.frag37_amount_et);
        timeEt = (EditText) view.findViewById(R.id.frag37_time_et);
        generateBtn = (Button) view.findViewById(R.id.frag37_generate_btn);
        qrImg = (ImageView) view.findViewById(R.id.frag37_qrcode_img);
        generateLayout = (LinearLayout) view.findViewById(R.id.frag37_generate_layout);
        qrCodeLayout = (LinearLayout) view.findViewById(R.id.frag37_qrcode_layout);
    }

    private void setListener(){
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = amountEt.getText().toString().trim();
                String time = timeEt.getText().toString().trim();
                if(!amount.equals("") && !time.equals("")){
                    qrCodeLayout.setVisibility(View.VISIBLE);
                    generateLayout.setVisibility(View.GONE);
                    qrImg.setImageBitmap(generateQR());
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            refresh();
                        }
                    },0,Integer.valueOf(time)*1000);
                }else {
                    Toast.makeText(getContext(), "请输入金额和刷新时间！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        qrImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                qrCodetitleTv.setVisibility(View.VISIBLE);
                qrCodetitleTv.setText(payInfo);
                return true;
            }
        });
        qrImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ZL_Frag37QrCodeActivity.class);
                intent.putExtra("payInfo", payInfo);
                intent.putExtra("refreshTime", Integer.valueOf(timeEt.getText().toString().trim()));
                startActivity(intent);
            }
        });

    }

    private Bitmap generateQR() {
        Log.d("TAG", "generateQR: 执行");
        String amount = amountEt.getText().toString().trim();
        int width = 300;
        int height = 300;
        Bitmap bitmap = null;
        if(!amount.equals("")){
            try {
                payInfo = "车辆编号="+(spinner.getSelectedItemPosition()+1)+"，付费金额="+amount;
                String time = "\n时间："+new SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis());
                Hashtable<EncodeHintType,String> hints = new Hashtable<>();
                hints.put(EncodeHintType.CHARACTER_SET,"utf-8");
                BitMatrix matrix = null;
                matrix = new QRCodeWriter().encode(payInfo+time, BarcodeFormat.QR_CODE,width,height,hints);
                int[] pixels = new int[width*height];
                for(int y=0;y<height;y++){
                    for(int x=0;x < width;x++){
                        if(matrix.get(x,y)){
                            pixels[y * width + x] = 0xff000000;
                        }else{
                            pixels[y * width + x] = 0xffffffff;
                        }
                    }
                }
                bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels,0,width,0,0,width,height);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private void refresh(){
        String param = "{\"CarId\":2, \"UserName\":\"user1\"}";
        HttpRequest.post("GetCarAccountBalance", param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                qrImg.setImageBitmap(generateQR());
            }
        },null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
        }
    }
}
