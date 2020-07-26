package com.example.sqlliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL1="ID";
    public static final String COL2="NAME";
    public static final String COL3="SURNAME";
    public static final String COL4="MARKS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, SURNAME TEXT, MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,String surname,String marks)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,marks);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result ==-1)
        {
            return false;
        }
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    public boolean upDateData(String ID,String name,String surname,String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,ID);
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,marks);

        db.update(TABLE_NAME,contentValues,"ID=?",new String[]{ ID });
        return true;
    }
    public Integer deleteData(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
         return db.delete(TABLE_NAME,"ID = ?", new String[]{ID});
    }
}
