package com.example.community;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;


public class DianfeiInfoDBAapter {
    private static final String DB_NAME = "dianfei.db";
    private static final String DB_TABLE = "dianfeiinfo";
    private static final int DB_VERSION = 1;
    public static final String NUM = "num";
    public static final String ADDR = "addr";
    public static final String ELEC = "elec";

    private SQLiteDatabase db;
    private final Context context;

    public  class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper (Context context, String db_name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, db_name, factory, version);
        }

        private static final String DB_CREATE =
                "create table " + DB_TABLE
                        + "(" + NUM + " varchar(50), "
                        + ADDR + " varchar(500),"
                        + ELEC + " varchar(50));";

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
        newValues.put(ADDR,dianfeiInfo.Addr);
        newValues.put(ELEC,dianfeiInfo.Elec);
        return db.insert(DB_TABLE, null, newValues);
    }

    public int updateelec(DianfeiInfo dianfeiInfo, String addr) {
        ContentValues newValues = new ContentValues();
        newValues.put(ELEC, dianfeiInfo.Elec);
        return db.update(DB_TABLE, newValues, "addr=?", new String[] { String.valueOf(addr) });
    }

    public int updateaddr(DianfeiInfo dianfeiInfo, String addr) {
        ContentValues newValues = new ContentValues();
        newValues.put(ADDR, dianfeiInfo.Addr);
        return db.update(DB_TABLE, newValues, "addr=?", new String[] { String.valueOf(addr) });
    }

    public long delete(String addr){
        return db.delete(DB_TABLE, "addr=?", new String[] { String.valueOf(addr) });
    }

    public DianfeiInfo[] queryAllData(){
        Cursor results = db.query(DB_TABLE, new String[] { NUM, ADDR, ELEC }, null,
                null,null,null,null);
        return ConvertToPeople(results);
    }

    private DianfeiInfo[] ConvertToPeople(Cursor cursor){
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst())
            return null;
        DianfeiInfo[] dianfeiinfo = new DianfeiInfo[resultCounts];
        for (int i = 0; i < resultCounts; i++){
            dianfeiinfo[i] = new DianfeiInfo();
            dianfeiinfo[i].Num = cursor.getString(cursor.getColumnIndex(NUM));
            dianfeiinfo[i].Addr = cursor.getString(cursor.getColumnIndex(ADDR));
            dianfeiinfo[i].Elec = cursor.getString(cursor.getColumnIndex(ELEC));
            cursor.moveToNext();    // 将游标向下移动一位
        }
        return dianfeiinfo;
    }

}