package com.mirea.kt.android.kyrsovaya_yudakova;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DBManager {
    public SQLiteOpenHelper sqLiteOpenHelper;


    public DBManager() {
    }

    public DBManager(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }



    public boolean saveTermToDatabase(Dictionary term){
        SQLiteDatabase db = this.sqLiteOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("term", term.getTerm());
        cv.put("definition", term.getDefinition());

        long rowId = db.insert("TABLE_DICTIONARY", null, cv);
        cv.clear();
        db.close();
        Log.i("simple_app_tag", "save term to database");
        return rowId !=1;
    }

    public ArrayList<Dictionary> loadAllTermsFromDatabase(){
        ArrayList<Dictionary> terms = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + "TABLE_DICTIONARY";
        SQLiteDatabase db = this.sqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Dictionary term = new Dictionary(cursor.getString(1), cursor.getString(2), cursor.getInt(3) ==1 );
                terms.add(term);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return terms;
    }




    }

