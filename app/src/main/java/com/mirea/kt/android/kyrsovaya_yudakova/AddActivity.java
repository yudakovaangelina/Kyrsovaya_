package com.mirea.kt.android.kyrsovaya_yudakova;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity  implements View.OnClickListener {
    private EditText editTextWord, editTextDefinition;
    private  DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));
        editTextWord = findViewById(R.id.etWord);
        Log.i("simple_app_tag", "editTextMode " + String.valueOf(editTextWord));
        editTextDefinition = findViewById(R.id.etDefinition);
        Log.i("simple_app_tag", "editTextSerialNumber " + String.valueOf(editTextDefinition));

        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("simple_app_tag", "Нажатие на кнопку");
        if(v.getId() == R.id.btnAdd){
            if(this.dbManager != null){
                String word = editTextWord.getText().toString();
                String definition = editTextDefinition.getText().toString();

                if(!word.isEmpty() && !definition.isEmpty()){
                    boolean result = dbManager.saveTermToDatabase(new Dictionary(word, definition));
                    if(result){
                        Toast.makeText(this, "Запись успешно добавлена", Toast.LENGTH_LONG).show();
                        Log.i("simple_app_tag", "Запись успешно добавлена");
                    }else {
                        Toast.makeText(this, "При добавлении произошла ошибка", Toast.LENGTH_LONG).show();
                        Log.i("simple_app_tag", "При добавлении произошла ошибка");
                    }
                }else {
                    Toast.makeText(this, "Вы ввели некорректное значение", Toast.LENGTH_LONG).show();
                    Log.i("simple_app_tag", "Вы ввели некорректное значение");
                }
            }
        }

    }
}