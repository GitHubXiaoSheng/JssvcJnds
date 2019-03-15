package com.app.trafficclient;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;

import com.android.volley.Response;
import com.app.trafficclient.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MyReceiver extends BroadcastReceiver {

    private int yuzhi_wendu,yuzhi_shidu,yuzhi_guangzhao,yuzhi_co2,yuzhi_pm25,yuzhi_daolu;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    private ContentValues values;

    private NotificationManager notificationManager;

    @Override
    public void onReceive(final Context context, Intent intent) {

        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        SharedPreferences sharedPreferences2 = context.getSharedPreferences("yuzhi",Context.MODE_PRIVATE);
        yuzhi_wendu = sharedPreferences2.getInt("wendu",0);
        yuzhi_shidu = sharedPreferences2.getInt("shidu",0);
        yuzhi_guangzhao = sharedPreferences2.getInt("guangzhao",0);
        yuzhi_co2 = sharedPreferences2.getInt("co2",0);
        yuzhi_pm25 = sharedPreferences2.getInt("pm25",0);
        yuzhi_daolu = sharedPreferences2.getInt("daolu",0);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(context, MySQLiteOpenHelper.DBNAME, null, 1);
        db = mySQLiteOpenHelper.getWritableDatabase();

        HttpRequest.post("GetAllSense", "{\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int wendu = jsonObject.getInt("temperature");
                    int shidu = jsonObject.getInt("humidity");
                    int guangzhao = jsonObject.getInt("LightIntensity");
                    int co2 = jsonObject.getInt("co2");
                    int pm25 = jsonObject.getInt("pm2.5");

                    if (wendu > yuzhi_wendu) {
                        values = new ContentValues();
                        values.put("leixing","【温度】报警");
                        values.put("zhi",wendu);
                        values.put("dq_zhi",yuzhi_wendu);
                        db.insert("tongzhi_wendu",null,values);

                        Notification notification = new Notification.Builder(context)
                                .setContentTitle("警告通知")
                                .setContentText("温度 报警，阈值：" + yuzhi_wendu + "，当前值：" + wendu)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.zzj_background_1)
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.zzj_background_1))
                                .build();
                        notificationManager.notify(1,notification);
                    }
                    if (shidu > yuzhi_shidu) {
                        values = new ContentValues();
                        values.put("leixing","【湿度】报警");
                        values.put("zhi",shidu);
                        values.put("dq_zhi",yuzhi_shidu);
                        db.insert("tongzhi_shidu",null,values);

                        Notification notification = new Notification.Builder(context)
                                .setContentTitle("警告通知")
                                .setContentText("湿度 报警，阈值：" + yuzhi_shidu + "，当前值：" + shidu)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.zzj_background_1)
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.zzj_background_1))
                                .build();
                        notificationManager.notify(2,notification);
                    }
                    if (guangzhao > yuzhi_guangzhao) {
                        values = new ContentValues();
                        values.put("leixing","【光照强度】报警");
                        values.put("zhi",guangzhao);
                        values.put("dq_zhi",yuzhi_guangzhao);
                        db.insert("tongzhi_guangzhao",null,values);

                        Notification notification = new Notification.Builder(context)
                                .setContentTitle("警告通知")
                                .setContentText("光照强度 报警，阈值：" + yuzhi_guangzhao + "，当前值：" + guangzhao)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.zzj_background_1)
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.zzj_background_1))
                                .build();
                        notificationManager.notify(3,notification);
                    }
                    if (co2 > yuzhi_co2) {
                        values = new ContentValues();
                        values.put("leixing","【CO2】报警");
                        values.put("zhi",co2);
                        values.put("dq_zhi",yuzhi_co2);
                        db.insert("tongzhi_co2",null,values);

                        Notification notification = new Notification.Builder(context)
                                .setContentTitle("警告通知")
                                .setContentText("CO2 报警，阈值：" + yuzhi_co2 + "，当前值：" + co2)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.zzj_background_1)
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.zzj_background_1))
                                .build();
                        notificationManager.notify(4,notification);
                    }
                    if (pm25 > yuzhi_pm25) {
                        values = new ContentValues();
                        values.put("leixing","【PM2.5】报警");
                        values.put("zhi",pm25);
                        values.put("dq_zhi",yuzhi_pm25);
                        db.insert("tongzhi_pm25",null,values);

                        Notification notification = new Notification.Builder(context)
                                .setContentTitle("警告通知")
                                .setContentText("PM2.5 报警，阈值：" + yuzhi_pm25 + "，当前值：" + pm25)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.zzj_background_1)
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.zzj_background_1))
                                .build();
                        notificationManager.notify(5,notification);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);

        HttpRequest.post("GetRoadStatus", "{\"RoadId\":1,\"UserName\":\"user1\"}", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    int daolu = jsonObject.getInt("Status");

                    if (daolu > yuzhi_daolu) {
                        values = new ContentValues();
                        values.put("leixing","【道路状况】报警");
                        values.put("zhi",daolu);
                        values.put("dq_zhi",yuzhi_daolu);
                        db.insert("tongzhi_daolu",null,values);

                        Notification notification = new Notification.Builder(context)
                                .setContentTitle("警告通知")
                                .setContentText("道路状况 报警，阈值：" + yuzhi_daolu + "，当前值：" + daolu)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.zzj_background_1)
                                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.zzj_background_1))
                                .build();
                        notificationManager.notify(6,notification);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },null);

    }
}
