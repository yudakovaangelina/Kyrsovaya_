package com.mirea.kt.android.kyrsovaya_yudakova;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.mirea.kt.android.kyrsovaya_yudakova.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DBManager dbManager;


    int result;
    JSONArray data;
    private JSONObject jsonObject;
    private JSONArray jSONObject;
    private SQLiteDatabase database;
    private MyAppSQLiteHelper sqLiteHelper;
    private SharedPreferences sharedPreferences;
    private static final String IS_FIRST_LAUNCH = "is_first_launch";
    private ActivityMainBinding binding;
    private EditText inputPswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnEnter.setOnClickListener(this);

        EditText inputPswd = findViewById(R.id.etPassword);
        inputPswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());




    }

    @Override
    public void onClick(View view) {
        Log.i("simple_app_tag", "Начало всего");

        EditText inpupLog = findViewById(R.id.etLogin);
        EditText inputPswd = findViewById(R.id.etPassword);
        inputPswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        String login = inpupLog.getText().toString();
        String pswd = inputPswd.getText().toString();

        if(!login.isEmpty() && !pswd.isEmpty()) {

            StdApp.getServerApi().getStudentInfoAll(login, pswd, "RIBO-02-21").enqueue(new Callback<StudentInfoResponse>() {
                @Override
                public void onResponse(Call<StudentInfoResponse> call, Response<StudentInfoResponse> response) {
                    StudentInfoResponse sir = response.body();

                    int result = sir.getResult();
                    if(result == 1){
                        Intent actIntent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(actIntent);

                    }else if(result == -1) {
                     
                        Toast.makeText(getApplicationContext(), "Неверный пароль или логин", Toast.LENGTH_LONG).show();
                        Log.i("simple_app_tag", "Неверный пароль или логин");


                    }


                    sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                    boolean isFirstLaunch = sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true);
                    if (isFirstLaunch) {
                        String value1 = sir.getData().get(0).getValue();
                        String value2 = sir.getData().get(1).getValue();
                        String value3 = sir.getData().get(2).getValue();
                        String description1 = sir.getData().get(0).getDescription();
                        String description2 = sir.getData().get(1).getDescription();
                        String description3 = sir.getData().get(2).getDescription();

                        Dictionary term = new Dictionary();
                        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        ContentValues cv2 = new ContentValues();
                        ContentValues cv3 = new ContentValues();
                        cv.put("term", value1);
                        cv.put("definition", description1);
                        cv2.put("term", value2);
                        cv2.put("definition", description2);
                        cv3.put("term", value3);
                        cv3.put("definition", description3);

                        long rowId = db.insert("TABLE_DICTIONARY", null, cv);
                        long rowId1 = db.insert("TABLE_DICTIONARY", null, cv2);
                        long rowId2 = db.insert("TABLE_DICTIONARY", null, cv3);
                        cv.clear();
                        db.close();
                        Log.i("simple_app_tag", "Загрузка с сервера");
                        sharedPreferences.edit().putBoolean(IS_FIRST_LAUNCH, false).apply();

                    }


                }

                @Override
                public void onFailure(Call<StudentInfoResponse> call, Throwable t) {
                    Log.i("simple_app_tag", "error");
                }
            });

        }else {
            Toast.makeText(getApplicationContext(),"Необходимо заполнить оба поля",Toast.LENGTH_LONG).show();
        }

    }

}
