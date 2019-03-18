package com.app.trafficclient;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "sql.db";

    public Context contextId;

    public static final String CHONGZHI_JILU = "create table chongzhi("
            + "id Integer primary key autoincrement,"
            + "chehao Integer,"
            + "jine Integer,"
            + "caozuoren text,"
            + "time text)";

    public static final String HUANJING_ZHIBIAO = "create table huanjing("
            + "id Integer primary key autoincrement,"
            + "wendu Integer,"
            + "shidu Integer,"
            + "guangzhao Integer,"
            + "co2 Integer,"
            + "pm25 Integer,"
            + "time text)";

    public static final String DAOLU_ZHIBIAO = "create table daolu("
            + "id Integer primary key autoincrement,"
            + "daolu Integer,"
            + "time text)";

    public static final String TONGZHI_QUANBU = "create table tongzhi_quanbu("
            + "id Integer primary key autoincrement,"
            + "leixing text,"
            + "zhi Integer,"
            + "dq_zhi Integer)";

    public static final String TONGZHI_WENDU = "create table tongzhi_wendu("
            + "id Integer primary key autoincrement,"
            + "leixing text,"
            + "zhi Integer,"
            + "dq_zhi Integer)";

    public static final String TONGZHI_SHIDU = "create table tongzhi_shidu("
            + "id Integer primary key autoincrement,"
            + "leixing text,"
            + "zhi Integer,"
            + "dq_zhi Integer)";

    public static final String TONGZHI_GUANGZHAO = "create table tongzhi_guangzhao("
            + "id Integer primary key autoincrement,"
            + "leixing text,"
            + "zhi Integer,"
            + "dq_zhi Integer)";

    public static final String TONGZHI_CO2 = "create table tongzhi_co2("
            + "id Integer primary key autoincrement,"
            + "leixing text,"
            + "zhi Integer,"
            + "dq_zhi Integer)";

    public static final String TONGZHI_PM25 = "create table tongzhi_pm25("
            + "id Integer primary key autoincrement,"
            + "leixing text,"
            + "zhi Integer,"
            + "dq_zhi Integer)";

    public static final String TONGZHI_DAOLU = "create table tongzhi_daolu("
            + "id Integer primary key autoincrement,"
            + "leixing text,"
            + "zhi Integer,"
            + "dq_zhi Integer)";

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        contextId = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CHONGZHI_JILU);
        db.execSQL(HUANJING_ZHIBIAO);
        db.execSQL(DAOLU_ZHIBIAO);
        db.execSQL(TONGZHI_QUANBU);
        db.execSQL(TONGZHI_WENDU);
        db.execSQL(TONGZHI_SHIDU);
        db.execSQL(TONGZHI_GUANGZHAO);
        db.execSQL(TONGZHI_CO2);
        db.execSQL(TONGZHI_PM25);
        db.execSQL(TONGZHI_DAOLU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
