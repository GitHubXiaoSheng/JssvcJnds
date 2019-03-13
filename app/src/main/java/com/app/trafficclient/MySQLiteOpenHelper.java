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

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        contextId = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CHONGZHI_JILU);
        db.execSQL(HUANJING_ZHIBIAO);
        db.execSQL(DAOLU_ZHIBIAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
