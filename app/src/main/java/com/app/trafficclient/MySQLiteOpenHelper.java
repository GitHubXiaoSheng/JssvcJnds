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

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        contextId = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CHONGZHI_JILU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
