package com.mirea.kt.android.kyrsovaya_yudakova;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DefinitionActivity extends AppCompatActivity {
    private  DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        TextView textView = findViewById(R.id.tvDef);
        String definition = getIntent().getStringExtra("definition");

        TextView definitionTextView = findViewById(R.id.tvDef);
        definitionTextView.setText(definition);
        Log.i("simple_app_tag", "Отображение определения");

    }
}