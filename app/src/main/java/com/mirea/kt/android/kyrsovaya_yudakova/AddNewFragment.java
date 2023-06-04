package com.mirea.kt.android.kyrsovaya_yudakova;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddNewFragment extends Fragment implements View.OnClickListener {
    private EditText editTextWord, editTextDefinition;
    private  DBManager dbManager;

    private Button btnNext;
    public AddNewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add_new, container, false);

        this.dbManager = new DBManager(new MyAppSQLiteHelper(getActivity(), "my_database.db", null, 1));
        editTextWord = (EditText) v.findViewById(R.id.etWord);
        Log.i("simple_app_tag", "editTextMode " + String.valueOf(editTextWord));
        editTextDefinition = (EditText) v.findViewById(R.id.etDefinition);
        Log.i("simple_app_tag", "editTextSerialNumber " + String.valueOf(editTextDefinition));

        Button btnAdd =(Button) v.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View view) {
        Log.i("simple_app_tag", "Нажатие на кнопку");
        if(view.getId() == R.id.btnAdd){
            if(this.dbManager != null){
                String word = editTextWord.getText().toString();
                String definition = editTextDefinition.getText().toString();

                if(!word.isEmpty() && !definition.isEmpty()){
                    boolean result = dbManager.saveTermToDatabase(new Dictionary(word, definition));
                    if(result){
                        Toast.makeText(getActivity(), "Запись успешно добавлена", Toast.LENGTH_LONG).show();
                        Log.i("simple_app_tag", "Запись успешно добавлена");
                    }else {
                        Toast.makeText(getActivity(), "При добавлении произошла ошибка", Toast.LENGTH_LONG).show();
                        Log.i("simple_app_tag", "При добавлении произошла ошибка");
                    }
                }else {
                    Toast.makeText(getActivity(), "Вы ввели некорректное значение", Toast.LENGTH_LONG).show();
                    Log.i("simple_app_tag", "Вы ввели некорректное значение");
                }
            }
        }

        }
    }
