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
    private static MyAppSQLiteHelper instance;

    public MyAppSQLiteHelper( Context c,  String name, SQLiteDatabase.CursorFactory f, int version) {
        super(c, name, f, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("simple_app_tag", "Создание базы данных");
        db.execSQL("create table " + "TABLE_DICTIONARY" + "("
                + "_id integer primary key autoincrement,"
                + "term String,"
                + "definition String" + ");");



        Log.d("simple_app_tag","Дошло до добавления столбца");
        db.execSQL("ALTER TABLE " + "TABLE_DICTIONARY" + " ADD COLUMN isFavorite BOOLEAN DEFAULT 0");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void deleteTerm(String term) {
        // Получаем объект базы данных.
        SQLiteDatabase db = this.getWritableDatabase();

        // Удаляем строку с переданным термином.
        db.delete("TABLE_DICTIONARY", "term=?", new String[]{term});

        // Закрываем соединение с базой данных.
        db.close();
    }





}

