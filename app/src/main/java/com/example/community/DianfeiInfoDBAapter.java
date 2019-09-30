package com.example.community;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DianfeiInfoDBAapter {
    private static final String DB_NAME = "dianfei.db";
    private static final String DB_TABLE = "dianfeiinfo";
    private static final int DB_VERSION = 1;
    public static final String NUM = "num";
    public static final String GONGYU = "gongyu";
    public static final String LOUHAO = "louhao";
    public static final String LOUCENG = "louceng";
    public static final String QINSHIHAO = "qinshihao";
    private SQLiteDatabase db;
    private final Context context;

    public  class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper (Context context, String db_name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, db_name, factory, version);
        }

        private static final String DB_CREATE =
                "create table " + DB_TABLE
                        + "(" + NUM + " varchar(50), "
                        + GONGYU + " varchar(500),"
                        + LOUHAO + " varchar(100),"
                        + LOUCENG + " varchar(200),"
                        + QINSHIHAO + " varchar(200));";

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
    public DianfeiInfoDBAapter(Context _context){
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

    public long insert(DianfeiInfo dianfeiInfo){
        ContentValues newValues = new ContentValues();
        newValues.put(NUM, dianfeiInfo.Num);
        newValues.put(GONGYU,dianfeiInfo.gongyu);
        newValues.put(LOUHAO,dianfeiInfo.louhao);
        newValues.put(LOUCENG,dianfeiInfo.louceng);
        newValues.put(QINSHIHAO,dianfeiInfo.qisnhihao);
        return db.insert(DB_TABLE, null, newValues);
    }
}