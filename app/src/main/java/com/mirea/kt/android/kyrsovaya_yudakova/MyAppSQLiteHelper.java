package com.mirea.kt.android.kyrsovaya_yudakova;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAppSQLiteHelper extends SQLiteOpenHelper {


    public MyAppSQLiteHelper( Context c,  String name, SQLiteDatabase.CursorFactory f, int version) {
        super(c, name, f, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("simple_app_tag", "Создание базы данных");
        db.execSQL("create table " + "TABLE_DICTIONARY" + "("
                + "_id integer primary key autoincrement,"
                + "term String,"
                + "isFavorite BOOLEAN,"
                + "definition String" + ");");







    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void deleteTerm(String term) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TABLE_DICTIONARY", "term=?", new String[]{term});
        db.close();
    }





}

