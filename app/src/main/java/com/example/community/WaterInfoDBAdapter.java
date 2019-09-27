package com.example.community;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class WaterInfoDBAdapter {
    private static final String DB_NAME = "water.db";
    private static final String DB_TABLE = "waterinfo";
    private static final int DB_VERSION = 1;
    public static final String NUM = "num";
    public static final String XIAOQUITEM = "xiaoquitem";
    public static final String LOUITEM = "louitem";
    public static final String WATERITEM = "waterItem";
    public static final String ROOMNUM = "roomnum";
    public static final String COUNT = "count";
    public static final String SUM = "sum";
    private SQLiteDatabase db;
    private final Context context;

    public  class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper (Context context, String db_name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, db_name, factory, version);
        }

        private static final String DB_CREATE =
                "create table " + DB_TABLE
                        + "(" + NUM + " varchar(50), "
                        + XIAOQUITEM + " varchar(100),"
                        + LOUITEM + " varchar(100),"
                        + WATERITEM + " varchar(100),"
                        + ROOMNUM + " varchar(100),"
                        + COUNT + " varchar(100),"
                        + SUM + " varchar(200));";

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
    public WaterInfoDBAdapter(Context _context){
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

    public long insert(WaterInfo waterInfo){
        ContentValues newValues = new ContentValues();
        newValues.put(NUM, waterInfo.Num);
        newValues.put(XIAOQUITEM, waterInfo.XiaoQuItem);
        newValues.put(LOUITEM, waterInfo.LouItem);
        newValues.put(WATERITEM, waterInfo.WaterItem);
        newValues.put(ROOMNUM, waterInfo.RoomNum);
        newValues.put(COUNT, waterInfo.Count);
        newValues.put(SUM, waterInfo.Sum);
        return db.insert(DB_TABLE, null, newValues);
    }
}