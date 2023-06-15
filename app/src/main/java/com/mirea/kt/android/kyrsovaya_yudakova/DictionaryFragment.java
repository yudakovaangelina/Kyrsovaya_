package com.mirea.kt.android.kyrsovaya_yudakova;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DictionaryFragment extends Fragment {

    private Button btnOpen;



    private  DBManager dbManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment


        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);

        this.dbManager = new DBManager(new MyAppSQLiteHelper(getActivity(), "my_database.db", null, 1));
        RecyclerView rcView =(RecyclerView) v.findViewById(R.id.recyclerView);
        TermAdapter adapter = new TermAdapter(dbManager.loadAllTermsFromDatabase(), this.getActivity());
        Log.i("simple_app_tag", "Получение записей из базы данных");
        rcView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        rcView.setAdapter(adapter);
        Log.i("simple_app_tag", "Отображение в виде списка");

        return v;
    }


}