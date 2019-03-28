package com.app.trafficclient.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.Response;
import com.app.trafficclient.R;
import com.app.trafficclient.util.HttpRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

public class ZL_Frag37QrCodeActivity extends AppCompatActivity {
    private ImageView qrImg;
    private int width;
    private int height;
    private String payInfo = "";
    private int refreshTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.zl_activity_frag37_qr_code);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        qrImg = (ImageView) findViewById(R.id.qrcode_img);
        qrImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        payInfo = getIntent().getStringExtra("payInfo");
        refreshTime = getIntent().getIntExtra("refreshTime",5);
        Log.d("TAG", "onCreate: "+payInfo +refreshTime);
        qrImg.setImageBitmap(generateQR());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        },0,refreshTime*1000);
    }

    private Bitmap generateQR() {
//        int width = 300;
//        int height = 300;
        Bitmap bitmap = null;
        if(!payInfo.equals("")){
            try {
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

}
