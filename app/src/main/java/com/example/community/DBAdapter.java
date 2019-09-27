package com.example.community;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    private static final String DB_NAME = "people.db";      // 数据库名称
    private static final String DB_TABLE = "peopleinfo";    // 数据表名称
    private static final int DB_VERSION = 1;                // 数据库版本号
    public static final String NAME = "name";               //姓名字段名称
    public static final String NUM = "num";                 // 学号字段名称
    public static final String PWD = "pwd";                 // 密码字段名称
    public static final String PHO = "pho";                 //手机字段名称
    private SQLiteDatabase db;                               // people 数据库
    private final Context context;

    /** 静态 Helper 类，用于建立、更新和打开数据库 */
    // DBOpenHelper 作为访问 SQLite 的助手类，提供两方面功能：
    // (1) 提供 getReadableDatabase() 和 getWritableDatabase() 可以获得 SQLiteDatabase 对象
    // (2) 提供 onCreate() 和 onUpgrade() 两个回调函数，允许在创建和升级数据库时，进行自己的操作
    public  class DBOpenHelper extends SQLiteOpenHelper {
        // 在 SQLiteOpenHelper 的子类中必须右该构造函数
        public DBOpenHelper (Context context, String db_name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, db_name, factory, version);
        }
        // 创建数据表的 SQL 语句
        private static final String DB_CREATE =
                "create table " + DB_TABLE
                        + "(" + NAME + " varchar(50),"             // 姓名：字符型字段
                        + NUM + " varchar(50) primary key, "   // 学号：字符型主键字段
                        + PWD + " varchar(50),"                        // 密码： 字符型字段
                        + PHO + " varchar(50));";                 // 手机： 字符型字段

        @Override
        // 该函数在第一次创建数据库的时候执行，在第一次得到 SQLiteDatabase 对象的时候才会调用
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        // 该函数在升级数据库时调用
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

    private DBOpenHelper dbOpenHelper;
    public DBAdapter(Context _context){
        context = _context;
    }
    // 关闭数据库
    public void close(){
        if (db != null){
            db.close();
            db = null;
        }
    }
    // 创建及打开数据库
    public void open() throws SQLiteException{
        // 创建一个 DatabaseHelper 对象
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        // 只有调用了 DatabaseHelper 对象的 getReadableDatabase() 或者 getWritableDatabase()
        // 方法才会调用 DBOpenHelper 的 onCreate() 方法
        try {
            db = dbOpenHelper.getWritableDatabase();
        }catch (SQLiteException ex){
            db = dbOpenHelper.getReadableDatabase();
        }
    }
    // 删除数据库
    public void delete() throws SQLiteException{
        context.deleteDatabase(DB_NAME);
    }

    // 增加记录
    public long insert(People people){
        // 生成 ContentValues 对象
        ContentValues newValues = new ContentValues();
        // 向该对象当中插入键值对，其中键是列明，值是希望插入到这一列的值，值必须和键的类型匹配
        newValues.put(NAME, people.Name);
        newValues.put(NUM, people.Num);
        newValues.put(PWD, people.Pwd);
        newValues.put(PHO, people.Pho);
        return db.insert(DB_TABLE, null, newValues);
    }

    // 修改密码
    public int updatepwd(People people, String pho) {
        ContentValues newValues = new ContentValues();
        newValues.put(PWD, people.Pwd);
        return db.update(DB_TABLE, newValues, "pho=?", new String[] { String.valueOf(pho) });
    }

    // 查询记录
    public People[] queryAllData(){
        Cursor results = db.query(DB_TABLE, new String[] { NAME, NUM, PWD, PHO }, null,
                null,null,null,null);
        return ConvertToPeople(results);
    }
    private People[] ConvertToPeople(Cursor cursor){
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst())
            return null;
        People[] peoples = new People[resultCounts];
        for (int i = 0; i < resultCounts; i++){
            peoples[i] = new People();
            peoples[i].Name = cursor.getString(cursor.getColumnIndex(NAME));
            peoples[i].Num = cursor.getString(cursor.getColumnIndex(NUM));
            peoples[i].Pwd = cursor.getString(cursor.getColumnIndex(PWD));
            peoples[i].Pho = cursor.getString(cursor.getColumnIndex(PHO));
            cursor.moveToNext();    // 将游标向下移动一位
        }
        return peoples;
    }
}