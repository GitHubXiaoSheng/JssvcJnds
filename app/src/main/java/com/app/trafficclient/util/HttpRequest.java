package com.app.trafficclient.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.trafficclient.MyApplication;

import org.json.JSONObject;
/**
 *  网络请求
 */
public class HttpRequest {
    public static void post(String name, String param, Response.Listener<JSONObject>listener,Response.ErrorListener errorListener){
        String url="http://192.168.0.100:8088/transportservice/action/"+name+".do";
        JsonObjectRequest request=new JsonObjectRequest(1,url,param,listener,errorListener);
        MyApplication.queue.add(request);

    }

    public static void bitmap(String name, Response.Listener<Bitmap>listener,Response.ErrorListener errorListener){
        String url="http://192.168.0.100:8088/transportservice"+name;
        ImageRequest request=new ImageRequest(url,listener,0,0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888,errorListener);
        MyApplication.queue.add(request);
    }

}

