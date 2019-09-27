package com.example.community;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class FixInfoDBAdapter {
    private static final String DB_NAME = "fix.db";
    private static final String DB_TABLE = "fixinfo";
    private static final int DB_VERSION = 1;
    public static final String NUM = "num";
    public static final String BRIEFINFO = "briefinfo";
    public static final String ITEM = "item";
    public static final String ADDR = "addr";
    private SQLiteDatabase db;
    private final Context context;

    public  class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper (Context context, String db_name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, db_name, factory, version);
        }

        private static final String DB_CREATE =
                "create table " + DB_TABLE
                        + "(" + NUM + " varchar(50), "
                        + BRIEFINFO + " varchar(500),"
                        + ITEM + " varchar(100),"
                        + ADDR + " varchar(200));";

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

    private DBOpenHelper dbOpenHelper;
    public FixInfoDBAdapter(Context _context){
        context = _context;
    }
    public void close(){
        if (db != null){
            db.close();
            db = null;
        }
    }

    public void open() throws SQLiteException{
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbOpenHelper.getWritableDatabase();
        }catch (SQLiteException ex){
            db = dbOpenHelper.getReadableDatabase();
        }
    }

    public long insert(FixInfo fixInfo){
        ContentValues newValues = new ContentValues();
        newValues.put(NUM, fixInfo.Num);
        newValues.put(BRIEFINFO, fixInfo.BriefInfo);
        newValues.put(ITEM, fixInfo.Item);
        newValues.put(ADDR, fixInfo.Addr);
        return db.insert(DB_TABLE, null, newValues);
    }
}